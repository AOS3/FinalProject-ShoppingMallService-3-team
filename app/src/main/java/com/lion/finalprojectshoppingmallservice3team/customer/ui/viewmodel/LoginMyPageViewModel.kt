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

    val isCreator = mutableStateOf(true)

    fun logoutOnClick(){
        shoppingApplication.navHostController.navigate("logoutMyPage") {
            popUpTo("loginMyPage") { inclusive = true }
        }
    }

    fun userSettingOnClick(){
        shoppingApplication.navHostController.navigate("userSetting") {
        }
    }

    fun myPostsOnClick(){
        shoppingApplication.navHostController.navigate("myPosts") {
        }
    }

    fun myRecentOnClick(){
        shoppingApplication.navHostController.navigate("myRecent") {
        }
    }

    fun myPerchaseHistoryOnClick(){
        shoppingApplication.navHostController.navigate("myPurchaseHistory") {
        }
    }

    fun inquiryOnClick(){
        shoppingApplication.navHostController.navigate("inquiryList") {
        }
    }
}