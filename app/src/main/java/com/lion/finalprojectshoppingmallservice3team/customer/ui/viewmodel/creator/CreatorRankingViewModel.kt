package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class CreatorRankingViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    // 뒤로 가기 버튼
    fun popBack(){
        shoppingApplication.navHostController.popBackStack()
    }

    /****************************************************WEEKLY RANKING********************************************************************************************************/

    // 크리에이터 이름
    val creatorName = mutableStateOf("")
    // 크리에이터 종류
    val creatorSubName = mutableStateOf("")
    // 프로필
    val creatorProfile = mutableStateOf<Uri?>(null)
}