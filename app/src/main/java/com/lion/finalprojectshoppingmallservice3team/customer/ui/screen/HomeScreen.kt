package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.collection.intIntMapOf
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ManageSearch
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lion.a02_boardcloneproject.component.LikeLionIconButton
import com.lion.a02_boardcloneproject.component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.AutoScrollingBanner
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCircularBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProfileImg
import com.lion.finalprojectshoppingmallservice3team.Component.WeeklyCreator
import com.lion.finalprojectshoppingmallservice3team.Component.YouTubePlayer
import com.lion.finalprojectshoppingmallservice3team.R
import androidx.compose.foundation.lazy.items
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.HomeViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val circularBoxItems = homeViewModel.circularBoxItems


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                    text = "Home",
                    fontWeight = FontWeight.Bold
                )},
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        iconButtonOnClick = {
                            navController.navigate("search")
                        },
                        borderNull = true,
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_shopping_cart_24),
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        iconButtonOnClick = {

                        },
                        borderNull = true,
                    )
                }
            )
        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // AutoScrollingBanner 호출
            AutoScrollingBanner(
                bannerImages = listOf(
                    R.drawable.marcshop_logo
                ),
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(circularBoxItems) { item ->
                    LikeLionCircularBox(
                        imgUrl = item.imgUrl,
                        text = item.text,
                        iconTint = Color.White,
                        profileBack = MainColor,
                        modifier = Modifier
                            .clickable {
                            handleCircularBoxClick(item.targetAction, navController)
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "그룹이름"
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
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = "최애는 지금 영업 중"
                )
                Text(
                    text = "CAST ON-AIR",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                LikeLionProfileImg(
                    imgUrl = "",
                    modifier = Modifier.padding(5.dp),
                    iconTint = Color.White,
                    profileBack = MainColor,
                )
//                    YouTubePlayer(
//                        videoId = "911eCyHPlHs",
//                        modifier = Modifier
//                            .padding(vertical = (16.dp))
//                            .fillMaxSize()
//                            .height(250.dp),
//                    )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// 클릭 이벤트 처리 함수
private fun handleCircularBoxClick(targetAction: String, navController: NavController) {
    when (targetAction) {
        "action1" -> navController.navigate("search")
        "action2" -> navController.navigate("screen2")
        "action3" -> navController.navigate("screen3")
        "action4" -> navController.navigate("screen4")
        "action5" -> navController.navigate("screen5")
        else -> {
            // 기본 동작 또는 에러 처리
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