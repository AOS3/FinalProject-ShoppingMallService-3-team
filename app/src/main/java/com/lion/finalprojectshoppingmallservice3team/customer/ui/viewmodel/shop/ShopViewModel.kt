package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.Component.Product
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.data.Storage
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    fun listItemImageOnClick(documentId:String) {
        shoppingApplication.navHostController.navigate("productInfo/${documentId}")
    }

    // TopAppBar Title
    val topAppBarTitle = mutableStateOf("Shop")

    // 좋아요 상태를 관리하는 Map
    private val favoriteState = mutableStateOf(mutableMapOf<String, Boolean>())

    //**************** 칩 *********************
    val chipElements: SnapshotStateList<ChipState> = mutableStateListOf(
        ChipState("전체 상품", mutableStateOf(true)),
        ChipState("의류", mutableStateOf(false)),
        ChipState("굿즈", mutableStateOf(false)),
        ChipState("패션잡화", mutableStateOf(false)),
        ChipState("쿠션/패브릭", mutableStateOf(false)),
        ChipState("문구/오피스", mutableStateOf(false)),
        ChipState("폰액세서리", mutableStateOf(false)),
        ChipState("스티커/지류", mutableStateOf(false)),
        ChipState("리빙", mutableStateOf(false)),
    )

    val chipState = ChipStyle(
        selectedColor = Color(0xFFA16DEB),
        selectedTextColor = Color.White,
        unselectedColor = Color.White,
        unselectedTextColor = Color.Black,
        chipTextStyle = Typography.bodySmall,
        chipModifier = Modifier.padding(start = 15.dp, top = 8.dp, bottom = 8.dp, end = 15.dp)
    )

    // 칩 클릭 시 선택 상태 변경
    fun setEvent(idx: Int) {
        chipElements.forEachIndexed { index, chipState ->
            chipState.isSelected.value = index == idx
        }
    }

    //**************** 탭 *********************
    private val categoryTabs = mapOf(
        "전체 상품" to listOf("전체 상품"),
        "의류" to listOf("전체", "티셔츠", "맨투맨", "후드"),
        "굿즈" to listOf("전체", "아크릴굿즈", "키링", "거울/핀버튼"),
        "패션잡화" to listOf("전체", "가방"),
        "쿠션/패브릭" to listOf("전체", "쿠션/방석"),
        "문구/오피스" to listOf("전체", "마우스패드"),
        "폰액세서리" to listOf("전체", "스마트톡"),
        "스티커/지류" to listOf("전체", "카드"),
        "리빙" to listOf("전체", "머그컵")
    )

    var selectedCategory = mutableStateOf("전체 상품")
    var selectedTabIndex = mutableStateOf(0)
    val selectedTabs = mutableStateOf<List<String>>(categoryTabs["전체 상품"]!!)

    // 카테고리 선택
    fun onCategoryClick(category: String) {
        selectedCategory.value = category
        selectedTabs.value = categoryTabs[category] ?: listOf("전체 상품")
        selectedTabIndex.value = 0
        filterProducts()
    }

    // 탭 선택
    fun onTabSelected(index: Int) {
        selectedTabIndex.value = index
        filterProducts()
    }

    //**************** 체크박스 *********************
    val excludeSoldOut = mutableStateOf(false)
    val limitedEditionOnly = mutableStateOf(false)

    fun onExcludeSoldOutChange() {
        filterProducts()
    }

    fun onLimitedEditionChange() {
        filterProducts()
    }

    //**************** 드롭다운 *********************
    var selectedSortOption = mutableStateOf("인기순")


    //******************상품***********

    //**************** 상품 필터링 *********************
    private val _productList = mutableStateOf(listOf<Product>())
    private val _filteredProductList = MutableStateFlow(listOf<Product>())
    val filteredProductList: StateFlow<List<Product>> = _filteredProductList.asStateFlow()

    // 상품 목록 로드
    fun loadProductList() {
        _productList.value = Storage.products
        filterProducts()
    }

    fun filterProducts() {
        val category = selectedCategory.value
        val subCategory = selectedTabs.value[selectedTabIndex.value]

        var filteredList = if (category == "전체 상품") {
            _productList.value
        } else {
            _productList.value.filter {
                (it.category == category || category == "전체 상품") &&
                        (it.subCategory == subCategory || subCategory == "전체")
            }
        }

        // 품절 제외
        if (excludeSoldOut.value) {
            filteredList = filteredList.filter { it.stockQuantity > 0 }
        }

        // 한정판만
        if (limitedEditionOnly.value) {
            filteredList = filteredList.filter { it.isLimited }
        }

        // 좋아요 상태를 동적으로 반영
        _filteredProductList.value = filteredList.map { product ->
            product.copy(isFavorite = favoriteState.value[product.productDocumentId] ?: false)
        }
    }


    // 좋아요 버튼 클릭 시 호출
    fun onLikeClick(product: Product) {
        // 좋아요 상태 토글
        val currentFavorite = favoriteState.value[product.productDocumentId] ?: false
        favoriteState.value[product.productDocumentId] = !currentFavorite

        // 필터링된 목록 갱신
        filterProducts()
    }
}