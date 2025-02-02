package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.NaverIdLoginSDK.getAccessToken
import com.navercorp.nid.oauth.NidOAuthPreferencesManager.accessToken
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class UserJoinViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    val joinError = mutableStateOf<String?>(null)

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.popBackStack("userJoin", inclusive = true) // 회원가입 스택 제거
        shoppingApplication.navHostController.navigate("logoutMyPage") {
            launchSingleTop = true // 중복 생성 방지
        }
    }

    fun buttonLoginClick(){
        shoppingApplication.navHostController.popBackStack("userJoin", inclusive = true)
        shoppingApplication.navHostController.navigate("login") {
        }
    }

    fun buttonNextOnClick(){
        shoppingApplication.navHostController.navigate("userJoinInfo")
    }

    fun kakaoSignUp(context: Context) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                joinError.value = "카카오 회원가입 실패: ${error.localizedMessage}"
            } else if (token != null) {
                handleSignUp(context, token)
            }
        }
    }

    private fun handleSignUp(context: Context, token: OAuthToken) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                joinError.value = "사용자 정보 요청 실패: ${error.localizedMessage}"
            } else if (user != null) {
                customerService.checkExistingUser(user.id.toString()) { isExisting, _ ->
                    if (isExisting) {
                        // 이미 회원인 경우 로그인 페이지로 이동
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, "이미 회원입니다. 로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT)
                                .show()
                            shoppingApplication.navHostController.navigate("login") {
                                popUpTo("userJoin") { inclusive = true }
                            }
                        }
                    } else {
                        // 회원가입 진행
                        viewModelScope.launch {
                            try {
                                customerService.saveKakaoUser(user)
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(
                                        context,
                                        "회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    shoppingApplication.navHostController.navigate("login") {
                                        popUpTo("userJoin") { inclusive = true }
                                    }
                                }
                            } catch (e: Exception) {
                                joinError.value = "회원가입 실패: ${e.localizedMessage}"
                            }
                        }
                    }
                }
            }
        }
    }

    fun naverSignUp(context: Context) {
        // 네이버 로그인 SDK 초기화
        NaverIdLoginSDK.initialize(
            context,
            "0avaFaV2h1h8qOZrZsje",    // 네이버 개발자 콘솔에서 발급받은 Client ID
            "_t4QPC6H_f",              // 네이버 개발자 콘솔에서 발급받은 Client Secret
            "marcshop"                 // 네이버 앱 이름
        )

        // 네이버 로그인 시작
        NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
            override fun onSuccess() {
                val accessToken = NaverIdLoginSDK.getAccessToken()
                if (accessToken != null) {
                    handleNaverSignUp(context, accessToken)
                } else {
                    joinError.value = "네이버 로그인 실패: Access Token을 가져올 수 없습니다."
                }
            }

            override fun onFailure(httpStatus: Int, message: String) {
                joinError.value = "네이버 로그인 실패: $message"
            }

            override fun onError(errorCode: Int, message: String) {
                joinError.value = "네이버 로그인 에러: $message"
            }
        })
    }

    private fun handleNaverSignUp(context: Context, accessToken: String) {
        viewModelScope.launch {
            try {
                // 사용자 정보 가져오기
                val userInfo = fetchNaverUserInfo(accessToken)
                Log.d("userInfo", "userInfo: $userInfo")
                val userId = userInfo.getString("id")
                val nickname = userInfo.optString("nickname", "unknown")
                val profileImage = userInfo.optString("profile_image", "unknown")
                val gender = userInfo.optString("gender", "unknown")
                val mobile = userInfo.optString("mobile", "unknown")
                val name = userInfo.optString("name", "unknown")
                val birthday = userInfo.optString("birthday", "unknown")
                val birthyear = userInfo.optString("birthyear", "unknown")

                processUserSignUp(context, userId, nickname, profileImage, gender,
                    mobile, name, birthday, birthyear)
            } catch (e: Exception) {
                joinError.value = "네이버 사용자 정보 요청 실패: ${e.localizedMessage}"
            }
        }
    }

    private suspend fun fetchNaverUserInfo(accessToken: String): JSONObject {
        val url = "https://openapi.naver.com/v1/nid/me"
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return withContext(Dispatchers.IO) {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                if (!responseBody.isNullOrEmpty()) {
                    JSONObject(responseBody).getJSONObject("response")
                } else {
                    throw Exception("Empty response body")
                }
            } else {
                throw Exception("HTTP Error: ${response.code}")
            }
        }
    }

    private fun processUserSignUp(context: Context, userId: String, nickname: String, profileImage: String,
                                  gender: String, mobile: String, name: String,
                                  birthday: String, birthyear: String) {
        customerService.checkExistingUser(userId) { isExisting, _ ->
            if (isExisting) {
                navigateToLogin(context, "이미 회원입니다. 로그인 페이지로 이동합니다.")
            } else {
                saveNewUser(context, userId, nickname, profileImage, gender, mobile, name, birthday, birthyear)
            }
        }
    }

    private fun saveNewUser(context: Context, userId: String, nickname: String, profileImage: String,
                            gender: String, mobile: String, name: String, birthday: String, birthyear: String) {
        viewModelScope.launch {
            try {
                customerService.saveNaverUser(userId, nickname, profileImage, gender, mobile, name, birthday, birthyear)
                navigateToLogin(context, "회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.")
            } catch (e: Exception) {
                joinError.value = "회원가입 실패: ${e.localizedMessage}"
            }
        }
    }

    fun googleSignUp(context: Context, launcher: ActivityResultLauncher<Intent>) {
        val googleSignInClient = getGoogleSignInClient(context)
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent) // 로그인 화면 호출
    }

    private fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(
                Scope("https://www.googleapis.com/auth/userinfo.profile"),
                Scope("https://www.googleapis.com/auth/user.birthday.read"),
                Scope("https://www.googleapis.com/auth/user.gender.read"),
                Scope("https://www.googleapis.com/auth/user.addresses.read"),
                Scope("https://www.googleapis.com/auth/user.phonenumbers.read"),
                Scope("https://www.googleapis.com/auth/contacts")
            )
            .requestServerAuthCode(context.getString(R.string.firebase_api_key3)) // ServerAuthCode 요청
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun handleGoogleSignUpResult(context: Context, task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val userId = account.id ?: "unknown"
                val name = account.displayName ?: "unknown"
                val profileImage = account.photoUrl ?: "unknown"

                // ServerAuthCode를 통해 Access Token 교환
                val serverAuthCode = account.serverAuthCode
                if (serverAuthCode != null) {
                    Log.d("GoogleSignUp", "Server Auth Code: $serverAuthCode")

                    // Access Token 교환
                    exchangeServerAuthCodeForAccessToken(context, serverAuthCode) { accessToken ->
                        if (accessToken != null) {
                            Log.d("GoogleSignUp", "Access Token: $accessToken")
                            fetchGoogleUserDetails(context, accessToken) { additionalData ->
                                processGoogleSignUp(context, userId, name,
                                    profileImage.toString(), additionalData)
                            }
                        } else {
                            joinError.value = "Access Token 교환에 실패했습니다."
                        }
                    }
                } else {
                    joinError.value = "Server Auth Code를 가져올 수 없습니다."
                }
            } else {
                joinError.value = "Google 회원가입 실패: 계정 정보를 가져올 수 없습니다."
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignUp", "Google Sign-In 실패", e)
            joinError.value = "Google 회원가입 실패: ${e.message}"
        }
    }

    private fun exchangeServerAuthCodeForAccessToken(
        context: Context,
        serverAuthCode: String,
        callback: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = "https://oauth2.googleapis.com/token"
                val client = OkHttpClient()

                // 요청 본문 구성
                val body = FormBody.Builder()
                    .add("code", serverAuthCode)
                    .add("client_id", context.getString(R.string.firebase_api_key3)) // 클라이언트 ID
                    .add("client_secret", context.getString(R.string.firebase_client_secret)) // 클라이언트 비밀키
                    .add("redirect_uri", "https://shoppingmall-7dd46.firebaseapp.com/__/auth/handler") // 등록된 Redirect URI
                    .add("grant_type", "authorization_code")
                    .build()

                val request = Request.Builder()
                    .url(url)
                    .post(body)
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                Log.e("AccessToken Error Response", responseBody ?: "No response body")

                if (response.isSuccessful && !responseBody.isNullOrEmpty()) {
                    val jsonObject = JSONObject(responseBody)
                    val accessToken = jsonObject.optString("access_token")
                    Log.d("AccessToken", "Access Token: $accessToken")
                    withContext(Dispatchers.Main) {
                        callback(accessToken)
                    }
                } else {
                    Log.e("AccessToken", "Access Token 교환 실패: HTTP ${response.code}")
                    Log.e("AccessToken Error", responseBody ?: "No error details")
                    withContext(Dispatchers.Main) {
                        callback(null)
                    }
                }
            } catch (e: Exception) {
                Log.e("AccessToken", "Access Token 교환 중 오류 발생", e)
                withContext(Dispatchers.Main) {
                    callback(null)
                }
            }
        }
    }

    private fun fetchGoogleUserDetails(context: Context, accessToken: String, callback: (JSONObject) -> Unit) {
        val url = "https://people.googleapis.com/v1/people/me?personFields=addresses,phoneNumbers,birthdays,genders"
        val client = OkHttpClient()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    Log.d("PeopleAPI", "URL: $url")
                    Log.d("PeopleAPI", "Access Token: $accessToken")

                    val request = Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer $accessToken")
                        .build()

                    val response = client.newCall(request).execute()
                    Log.d("PeopleAPI", "HTTP Status Code: ${response.code}")

                    if (!response.isSuccessful) {
                        val errorBody = response.body?.string()
                        Log.e("PeopleAPI", "HTTP ${response.code}: ${response.message}")
                        Log.e("PeopleAPIError", errorBody ?: "No error details")
                        joinError.value = "Google People API 호출 실패: HTTP ${response.code}"
                    }

                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        if (!responseBody.isNullOrEmpty()) {
                            Log.d("PeopleAPI", "Response Body: $responseBody")
                            val userDetails = JSONObject(responseBody)
                            withContext(Dispatchers.Main) {
                                callback(userDetails)
                            }
                        } else {
                            Log.e("PeopleAPI", "Response Body가 비어 있습니다.")
                            joinError.value = "Google People API 응답이 비어 있습니다."
                        }
                    } else {
                        Log.e("PeopleAPI", "HTTP ${response.code}: ${response.message}")
                        joinError.value = "Google People API 호출 실패: HTTP ${response.code}"
                    }
                } catch (e: Exception) {
                    Log.e("PeopleAPI", "Google People API 호출 중 오류 발생", e)
                    joinError.value = "Google People API 호출 실패: ${e.message}"
                }
            }
        }
    }

    private fun processGoogleSignUp(context: Context, userId: String, name: String, profileImage: String, additionalData: JSONObject
    ) {
        // People API에서 추가 정보 추출
        val birthday = additionalData.optJSONArray("birthdays")?.optJSONObject(0)?.optJSONObject("date")
        val birthDate = if (birthday != null) {
            "${birthday.optInt("year", 0)}${birthday.optInt("month", 0)}${birthday.optInt("day", 0)}"
        } else {
            ""
        }

        val gender = additionalData.optJSONArray("genders")?.optJSONObject(0)?.optString("value") ?: ""
        val phoneNumber = additionalData.optJSONArray("phoneNumbers")?.optJSONObject(0)?.optString("canonicalForm") ?: ""
        val address = additionalData.optJSONArray("addresses")?.optJSONObject(0)?.optString("formattedValue") ?: ""

        customerService.checkExistingUser(userId) { isExisting, _ ->
            if (isExisting) {
                navigateToLogin(context, "이미 회원입니다. 로그인 페이지로 이동합니다.")
            } else {
                saveNewGoogleUser(context, userId, name, profileImage, phoneNumber, birthDate, gender, address)
            }
        }
    }

    private fun saveNewGoogleUser(context: Context, userId: String, name: String, profileImage: String, phoneNumber: String, birthDate: String,
        gender: String, address: String,
    ) {
        viewModelScope.launch {
            try {
                customerService.saveGoogleUser(userId = userId, name = name, profileImage = profileImage, phoneNumber = phoneNumber, birthDate = birthDate,
                    gender = gender, address = address
                )
                navigateToLogin(context, "회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.")
            } catch (e: Exception) {
                joinError.value = "회원가입 실패: ${e.localizedMessage}"
            }
        }
    }

    private fun navigateToLogin(context: Context, message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.navigate("login") {
                popUpTo("userJoin") { inclusive = true }
            }
        }
    }
}