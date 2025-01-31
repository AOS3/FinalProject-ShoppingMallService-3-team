package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ShopOrderSheetWriteViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel(){

    // 뒤로가기 버튼
    fun navigationButtonClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    val shoppingApplication = context as ShoppingApplication
}