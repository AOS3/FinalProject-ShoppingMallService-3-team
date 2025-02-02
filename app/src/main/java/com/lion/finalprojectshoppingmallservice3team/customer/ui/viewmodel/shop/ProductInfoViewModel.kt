package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val productService: ProductService
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
    // 주문서 작성화면 이동(바텀시트 -> 주문서 작성버튼)
    fun shopOrderSheetWriteButtonOnClick(){
        shoppingApplication.navHostController.navigate("shopOrderSheetWrite")
    }

    // 장바구니 담기 버튼
    fun shoppingCartButtonOnClick(){
        Toast.makeText(shoppingApplication, "상품을 장바구니에 담았습니다.", Toast.LENGTH_LONG).show()
        shoppingApplication.navHostController.navigate("shoppingCart")
    }



    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun onLikeClick() {
        _isFavorite.value = !_isFavorite.value
    }


    fun gettingProductData(productDocumentId: String, onProductLoaded: (ProductModel) -> Unit) {
        // 실제 데이터베이스에서 상품 정보를 가져오는 로직
        viewModelScope.launch {
            val product = productService.selectProductDataOneById(productDocumentId)
            // 상품이 존재하면 콜백으로 전달
            if (product != null) {
                onProductLoaded(product)
            } else {

            }
        }
    }
}
