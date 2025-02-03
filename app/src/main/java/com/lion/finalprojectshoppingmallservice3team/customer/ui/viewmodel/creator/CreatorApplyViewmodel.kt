package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CreatorApplyViewmodel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 샵 이름
    var shopName by mutableStateOf("")
    // 도메인 명
    var domainName by mutableStateOf("")
    // 본인 또는 브랜드 소개
    var brandDescription by mutableStateOf("")
    // 회사명
    var companyName by mutableStateOf("")
    // 회사 서류 제출
    var fileUploaded by mutableStateOf(false)

    // 첫번쨰 화면
    fun navigationFirstIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApply", inclusive = true)
        shoppingApplication.navHostController.navigate("home"){
            launchSingleTop = true // 중복 생성 방지

        }
    }
    fun buttonFirstNextOnClick(){
        shoppingApplication.navHostController.navigate("creatorApplySecond")
    }

    // 두번쨰 화면
    fun navigationSecondIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApplySecond", inclusive = true)
        shoppingApplication.navHostController.navigate("creatorApply"){
            launchSingleTop = true // 중복 생성 방지

        }
    }
    fun buttonSecondNextOnClick(){
        shoppingApplication.navHostController.navigate("creatorApplyThird")
    }

    // 세번쨰 화면
    fun navigationThirdIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApplyThird", inclusive = true)
        shoppingApplication.navHostController.navigate("creatorApplySecond"){
            launchSingleTop = true // 중복 생성 방지

        }
    }
    fun buttonThirdNextOnClick(){
        shoppingApplication.navHostController.navigate("")
    }



    fun onFileUpload() {
        fileUploaded = true
    }
}