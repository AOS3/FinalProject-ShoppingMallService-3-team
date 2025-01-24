package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginMyPageViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    val textNickname = mutableStateOf(shoppingApplication.loginCustomerModel.customerUserNickName)

    val isCreator = mutableStateOf(shoppingApplication.loginCustomerModel.isCreator)

    // 서버로 부터 이미지를 받아올 수 있는 Uri를 담을 상태 변수
    val imageUriState = mutableStateOf<Uri?>(null)

    // 보여줄 이미지 요소
    val showImage1State = mutableStateOf(false)
    val showImage2State = mutableStateOf(false)

    fun loadProfileImage() {
        val profileImage = shoppingApplication.loginCustomerModel.customerUserProfileImage

        // 값이 비어 있거나 기본값으로 설정된 경우
        if (profileImage.isNullOrEmpty() || profileImage == "none") {
            // 기본 이미지 표시
            showImage1State.value = true
        }
        // 외부 URL인 경우 (카카오 이미지)
        else if (profileImage.startsWith("http://") || profileImage.startsWith("https://")) {
            loadImageFromUrl(profileImage)
        }
        // Firebase Storage 파일 이름인 경우 (기존 이미지)
        else {
            loadImageFromFirebaseStorage(profileImage)
        }
    }

    // 외부 URL에서 이미지 로드
    fun loadImageFromUrl(url: String) {
        try {
            val imageUri = Uri.parse(url) // URL을 URI로 변환
            showImage2State.value = true
            imageUriState.value = imageUri
        } catch (e: Exception) {
            println("URL 이미지 로드 실패: ${e.localizedMessage}")
            showImage1State.value = true // 기본 이미지 표시
        }
    }

    // Firebase Storage에서 이미지 로드
    fun loadImageFromFirebaseStorage(fileName: String) {
        val storageRef = FirebaseStorage.getInstance().reference.child("profile_images/$fileName")
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            showImage2State.value = true
            imageUriState.value = uri
        }.addOnFailureListener { e ->
            println("Firebase Storage 이미지 로드 실패: ${e.localizedMessage}")
            showImage1State.value = true // 기본 이미지 표시
        }
    }

    fun logoutOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("logoutMyPage")
    }

    fun userSettingOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("userSetting")
    }

    fun myPostsOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myPosts")
    }

    fun myRecentOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myRecent")
    }

    fun myPerchaseHistoryOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("myPurchaseHistory")
    }

    fun inquiryOnClick(){
        shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
        shoppingApplication.navHostController.navigate("inquiryList")
    }
}