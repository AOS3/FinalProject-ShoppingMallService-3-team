package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LogoutMyPageViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 사이트 이용 시 유의사항 다이얼로그 상태 변수
    val showDialogSiteInfoState = mutableStateOf(false)

    // 사이트 이용 시 유의사항을 눌렀을때
    fun siteInfoOnClick(){
        showDialogSiteInfoState.value = true
    }

    fun loginOnClick(){
        shoppingApplication.navHostController.popBackStack("logoutMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("login")
    }

    fun userJoinOnClick(){
        shoppingApplication.navHostController.popBackStack("logoutMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("userJoin") {
        }
    }
}