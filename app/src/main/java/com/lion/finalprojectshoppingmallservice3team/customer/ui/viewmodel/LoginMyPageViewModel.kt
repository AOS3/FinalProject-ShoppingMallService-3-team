package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginMyPageViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    val textNickname = mutableStateOf(shoppingApplication.loginCustomerModel.customerUserNickName)

    val isCreator = mutableStateOf(shoppingApplication.loginCustomerModel.isCreator)

    fun logoutOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("logoutMyPage")
    }

    fun userSettingOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("userSetting")
    }

    fun myPostsOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myPosts")
    }

    fun myRecentOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myRecent")
    }

    fun myPerchaseHistoryOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myPurchaseHistory")
    }

    fun inquiryOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("inquiryList")
    }
}