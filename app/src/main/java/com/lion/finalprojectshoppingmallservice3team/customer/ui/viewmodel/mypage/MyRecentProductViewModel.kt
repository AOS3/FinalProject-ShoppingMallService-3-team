package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyRecentProductViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 좋아요 상태를 관리하는 Map
    private val favoriteState = mutableStateOf(mutableMapOf<String, Boolean>())

    // 좋아요 버튼 클릭 시 호출
    fun onLikeClick(product: ProductModel) {
        // 좋아요 상태 토글
        val currentFavorite = favoriteState.value[product.productDocumentId] ?: false
        favoriteState.value[product.productDocumentId] = !currentFavorite
    }

    fun listItemImageOnClick(documentId:String) {
        shoppingApplication.navHostController.navigate("productInfo/${documentId}")
    }

}