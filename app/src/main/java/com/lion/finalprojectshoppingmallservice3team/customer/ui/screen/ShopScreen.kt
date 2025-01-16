package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDropDownMenu
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionScrollableTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.ShopViewModel

@Composable
fun ShopScreen(shopViewModel: ShopViewModel = hiltViewModel()) {
    val filteredProducts by shopViewModel.filteredProductList.collectAsState()

    LaunchedEffect(Unit) {
        shopViewModel.loadProductList()
    }

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = shopViewModel.topAppBarTitle.value,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.search_24px),
                        iconButtonOnClick = {
                            // 검색화면 이동
                        },
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        borderNull = true
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.shopping_cart_24px),
                        iconButtonOnClick = {
                            // 장바구니 이동
                        },
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        borderNull = true
                    )
                },
                backColor = Color.Transparent,

            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            // 칩 그룹
            LikeLionChipGroup(
                elements = shopViewModel.chipElements,
                chipStyle = shopViewModel.chipState,
                onChipClicked = { content, _, idx ->
                    shopViewModel.onCategoryClick(content)
                    shopViewModel.setEvent(idx)
                },
                modifier = Modifier.padding(10.dp)
            )

            // 선택된 카테고리에 맞는 탭 표시
            if (shopViewModel.selectedCategory.value != "전체 상품") {
                LikeLionScrollableTabs(
                    tabTitle = shopViewModel.selectedTabs.value,
                    selectedTabIndex = shopViewModel.selectedTabIndex.value,
                    onTabSelected = { index -> shopViewModel.onTabSelected(index) },
                    modifier = Modifier.fillMaxWidth()
                )
                LikeLionDivider()
            }

            // 체크박스와 드롭다운 메뉴
            FilterSection(shopViewModel)

            // 상품 리스트를 2열 그리드로 표시
            LikeLionProductList(
                productList = filteredProducts,
                onProductClick = { /* 상품 클릭 시 처리 */ },
                onLikeClick = { shopViewModel.onLikeClick(it) },
                columns = 2
            )
        }
    }
}

@Composable
fun FilterSection(shopViewModel: ShopViewModel) {
    Row(modifier = Modifier.padding(vertical = 8.dp).padding(start = 10.dp,end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        LikeLionCheckBox(
            text = "품절 제외",
            checkedValue = shopViewModel.excludeSoldOut,
            onCheckedChange = {

            },
            modifier = Modifier,
            textModifier = Modifier.padding(start = 0.dp)
        )
        LikeLionCheckBox(
            text = "한정판만",
            checkedValue = shopViewModel.limitedEditionOnly,
            onCheckedChange = {

            },
            modifier = Modifier,
            textModifier = Modifier.padding(start = 0.dp)
        )

        // 드롭다운 메뉴를 오른쪽에 배치하기 위한 Row
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
            LikeLionDropDownMenu(
                options = listOf("인기순", "최신순", "낮은 가격순", "높은 가격순", "리뷰순"),
                selectedOption = shopViewModel.selectedSortOption.value,
                onOptionSelected = { option -> shopViewModel.selectedSortOption.value = option }
            )
        }
    }
}
