package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.Product
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    // 뒤로가기 버튼
    fun navigationButtonClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    // 문의하기 화면 이동
    fun inquiryProductButtonOnClick(){
        shoppingApplication.navHostController.navigate("inquiryProductWrite")
    }
    // 취소/환불 FAQ 이동
    fun cancelRefundFAQButtonOnClick(){
        shoppingApplication.navHostController.navigate("cancelRefundFAQ")
    }



    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun onLikeClick() {
        _isFavorite.value = !_isFavorite.value
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