package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class InquiryReadViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    fun navigationOnClick(){
        shoppingApplication.navHostController.popBackStack("inquiryRead", inclusive = true)
    }
}