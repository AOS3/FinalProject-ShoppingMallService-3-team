package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lion.finalprojectshoppingmallservice3team.Component.AutoScrollingBanner
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCircularBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionHomeCircleBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProfileImg
import com.lion.finalprojectshoppingmallservice3team.Component.WeeklyCreator
import com.lion.finalprojectshoppingmallservice3team.Component.YouTubePlayer
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.home.HomeViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    windowInsetsController: WindowInsetsControllerCompat,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    // 현재 월과 주 계산
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1
    val currentWeek = calendar.get(Calendar.WEEK_OF_MONTH)

    val buttonItems = homeViewModel.buttonItems


    LaunchedEffect(Unit) {
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Bottom),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = homeViewModel.topAppBarTitle.value,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        color = Color.Transparent,
                        iconButtonOnClick = {
                            homeViewModel.searchOnClick()
                        },
                        borderNull = true,
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_shopping_cart_24),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {

                        },
                        borderNull = true,
                    )
                },
            )
        },

        ) { paddingValues ->
        // LocalContext.current는 컴포저블 내부에서 호출
        val context = LocalContext.current
        val screenHeightPx = remember {
            context.resources.displayMetrics.heightPixels.toFloat()
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // AutoScrollingBanner 호출
            AutoScrollingBanner(
                bannerImages = listOf(
                    R.drawable.marcshop_logo,
                    "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                    R.drawable.product
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(buttonItems) { item ->
                    LikeLionHomeCircleBox(item) { route ->
                        homeViewModel.shoppingApplication.navHostController.navigate(route)
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "그룹이름",
                    fontWeight = FontWeight.Bold
                )
            }

            LazyRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(8) { index ->
                    LikeLionProfileImg(
                        imgUrl = "",
                        modifier = Modifier.padding(horizontal = 5.dp),
                        iconTint = Color.White,
                        profileBack = MainColor,
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(

            ) {
                // 상단 텍스트 (동적으로 월과 주 표시)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "${currentMonth}월 ${currentWeek}주차 인기 크리에이터 랭킹",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray,
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "WEEKLY RANKING",
                            style = TextStyle(
                                fontSize = 26.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        )

                        Spacer(modifier = Modifier.weight(1f)) // 텍스트를 오른쪽으로 밀기

                        Text(
                            text = "See More",
                            style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .clickable {
                                    homeViewModel.seeMoreOnClick()
                                } // 클릭 이벤트 처리
                        )
                    }
                }
                WeeklyCreator(
                    rank = "1",
                    title = "허블사무소",
                    subtitle = "유튜버",
                    imageUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                    items = mutableListOf(
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg"
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
            ) {
                Text(
                    text = "최애는 지금 영업 중",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = "CAST ON-AIR",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)

                )

                LikeLionProfileImg(
                    imgUrl = "",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp),
                    iconTint = Color.White,
                    profileBack = MainColor,
                )

                // YouTubePlayer 가시성 체크 및 동작 제어
                val isVisible = remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(250.dp)
                        .onGloballyPositioned { coordinates ->
                            val positionY = coordinates.positionInWindow().y
                            val heightPx = coordinates.size.height.toFloat()

                            // 화면에 최소 30% 이상 보이는 경우를 기준으로 가시성 판단
                            isVisible.value =
                                positionY + heightPx * 0.3f > 0 && positionY < screenHeightPx * 0.7f
                        }
                ) {
                    YouTubePlayer(
                        videoId = "911eCyHPlHs",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, end = 16.dp),
                        isPlaying = isVisible.value // 가시성에 따라 재생/일시정지 제어
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HomeScreenPreView() {
//    FinalProjectShoppingMallService3teamTheme {
//        HomeScreen()
//    }
//}