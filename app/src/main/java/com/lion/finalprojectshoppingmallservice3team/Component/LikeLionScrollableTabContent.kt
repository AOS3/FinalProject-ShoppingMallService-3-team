package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.ShopViewModel

@Composable
fun LikeLionScrollableTabContent(selectedTabIndex: Int, shopViewModel: ShopViewModel) {
    val selectedTab = shopViewModel.selectedTabs.value.getOrNull(selectedTabIndex)
    when (selectedTab) {
        "전체 상품" -> {
            // 전체 상품 리스트 표시
            Text("전체 상품 화면")
        }
        "티셔츠" -> {
            // 티셔츠 상품 리스트 표시
            Text("티셔츠 화면")
        }
        "맨투맨" -> {
            // 맨투맨 상품 리스트 표시
            Text("맨투맨 화면")
        }
        "후드" -> {
            // 후드 상품 리스트 표시
            Text("후드 화면")
        }
        else -> {
            // 선택된 탭에 맞는 콘텐츠가 없으면 기본 화면 표시
            Text("선택된 탭 없음")
        }
    }
}
