package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                        customerService.saveKakaoUserToFirebase(user)
                        CoroutineScope(Dispatchers.Main).launch {
                            Toast.makeText(context, "회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT)
                                .show()
                            shoppingApplication.navHostController.navigate("login") {
                                popUpTo("userJoin") { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }
}