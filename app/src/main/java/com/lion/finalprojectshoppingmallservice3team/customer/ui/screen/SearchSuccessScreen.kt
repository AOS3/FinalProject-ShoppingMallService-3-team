package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFixedTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSuccessScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.arrow_back_24px),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        iconButtonOnClick = {
                            navController.popBackStack()
                        },
                        borderNull = true,
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        iconButtonOnClick = {
//                            navController.navigate("search")
                        },
                        borderNull = true,
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_shopping_cart_24),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true,
                    )
                }
            )

        }
        )
    { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LikeLionFixedTabs(
                tabTitleWithCounts = searchViewModel.tabs, //: List<Pair<String, Int>>,
            selectedTabIndex = searchViewModel.selectedTabIndex.value,
            onTabSelected = { index -> searchViewModel.onTabSelected(index) },
            modifier = Modifier.fillMaxWidth(),
            )
            LikeLionDivider()

            when (searchViewModel.selectedTabIndex.value) {
                0 -> DisplayAllContent()
                1 -> DisplayCreatorContent()
                2 -> DisplayProductContent()
                else -> Text("잘못된 탭 선택")
            }

            Spacer(Modifier.height(16.dp))

        }
    }
}

// 예시 콘텐츠 컴포저블 함수들 (필요에 따라 수정 가능)
@Composable fun DisplayAllContent() { /* 전체 콘텐츠 */ }
@Composable fun DisplayCreatorContent() { /* 크리에이터 콘텐츠 */ }
@Composable fun DisplayProductContent() { /* 상품 콘텐츠 */ }

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SearchSuccessScreenPreView() {
//    FinalProjectShoppingMallService3teamTheme {
//        SearchSuccessScreen()
//    }
//}