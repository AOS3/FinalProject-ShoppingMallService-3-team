package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class InquiryListViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){
    val shoppingApplication = context as ShoppingApplication

    val checkBoxMyinquiryValue = mutableStateOf(false)

    fun inquiryWriteFabOnClick(){
        shoppingApplication.navHostController.navigate("inquiryWrite")
    }

    fun inquiryListOnClick(){
        shoppingApplication.navHostController.popBackStack("inquiryList", inclusive = true)
        shoppingApplication.navHostController.navigate("inquiryRead")
    }

    fun navigationOnClick(){
        shoppingApplication.navHostController.popBackStack("inquiryList", inclusive = true)
        shoppingApplication.navHostController.navigate("loginMyPage")
    }
}