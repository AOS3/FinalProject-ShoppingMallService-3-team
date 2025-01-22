package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyRecentViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 현재 선택된 탭 인덱스
    val selectedTabIndex = mutableStateOf(0)

    fun navigationOnClick(){
        shoppingApplication.navHostController.popBackStack("myRecent", inclusive = true)
        shoppingApplication.navHostController.navigate("loginMyPage")
    }
}