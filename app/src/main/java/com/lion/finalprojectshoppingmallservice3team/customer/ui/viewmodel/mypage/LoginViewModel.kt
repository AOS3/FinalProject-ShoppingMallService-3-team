package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    val textFieldLoginIdValue = mutableStateOf("")
    val textFieldLoginPwValue = mutableStateOf("")

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