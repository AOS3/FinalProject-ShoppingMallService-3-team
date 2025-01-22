package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class InquiryWriteViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){
    val shoppingApplication = context as ShoppingApplication

    val inquiryTitleValue = mutableStateOf("")
    val inquiryContentValue = mutableStateOf("")
    val inquiryFileValue = mutableStateOf("")

    fun inquiryWriteCloseOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun inquiryWriteCancelOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun inquiryWriteSuccessOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }
}