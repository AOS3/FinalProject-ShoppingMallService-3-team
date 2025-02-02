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
import androidx.lifecycle.viewModelScope
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.CategoryMapping
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.ProductCategory
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val productService: ProductService
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    fun listItemImageOnClick(documentId:String) {
        shoppingApplication.navHostController.navigate("productInfo/${documentId}")
    }

    // TopAppBar Title
    val topAppBarTitle = mutableStateOf("Shop")

    // 좋아요 상태를 관리하는 Map
    private val _favoriteState = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favoriteState = _favoriteState.asStateFlow()

    //**************** 칩 *********************
    val chipElements: SnapshotStateList<ChipState> = mutableStateListOf(
        ChipState(ProductCategory.PRODUCT_CATEGORY_ALL.str, mutableStateOf(true)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_CLOTHING.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_GOODS.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_FASHION_ACCESSORIES.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_CUSHION_FABRIC.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_OFFICE_SUPPLIES.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_PHONE_ACCESSORIES.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_STICKER_PAPER.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_LIVING.str, mutableStateOf(false)),
        ChipState(ProductCategory.PRODUCT_CATEGORY_TOY_HOBBY.str, mutableStateOf(false)),
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
    private val categoryTabs: Map<String, List<String>> = buildMap {
        // "전체 상품" 카테고리는 직접 추가
        put(ProductCategory.PRODUCT_CATEGORY_ALL.str, listOf("전체 상품"))

        // CategoryMapping을 기반으로 나머지 카테고리 추가
        CategoryMapping.categoryToSubCategory.forEach { (category, subCategories) ->
            put(category, listOf("전체") + subCategories) // "전체"를 포함해서 리스트 생성
        }
    }

    var selectedCategory = mutableStateOf(ProductCategory.PRODUCT_CATEGORY_ALL.str)
    var selectedTabIndex = mutableStateOf(0)
    val selectedTabs = mutableStateOf(categoryTabs[ProductCategory.PRODUCT_CATEGORY_ALL.str]!!)

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
    private val _productList = mutableStateOf(mutableListOf<ProductModel>())
    private val _filteredProductList = MutableStateFlow(listOf<ProductModel>())
    val filteredProductList: StateFlow<List<ProductModel>> = _filteredProductList.asStateFlow()

    // 상품 목록 로드
    fun loadProductList() {
//        _productList.value = Storage.products
//        filterProducts()
        viewModelScope.launch {
            val productList = productService.selectAllProductData(selectedCategory.value)
            _productList.value = productList
            filterProducts()
        }
    }

    fun filterProducts() {
        val category = selectedCategory.value
        val subCategory = selectedTabs.value[selectedTabIndex.value]

        // 상품 필터링 로직
        var filteredList = _productList.value.filter {
            // '전체 상품' 카테고리일 때는 모든 상품을 포함
            if (category == ProductCategory.PRODUCT_CATEGORY_ALL.str) {
                true // 전체 상품 카테고리일 땐 모든 상품을 포함
            } else {
                (it.productCategory == category || category == ProductCategory.PRODUCT_CATEGORY_ALL.str) &&
                        (it.productSubCategory == subCategory || subCategory == "전체")
            }
        }

        // 품절 제외
        if (excludeSoldOut.value) {
            filteredList = filteredList.filter { it.productManagementAllQuantity > 0 }
        }

        // 한정판만
        if (limitedEditionOnly.value) {
            filteredList = filteredList.filter { !it.productLimitedSalesPeriod.isBlank() }
        }

        // 🔥 정렬 로직 추가
        filteredList = when (selectedSortOption.value) {
            "인기순" -> filteredList.sortedByDescending { it.productSalesCount }
            "최신순" -> filteredList.sortedByDescending { it.productCreatedAt }
            "낮은 가격순" -> filteredList.sortedBy { it.productPrice }
            "높은 가격순" -> filteredList.sortedByDescending { it.productPrice }
            "리뷰순" -> filteredList.sortedByDescending { it.productReviewCount }
            else -> filteredList
        }

        _filteredProductList.value = filteredList

    }


    fun onLikeClick(productModel: ProductModel) {
        val currentFavorite = favoriteState.value[productModel.productDocumentId] ?: false
        _favoriteState.value = favoriteState.value.toMutableMap().apply {
            this[productModel.productDocumentId] = !currentFavorite
        }
        filterProducts() // 변경된 좋아요 상태 반영
    }
}
