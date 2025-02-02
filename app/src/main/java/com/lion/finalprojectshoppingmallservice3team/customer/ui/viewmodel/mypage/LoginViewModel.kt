package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.content.Intent
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
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.LoginResult
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    val textFieldLoginIdValue = mutableStateOf("")
    val textFieldLoginPwValue = mutableStateOf("")

    val loginError = mutableStateOf<String?>(null)

    // 아이디 입력 오류 다이얼로그 상태변수
    val alertDialogUserIdState = mutableStateOf(false)
    // 비밀번호 입력 오류 다이얼로그 상태변수
    val alertDialogUserPwState = mutableStateOf(false)
    // 존재하지 않는 아이디 오류 다이얼로그 상태변수
    val alertDialogLoginFail1State = mutableStateOf(false)
    // 잘못된 비밀번호 다이얼로그 상태변수
    val alertDialogLoginFail2State = mutableStateOf(false)
    // 탈퇴한 회원 다이얼로그 상태변수
    val alertDialogLoginFail3State = mutableStateOf(false)

    val isButtonEnabled = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value = textFieldLoginIdValue.value.isNotEmpty() &&
                                textFieldLoginPwValue.value.isNotEmpty()
    }

    fun saveTokenToPreferences(context: Context, accessToken: String) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("accessToken", accessToken) // 토큰 저장
        editor.apply()
    }

    fun saveTokenToFirebase(userId: String, accessToken: String) {
        val db = FirebaseFirestore.getInstance()
        val userDocument = db.collection("CustomerData").document(userId)

        userDocument.update("autoLoginToken", accessToken)
            .addOnSuccessListener {
                println("토큰이 Firebase에 저장되었습니다.")
            }
            .addOnFailureListener { e ->
                println("Firebase에 토큰 저장 실패: ${e.localizedMessage}")
            }
    }

    fun kakaoLogin(context: Context) {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                loginError.value = "카카오 로그인 실패: ${error.localizedMessage}"
            } else if (token != null) {
                handleLogin(context, token)
            }
        }
    }

    private fun handleLogin(context: Context, token: OAuthToken) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                loginError.value = "사용자 정보 요청 실패: ${error.localizedMessage}"
            } else if (user != null) {
                // Firestore에서 사용자 확인
                customerService.checkExistingUser(user.id.toString()) { isExisting, documentId ->
                    if (isExisting && documentId != null) {
                        // Firestore에서 사용자 데이터를 가져옴
                        viewModelScope.launch {
                            try {
                                val customerModel = customerService.fetchCustomerData(documentId)

                                // SharedPreferences에 토큰 저장
                                saveTokenToPreferences(context, token.accessToken)

                                // Firebase에 토큰 저장
                                saveTokenToFirebase(documentId, token.accessToken)

                                // 사용자 정보를 Application 객체에 저장
                                if (customerModel != null) {
                                    shoppingApplication.loginCustomerModel = customerModel
                                }

                                shoppingApplication.isLoggedIn.value = true
                                // 네비게이션 처리
                                shoppingApplication.navHostController.popBackStack("logoutMyPage", true)
                                shoppingApplication.navHostController.navigate("loginMyPage") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } catch (e: Exception) {
                                println("사용자 데이터 가져오기 실패: ${e.localizedMessage}")
                            }
                        }
                    } else {
                        // 회원이 아닌 경우 회원가입 페이지로 이동
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(
                                context,
                                "회원이 아닙니다. 회원가입부터 진행해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()
                            shoppingApplication.navHostController.navigate("userJoin") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }

    fun naverLogin(context: Context) {
        // 네이버 로그인 SDK 초기화
        NaverIdLoginSDK.initialize(
            context,
            "0avaFaV2h1h8qOZrZsje",    // 네이버 개발자 콘솔에서 발급받은 Client ID
            "_t4QPC6H_f",              // 네이버 개발자 콘솔에서 발급받은 Client Secret
            "marcshop"                 // 네이버 앱 이름
        )

        // 네이버 로그인 요청
        NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
            override fun onSuccess() {
                val accessToken = NaverIdLoginSDK.getAccessToken()
                if (accessToken != null) {
                    handleNaverLogin(context, accessToken)
                } else {
                    loginError.value = "네이버 로그인 실패: Access Token을 가져올 수 없습니다."
                }
            }

            override fun onFailure(httpStatus: Int, message: String) {
                loginError.value = "네이버 로그인 실패: $message"
            }

            override fun onError(errorCode: Int, message: String) {
                loginError.value = "네이버 로그인 에러: $message"
            }
        })
    }

    private fun handleNaverLogin(context: Context, accessToken: String) {
        viewModelScope.launch {
            try {
                // 네이버 사용자 정보 가져오기
                val userInfo = fetchNaverUserInfo(accessToken)
                val userId = userInfo.getString("id")

                // Firebase에서 사용자 확인
                customerService.checkExistingUser(userId) { isExisting, documentId ->
                    if (isExisting && documentId != null) {
                        // 기존 사용자 처리
                        processExistingUser(context, accessToken, documentId)
                    } else {
                        // 회원가입 페이지로 이동
                        navigateToSignUp(context)
                    }
                }
            } catch (e: Exception) {
                loginError.value = "네이버 사용자 정보 요청 실패: ${e.localizedMessage}"
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

    private fun processExistingUser(context: Context, accessToken: String, documentId: String) {
        viewModelScope.launch {
            try {
                // 사용자 데이터 가져오기
                val customerModel = customerService.fetchCustomerData(documentId)

                // SharedPreferences에 토큰 저장
                saveTokenToPreferences(context, accessToken)

                // Firebase에 토큰 저장
                saveTokenToFirebase(documentId, accessToken)

                // 사용자 정보를 Application 객체에 저장
                if (customerModel != null) {
                    shoppingApplication.loginCustomerModel = customerModel
                }

                shoppingApplication.isLoggedIn.value = true

                // 네비게이션 처리
                shoppingApplication.navHostController.popBackStack("logoutMyPage", true)
                shoppingApplication.navHostController.navigate("loginMyPage") {
                    popUpTo("login") { inclusive = true }
                }
            } catch (e: Exception) {
                loginError.value = "사용자 데이터 가져오기 실패: ${e.localizedMessage}"
            }
        }
    }

    private fun navigateToSignUp(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(
                context,
                "회원이 아닙니다. 회원가입부터 진행해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            shoppingApplication.navHostController.navigate("userJoin") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    fun googleLogin(context: Context, launcher: ActivityResultLauncher<Intent>) {
        val googleSignInClient = getGoogleSignInClient(context)
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent) // Google 로그인 화면 호출
    }

    private fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(context.getString(R.string.firebase_api_key3)) // Firebase 클라이언트 ID
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun handleGoogleLoginResult(context: Context, task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val userId = account.id ?: "unknown"
                val name = account.displayName ?: "unknown"
                val email = account.email ?: "unknown"
                val idToken = account.idToken

                if (idToken != null) {
                    handleLogin(context, idToken, userId, name, email)
                } else {
                    loginError.value = "Google ID Token을 가져올 수 없습니다."
                }
            } else {
                loginError.value = "Google 계정 정보를 가져올 수 없습니다."
            }
        } catch (e: ApiException) {
            loginError.value = "Google 로그인 실패: ${e.localizedMessage}"
        }
    }

    private fun handleLogin(context: Context, idToken: String, userId: String, name: String, email: String) {
        // Firestore에서 사용자 확인
        customerService.checkExistingUser(userId) { isExisting, documentId ->
            if (isExisting && documentId != null) {
                // 기존 사용자 처리
                processExistingUser(context, idToken, documentId)
            } else {
                // 신규 사용자 처리
                navigateToSignUp(context)
            }
        }
    }

    fun buttonLoginClick(){
        if(textFieldLoginIdValue.value.isEmpty()){
            alertDialogUserIdState.value = true
            return
        }

        if(textFieldLoginPwValue.value.isEmpty()){
            alertDialogUserPwState.value = true
            return
        }

        // 사용자가 입력한 아이디와 비밀번호
        val loginUserId = textFieldLoginIdValue.value
        val loginUserPw = textFieldLoginPwValue.value

        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.checkLogin(loginUserId, loginUserPw)
            }
            // 로그인 결과를 가져온다.
            val loginResult = work1.await()

            // 로그인 결과로 분기한다.
            when(loginResult){
                LoginResult.LOGIN_RESULT_ID_NOT_EXIST -> {
                    alertDialogLoginFail1State.value = true
                }
                LoginResult.LOGIN_RESULT_PASSWORD_INCORRECT -> {
                    alertDialogLoginFail2State.value = true
                }
                LoginResult.LOGIN_RESULT_SIGNOUT_MEMBER -> {
                    alertDialogLoginFail3State.value = true
                }
                LoginResult.LOGIN_RESULT_SUCCESS -> {
                    // 로그인한 사용자 정보를 가져온다.
                    val work2 = async(Dispatchers.IO){
                        customerService.selectUserDataByUserIdOne(loginUserId)
                    }
                    val loginCustomerModel = work2.await()
                    shoppingApplication.isLoggedIn.value = true
                    // Application 객체에 로그인한 사용자의 정보를 담고 게시판 메인 화면으로 이동한다.
                    shoppingApplication.loginCustomerModel = loginCustomerModel
                    shoppingApplication.navHostController.popBackStack("logoutMyPage", inclusive = true)
                    shoppingApplication.navHostController.navigate("loginMyPage") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        }
        updateButtonState()
    }

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.popBackStack("login", inclusive = true)
        shoppingApplication.navHostController.navigate("logoutMyPage"){
            // 기본 스택 동작 유지
            launchSingleTop = true
        }
    }

    fun buttonUserJoinClick(){
        shoppingApplication.navHostController.popBackStack("login", inclusive = true)
        shoppingApplication.navHostController.navigate("userJoin")
    }
}