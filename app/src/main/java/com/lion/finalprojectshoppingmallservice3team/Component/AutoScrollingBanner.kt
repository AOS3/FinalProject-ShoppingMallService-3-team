package com.lion.finalprojectshoppingmallservice3team.Component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import java.io.InputStream

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoScrollingBanner(
    bannerImages: List<Any>,
    autoScrollDelay: Long = 5000L,
    bannerHeight: Dp = 350.dp,
    imageSize: Dp = 300.dp,
    modifier: Modifier = Modifier,
    onBannerClick: (Int) -> Unit = {}
) {
    val pagerState = rememberPagerState()

    // Auto-scroll 기능 구현
    LaunchedEffect(pagerState) {
        while (true) {
            delay(autoScrollDelay)
            val nextPage = (pagerState.currentPage + 1) % bannerImages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // HorizontalPager로 배너 표시
        HorizontalPager(
            count = bannerImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onBannerClick(page) }, // 클릭 이벤트 처리
                contentAlignment = Alignment.Center
            ) {
                when (val item = bannerImages[page]) {
                    is Bitmap -> {
                        Image(
                            bitmap = item.asImageBitmap(),
                            contentDescription = "Banner $page",
                            modifier = Modifier.size(imageSize),
                            contentScale = ContentScale.Crop
                        )
                    }

                    is Int -> { // Drawable 리소스 ID일 경우
                        Image(
                            painter = painterResource(id = item),
                            contentDescription = "Banner $page",
                            modifier = Modifier.size(imageSize),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 페이지 인디케이터 추가
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                activeColor = Color.Gray,
                inactiveColor = Color.LightGray,
                indicatorShape = CircleShape,
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp,
                spacing = 4.dp
            )
        }
    }
}