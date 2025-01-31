package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.Product
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class InquiryProductWriteViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    // TopAppBar Title
    val topAppBarTitle = mutableStateOf("상품 문의하기")

    // 취소버튼클릭
    fun cancelButtonClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    // close 버튼 클릭
    fun closeButtonClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    fun onSubmitInquiryProduct() {
        shoppingApplication.navHostController.navigate("inquiryProductList")
        // 여기서 실제로 데이터 처리 후 토스트 메시지 띄우기
        Toast.makeText(shoppingApplication, "문의가 접수되었습니다.", Toast.LENGTH_LONG).show()

    }

    val isButtonEnabled = mutableStateOf(false)

    // 이름
    val textFieldInquiryProductNameValue = mutableStateOf("")
    // 연락처
    val textFieldInquiryProductPhoneNumberValue = mutableStateOf("")
    // 제목
    val textFieldInquiryProductTitleValue = mutableStateOf("")
    // 내용
    val textFieldInquiryProductContentValue = mutableStateOf("")
    // 파일
    val textFieldInquiryFileContentValue = mutableStateOf("")


    // 고민중
//    // 에러 메시지
//    val textFieldInquiryProductNameErrorText = mutableStateOf("")
//    val textFieldInquiryProductPhoneNumberErrorText = mutableStateOf("")
//    val textFieldInquiryProductTitleErrorText = mutableStateOf("")
//    val textFieldInquiryProductContentErrorText = mutableStateOf("")
//
//    // 에러 상태
//    val textFieldInquiryProductNameError = mutableStateOf(false)
//    val textFieldInquiryProductPhoneNumberError = mutableStateOf(false)
//    val textFieldInquiryProductTitleError = mutableStateOf(false)
//    val textFieldInquiryProductContentError = mutableStateOf(false)



    // 체크박스 상태
    val isCheckBoxChecked = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value =
            textFieldInquiryProductTitleValue.value.isNotBlank() &&
                textFieldInquiryProductNameValue.value.isNotBlank() &&
                    textFieldInquiryProductContentValue.value.isNotBlank() &&
                    textFieldInquiryProductPhoneNumberValue.value.isNotBlank() &&
                    isCheckBoxChecked.value

    }

    fun gettingProductData(productDocumentId: String, onProductLoaded: (Product) -> Unit) {
        // 실제 데이터베이스에서 상품 정보를 가져오는 로직
        val product = Storage.products.find { it.productDocumentId == productDocumentId }

        // 상품이 존재하면 콜백으로 전달
        if (product != null) {
            onProductLoaded(product)
        } else {
            // 상품이 없을 경우 기본값 반환 (에러 처리도 가능)
            onProductLoaded(
                Product(
                    productDocumentId = productDocumentId,
                    name = "Unknown Product",
                    price = 0,
                    imageUrl = "",
                    description = "Unknown Description",
                    creator = "Unknown Creator",
                    category = "Unknown Category",
                    subCategory = "Unknown SubCategory",
                    isFavorite = false,
                    stockQuantity = 0,
                    isLimited = false,
                    rating = 0f,
                    reviewCount = 0,
                )
            )
        }
    }
}