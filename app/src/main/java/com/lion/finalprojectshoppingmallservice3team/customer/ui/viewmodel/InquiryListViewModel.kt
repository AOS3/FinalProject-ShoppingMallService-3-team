package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

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
        shoppingApplication.navHostController.navigate("inquiryRead")
    }

    fun navigationOnClick(){
        shoppingApplication.navHostController.navigate("loginMyPage"){
            popUpTo("inquiryList") {inclusive = true}
        }
    }
}