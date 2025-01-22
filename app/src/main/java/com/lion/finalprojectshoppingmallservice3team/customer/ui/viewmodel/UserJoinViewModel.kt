package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class UserJoinViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

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
}