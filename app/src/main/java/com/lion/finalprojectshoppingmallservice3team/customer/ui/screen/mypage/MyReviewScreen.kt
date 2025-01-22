package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionMyReviewItem
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.MyReviewViewModel

@Composable
fun MyReviewScreen(myReviewViewModel: MyReviewViewModel = hiltViewModel()) {
    val dummyReviews: SnapshotStateList<Map<String, *>> = remember {
        mutableStateListOf(
            mapOf(
                "creatorName" to "크리에이터 1",
                "productName" to "상품명 1",
                "date" to "2025.01.10",
                "reviewContent" to "정말 훌륭한 제품이에요! 강력 추천합니다.",
                "rating" to 5
            ),
            mapOf(
                "creatorName" to "크리에이터 2",
                "productName" to "상품명 2",
                "date" to "2025.01.08",
                "reviewContent" to "괜찮지만 약간 개선이 필요할 것 같아요.",
                "rating" to 4
            ),
            mapOf(
                "creatorName" to "크리에이터 2",
                "productName" to "상품명 3",
                "date" to "2025.01.05",
                "reviewContent" to "가격 대비 품질이 괜찮습니다.",
                "rating" to 3
            )
        )
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 10.dp)
        ) {
            var previousCreatorName: String? = null // 이전 크리에이터 이름을 저장
            // 리스트 렌더링
            LikeLionList(
                dataList = dummyReviews,
                rowComposable = { review ->
                    val currentCreatorName = review["creatorName"] as? String
                    val showCreatorName = currentCreatorName != previousCreatorName // 이전 이름과 비교
                    previousCreatorName = currentCreatorName // 현재 이름을 이전 이름으로 업데이트
                    LikeLionMyReviewItem(review = review, showCreatorName = showCreatorName)
                },
                onRowClick = {
                    // 항목 클릭 이벤트 처리
                    println("리뷰 클릭됨!")
                }
            )
        }
    }
}