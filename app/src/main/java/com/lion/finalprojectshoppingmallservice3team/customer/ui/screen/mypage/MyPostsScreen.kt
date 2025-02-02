package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFixedTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.MyPostsViewModel

@Composable
fun MyPostsScreen(myPostsViewModel: MyPostsViewModel = hiltViewModel()) {
    val tabTitlesWithCounts = listOf(
        "리뷰",
        "응원하기"
    )
    val selectedTabIndex = myPostsViewModel.selectedTabIndex
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                title = "내가 쓴 글",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    myPostsViewModel.navigationOnClick()
                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        padding = 10.dp,
                        borderNull = true
                    )

                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        padding = 10.dp,
                        borderNull = true
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White).padding(it)
        ) {
            LikeLionFixedTabs(
                modifier = Modifier.fillMaxWidth(),
                tabTitleWithCounts = tabTitlesWithCounts,
                selectedTabIndex = selectedTabIndex.value,
                onTabSelected = { index ->
                    selectedTabIndex.value = index
                },
                divider = {
                    HorizontalDivider(
                        thickness = 1.dp,  // 두꺼운 선
                        color = Color.LightGray // 커스텀 색상
                    )
                }
            )

            // 탭에 따른 화면 변경
            when (selectedTabIndex.value) {
                0 -> MyReviewScreen() // 리뷰 화면
                1 -> MyCheerScreen()  // 응원하기 화면
            }
        }
    }
}