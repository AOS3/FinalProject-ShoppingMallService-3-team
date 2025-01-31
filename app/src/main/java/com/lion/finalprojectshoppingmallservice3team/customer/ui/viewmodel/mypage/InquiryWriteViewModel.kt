package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.InquiryModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.InquiryService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.InquiryState
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.Tools
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class InquiryWriteViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val inquiryService: InquiryService
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    val inquiryTitleValue = mutableStateOf("")
    val inquiryContentValue = mutableStateOf("")

    // 여러 개의 이미지를 저장하는 상태 변수
    val imageBitmapInquiryState = mutableStateOf<Bitmap?>(null)
    val newFileName = mutableStateOf("image_${System.currentTimeMillis()}.jpg")

    // 이미지 삭제 버튼을 눌렀을 때
    fun deleteInquiryImageOnClick() {
        // 카메라나 앨범에서 가져온 사진이 있다면 삭제한다.
        imageBitmapInquiryState.value = null
    }

    val isButtonSubmitEnabled = mutableStateOf(false)

    fun inquiryWriteCloseOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun inquiryWriteCancelOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun updateSubmitButtonState() {
        // 조건: 아이디 중복 확인, 닉네임 중복 확인, 비밀번호 조건 만족, 약관 필수 체크
        isButtonSubmitEnabled.value =
            inquiryTitleValue.value.isNotBlank() &&
                    inquiryContentValue.value.isNotBlank()
    }

    fun inquiryWriteSuccessOnClick() {
        CoroutineScope(Dispatchers.Main).launch {
            // 로컬에 ImageView에 있는 이미지 데이터를 저장한다.
            Tools.saveBitmap(shoppingApplication, imageBitmapInquiryState.value!!)

            val work1 = async(Dispatchers.IO) {
                val filePath = shoppingApplication.getExternalFilesDir(null).toString()
                inquiryService.uploadImage("${filePath}/uploadTemp.jpg", newFileName.value)
            }
            work1.join()

            val inquiryModel = InquiryModel()
            inquiryModel.inquiryTitle = inquiryTitleValue.value
            inquiryModel.inquiryContent = inquiryContentValue.value
            inquiryModel.inquiriesImage = newFileName.value
            inquiryModel.inquiryCustomerUserId = shoppingApplication.loginCustomerModel.customerDocumentId
            inquiryModel.inquiryCustomerUserNickname = shoppingApplication.loginCustomerModel.customerUserNickName
            inquiryModel.inquiryCreatedAt = System.currentTimeMillis()
            inquiryModel.inquiriesisCreator = shoppingApplication.loginCustomerModel.isCreator
            inquiryModel.inquiryState = InquiryState.INQUIRY_STATE_SUBMIT

            val work2 = async(Dispatchers.IO) {
                inquiryService.addInquiryData(inquiryModel)
            }
            work2.join()

            Toast.makeText(shoppingApplication, "문의가 접수되었습니다.", Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.popBackStack()
        }
    }
}