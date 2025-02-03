package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductImage
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "장바구니",
                        style = TextStyle(fontSize = 24.sp)
                    )
                },
                navigationIcon = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.arrow_back_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {
//                            navController.popBackStack()
                        },
                        borderNull = true,
                    )
                },
            )
        },
        bottomBar = {
            LikeLionFilledButton(
                text = "주문서 작성",
                containerColor = MainColor,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {

                }
            )
        }
    ) { paddingValues ->
        // Scroll 상태 생성
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            Text(
                text = "크리에이터 이름",
                modifier = Modifier.padding(8.dp)
            )
            LikeLionDivider()

            /*******************************상품 정보 카드*******************************/
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // 가로 중앙 정렬
                        modifier = Modifier
                            .padding(8.dp)
                    ) {

                            // 상품 이미지 (Placeholder)
                            LikeLionProductImage(
                                imgUrl = "",
                                size = 120.dp,
                                modifier = Modifier
                                    .padding(8.dp)

                            )

                        Text(
                            text = "수량 1개",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }


                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "상품 명",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold)
                        Text(
                            text = "상품 카테고리명",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "25,000원", style = MaterialTheme.typography.titleMedium)
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),

                        horizontalAlignment = Alignment.End, // 오른쪽 정렬
                        verticalArrangement = Arrangement.SpaceBetween, // 위아래로 간격 배치
                    ) {
                        LikeLionIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                            color = Color.Transparent,
                            iconBackColor = Color.Transparent,
                            iconButtonOnClick = {

                            },
                            borderNull = true,
                            modifier = Modifier.padding(top = 8.dp, end = 8.dp)
                        )

                        LikeLionOutlinedButton(
                            text = "수량 변경",
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(bottom = 25.dp, end = 8.dp) // 외부 패딩 제거
                                     ,
                            onClick = {

                            }
                        )
                    }

                }
            }

            LikeLionDivider()

          /*******************************배송비 라인*******************************/
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "배송비",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                    )
                Text(
                    text = "3,000원",
                    modifier = Modifier
                        .padding(end = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(modifier = Modifier.padding(16.dp))


            /*******************************상품금액,배송비,주문금액*******************************/
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "상품금액",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                            )
                        Text(
                            text = "25,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "배송비",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "3,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "주문금액",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "25,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))


            /*******************************주문 정보*******************************/

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column {
                    Text(
                        text = "주문정보",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )

                    LikeLionDivider(thickness = 3.dp, modifier = Modifier.padding(horizontal = 16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "총 수량",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "1개",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "총 상품 금액",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "25,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "배송비",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "3,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    LikeLionDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "총 주문금액",
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                .weight(1f),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = "28,000원",
                            modifier = Modifier
                                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }



                }
            }
        }
    }
}

@Preview
@Composable
fun ShoppingCartScreenPreview() {
    FinalProjectShoppingMallService3teamTheme {
        ShoppingCartScreen()
    }
}