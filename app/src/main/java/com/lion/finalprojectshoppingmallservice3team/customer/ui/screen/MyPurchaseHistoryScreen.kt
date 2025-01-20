package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionPurchaseCard
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionScrollableTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.MyPurchaseHistoryViewModel

@Composable
fun MyPurchaseHistoryScreen(myPurchaseHistoryViewModel: MyPurchaseHistoryViewModel = hiltViewModel()) {
    val tabTitles = listOf("전체", "구매확정")
    val selectedTabIndex = remember { mutableStateOf(0) } // 탭 상태 관리

    // 더미 데이터
    val purchaseData = remember {
        listOf(
            mapOf(
                "creatorName" to "크리에이터 이름1",
                "status" to "구매확정",
                "date" to "2/5 수요일",
                "deliveryStatus" to "배송 완료",
                "productImage" to R.drawable.kakao_login_logo, // 임시 이미지 리소스
                "productName" to "상품 1",
                "productCategory" to "상품 카테고리 1",
                "price" to "18,000원"
            ),
            mapOf(
                "creatorName" to "크리에이터 이름2",
                "status" to "구매대기",
                "date" to "2/4 화요일",
                "deliveryStatus" to "배송 중",
                "productImage" to R.drawable.kakao_login_logo,
                "productName" to "상품 2",
                "productCategory" to "상품 카테고리 2",
                "price" to "20,000원"
            ),
            mapOf(
                "creatorName" to "크리에이터 이름2",
                "status" to "구매확정",
                "date" to "2/3 월요일",
                "deliveryStatus" to "배송 완료",
                "productImage" to R.drawable.kakao_login_logo,
                "productName" to "상품 3",
                "productCategory" to "상품 카테고리 3",
                "price" to "15,000원"
            )
        )
    }

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.Transparent,
                title = "구매내역",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    myPurchaseHistoryViewModel.navigationOnClick()
                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp)
        ) {
            // 스크롤 가능한 탭
            LikeLionScrollableTabs(
                tabTitle = tabTitles,
                selectedTabIndex = selectedTabIndex.value,
                onTabSelected = { index ->
                    selectedTabIndex.value = index // 선택된 탭 상태 업데이트
                },
                modifier = Modifier.fillMaxWidth(),
            )

            LikeLionDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "2025.01.11 16:48",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    "더보기",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // 탭에 따라 데이터 필터링
            val filteredData = when (selectedTabIndex.value) {
                0 -> purchaseData // 전체 데이터
                1 -> purchaseData.filter { it["status"] == "구매확정" } // 구매확정 데이터만
                else -> purchaseData
            }

            // 필터링된 구매 리스트 출력
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                var previousCreatorName: String? = null // 이전 크리에이터 이름을 저장
                items(filteredData) { purchase ->
                    val currentCreatorName = purchase["creatorName"] as? String
                    val showCreatorName = currentCreatorName != previousCreatorName // 이전 이름과 비교
                    previousCreatorName = currentCreatorName // 현재 이름을 이전 이름으로 업데이트
                    LikeLionPurchaseCard(purchase = purchase, showCreatorName = showCreatorName)
                }
            }
        }
    }
}