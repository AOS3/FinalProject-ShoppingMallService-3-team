package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CancelRefundFAQViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel()
{
    val shoppingApplication = context as ShoppingApplication

    // 뒤로가기 버튼
    fun navigationButtonClick(){
        shoppingApplication.navHostController.popBackStack()
    }
}
