package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionImage
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.LoginMyPageViewModel

@Composable
fun LoginMyPageScreen(loginMyPageViewModel: LoginMyPageViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.Transparent,
                navigationIconImage = ImageVector.vectorResource(R.drawable.marcshop),
                navigationIconOnClick = {

                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        borderNull = true
                    )

                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        text = "",
                        iconSize = 30.dp,
                        size = 35.dp,
                        padding = 10.dp,
                        borderNull = true
                    )
                },

            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                LikeLionImage(
                    painter = painterResource(id = R.drawable.account_circle_24px),
                    contentScale = ContentScale.Crop,
                    isCircular = true, // 원형 설정
                    modifier = Modifier
                        .size(140.dp), // 크기 설정
                    onClick = {
                        // 클릭 이벤트 처리
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "닉네임 님, 반가워요!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "포인트",
                            fontSize = 14.sp,
                            color = Color.LightGray,
                        )

                        Text(
                            text = "0P",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    }
                }

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "최근 주문 * 배송중",
                            fontSize = 14.sp,
                            color = Color.LightGray,
                        )

                        Text(
                            text = "0",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    }
                }
            }

            Text(
                text = "쇼핑정보",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 14.sp,
                color = Color.LightGray,
            )

            Text(
                text = "구매내역",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "장바구니",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "내가 쓴 글",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "최근 본",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "고객센터",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 14.sp,
                color = Color.LightGray,
            )

            Text(
                text = "FAQ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {

                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "계정 설정",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {
                        loginMyPageViewModel.userSettingOnClick()
                    },
                fontSize = 16.sp,
                color = Color.Black,
            )

            Text(
                text = "로그아웃",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .clickable {
                        loginMyPageViewModel.logoutOnClick()
                    },
                fontSize = 16.sp,
                color = Color.Black,
            )
        }
    }
}