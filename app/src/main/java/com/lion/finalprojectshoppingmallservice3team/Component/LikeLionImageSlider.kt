package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R

@Composable
fun LikeLionImageSlider(imageUrls: List<String>) {
    val pagerState = rememberPagerState(initialPage = 0) {
        imageUrls.size.takeIf { it > 0 } ?: 1
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        // 이미지 슬라이드
        HorizontalPager(state = pagerState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF6F6F6))
            ) {
                LikeLionProductImage(
                    modifier = Modifier.align(Alignment.Center),
                    imgUrl = if (imageUrls.isNotEmpty() && imageUrls[page].isNotBlank()) {
                        imageUrls[page]
                    } else {
                        "" // 빈 URL 처리
                    },
                    size = 300.dp,
                    fixedImage = R.drawable.marcshop_logo, // 기본 이미지
                    contentScale = ContentScale.Crop
                )
            }
        }

        // 인디케이터는 슬라이더와 별도로 위치하도록 Box 밖에서 배치
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter) // 하단 가운데 정렬
                .fillMaxWidth()
                .padding(16.dp), // 슬라이더 높이에 맞춤
            contentAlignment = Alignment.Center // 아래쪽 가운데 정렬
        ) {
            DotsIndicator(
                totalDots = imageUrls.size.takeIf { it > 0 } ?: 1,
                selectedIndex = pagerState.currentPage
            )
        }
    }
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    dotSize: Int = 8, // 기본 크기
    selectedDotWidth: Int = 24, // 선택된 점의 가로 크기
    selectedDotHeight: Int = 8, // 선택된 점의 세로 크기 (일자 형태)
    dotSpacing: Int = 6,
    selectedDotColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f), // 선택된 색 (진한 회색)
    unselectedDotColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f) // 선택되지 않은 점 색 (연한 회색)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dotSpacing.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until totalDots) {
            Box(
                modifier = Modifier
                    .then(
                        if (i == selectedIndex) {
                            Modifier
                                .width(selectedDotWidth.dp)
                                .height(selectedDotHeight.dp)
                        } else {
                            Modifier
                                .size(dotSize.dp)
                        }
                    )
                    .clip(if (i == selectedIndex) RoundedCornerShape(50) else CircleShape) // 선택된 점은 일자 형태
                    .background(if (i == selectedIndex) selectedDotColor else unselectedDotColor)
            )
        }
    }
}