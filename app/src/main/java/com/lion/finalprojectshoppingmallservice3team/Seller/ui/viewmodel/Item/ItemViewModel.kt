package com.lion.finalprojectshoppingmallservice3team.Seller.ui.viewmodel.Item

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    @ApplicationContext context: Context,
): ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    var selectedCategory by mutableStateOf("전체카테고리")
        private set

    var selectedVisibility by mutableStateOf("공개여부")
        private set

    // 탭 상태 관리
    private var _selectedTabIndex = mutableStateOf(0)
    val selectedTabIndex: State<Int> = _selectedTabIndex

    // X 아이콘 누르면 이동[임시]
    fun backOnClick(){
        shoppingApplication.navHostController.navigate("shop")
    }


    // 이벤트 핸들러
    fun onTabSelected(index: Int) {
        _selectedTabIndex.value = index
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
    }

    fun onVisibilitySelected(visibility: String) {
        selectedVisibility = visibility
    }


    fun onAddProductClick() {
        // 상품 추가 화면으로 네비게이션
        shoppingApplication.navHostController.navigate("addProduct")
    }

}