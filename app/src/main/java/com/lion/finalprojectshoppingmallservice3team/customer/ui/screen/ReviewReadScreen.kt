package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.a02_boardcloneproject.component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCircularBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductImage
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProfileImg
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewReadScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "리뷰 상세",
                        style = TextStyle(fontSize = 24.sp)
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
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

        ) { paddingValue ->
        // Scroll 상태 생성
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier.run {
                padding(paddingValue)
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    // 스크롤 기능
                    .verticalScroll(scrollState)
            },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 상단 이미지
            LikeLionProductImage(
                imgUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                size = 350.dp,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                // 상단의 하단 작은 이미지
                LikeLionProductImage(
                    imgUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                    size = 70.dp,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(horizontal = 8.dp)
                    )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 리뷰 작성자 정보 및 별점 표시
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LikeLionProfileImg(

                    imgUrl = "",
                    iconTint = Color.White,
                    profileBack = MainColor,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        // 작성자 아이디
                        Text(
                            text = "a****@gmail.com",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.weight(1f)) // 남은 공간을 밀어내기

                        Text(
                            text = "3일전"
                        )
                        LikeLionIconButton(
                            icon = Icons.Default.MoreVert,
                            text = "",
                            iconButtonOnClick = {

                            },
                            borderNull = true,
                        )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    // 별점
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        repeat(5) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.star_big_on),
                                contentDescription = null,
                                tint = Color.Yellow,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                }
            }



            Spacer(modifier = Modifier.height(8.dp))

            // 리뷰 내용 텍스트
            Text(
                text = "크리에이터에게 하고 싶은 말 아무거나 적어",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 상품 정보 표시 (이미지와 가격)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                ),
                    onClick = {
                        // 클릭시 상품으로 이동
                    }
                ) {
                    Row(
                    )
                    {
                        LikeLionProductImage(
                            imgUrl = "",
                            size = 70.dp,
                            contentScale = ContentScale.Fit
                        )
                        Column()
                        {
                            Text(
                                text = "상품 이름",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                )
                            )
                            Spacer(modifier = Modifier.padding(4.dp))

                            Text(
                                text = "20,000원",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ReviewReadScreenPreView() {
    FinalProjectShoppingMallService3teamTheme {
        ReviewReadScreen()
    }
}