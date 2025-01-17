package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.LogoutMyPageScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    val textFieldLoginIdValue = mutableStateOf("")
    val textFieldLoginPwValue = mutableStateOf("")

    val isButtonEnabled = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value = textFieldLoginIdValue.value.isNotEmpty() &&
                                textFieldLoginPwValue.value.isNotEmpty()
    }

    fun buttonLoginClick(){
        shoppingApplication.navHostController.navigate("loginMyPage") {
            popUpTo("logoutMyPage") { inclusive = true }
            popUpTo("login") { inclusive = true }
        }
    }

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.navigate("logoutMyPage") {
            popUpTo("login") { inclusive = true }
            popUpTo("userJoin") { inclusive = true }
        }
    }

    fun buttonUserJoinClick(){
        shoppingApplication.navHostController.navigate("userJoin")
    }
}