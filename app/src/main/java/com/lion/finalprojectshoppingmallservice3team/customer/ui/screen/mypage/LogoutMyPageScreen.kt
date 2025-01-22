package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.BottomNavigationItemData
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionAlertDialog
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBottomNavigation
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.LogoutMyPageViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun LogoutMyPageScreen(logoutMyPageViewModel: LogoutMyPageViewModel = hiltViewModel()) {
    val bottomNavItems = listOf(
        BottomNavigationItemData(
            icon = Icons.Default.Home,
            label = "Home",
            route = "home"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Mood,
            label = "Creator",
            route = "creator"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Star,
            label = "My",
            route = "myfavorite"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Shop,
            label = "Shop",
            route = "shop"
        ),
        BottomNavigationItemData(
            icon = Icons.Default.Person,
            label = "My Page",
            route = "logoutmypage"
        )
    )

    Scaffold(
        bottomBar = {
            LikeLionBottomNavigation(
                navController = logoutMyPageViewModel.shoppingApplication.navHostController,
                items = bottomNavItems,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp),
        ) {
            Text(
                text = "MAKE IT REAL",
                modifier = Modifier.fillMaxWidth().padding(top = 100.dp, bottom = 20.dp),
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "상상하는 모든것이 현실이 되는 마크샵",
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LikeLionFilledButton(
                    text = "로그인",
                    modifier = Modifier.padding(end = 5.dp),
                    containerColor = SubColor,
                    contentColor = MainColor,
                    onClick = {
                        logoutMyPageViewModel.loginOnClick()
                    }
                )

                LikeLionFilledButton(
                    text = "회원가입",
                    modifier = Modifier.padding(start = 5.dp),
                    containerColor = SubColor,
                    contentColor = MainColor,
                    onClick = {
                        logoutMyPageViewModel.userJoinOnClick()
                    }
                )
            }

            Text(
                text = "쇼핑정보",
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                fontSize = 14.sp,
                color = Color.LightGray
            )

            Text(
                text = "장바구니",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "고객센터",
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                fontSize = 14.sp,
                color = Color.LightGray
            )

            Text(
                text = "FAQ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "사이트 이용 시 유의사항",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {
                        logoutMyPageViewModel.siteInfoOnClick()
                    },
                fontSize = 16.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "버전 2.1.1",
                    fontSize = 14.sp,
                    color = Color.LightGray,
                )
            }

            // 사이트 이용 시 유의사항을 띄우는 다이얼로그
            LikeLionAlertDialog(
                showDialogState = logoutMyPageViewModel.showDialogSiteInfoState,
                confirmButtonTitle = "확인",
                confirmButtonOnClick = {
                    logoutMyPageViewModel.showDialogSiteInfoState.value = false
                },
                confirmbuttonModifier = Modifier.fillMaxWidth(),
                title = "사이트 이용 시 유의사항",
                titleModifier = Modifier.fillMaxWidth(),
                titleAlign = TextAlign.Center,
                text = "이 사이트의 콘텐츠에 대한 모든 권리와 의무는\n" +
                        "저작권자 또는 제공처에 있습니다.\n" +
                        "이를 무단 사용 및 도용할 경우 저작권법 등에 \n" +
                        "따라 법적 책임을 질 수 있습니다.\n" +
                        "크리에이터의 저작물(콘텐츠)에 대한 문제\n" +
                        "발생 시 마크샵은 이에 대한 책임을 지지\n" +
                        "않습니다.",
                textAlign = TextAlign.Center,
                textModifier = Modifier.fillMaxWidth(),
            )


        }
    }
}