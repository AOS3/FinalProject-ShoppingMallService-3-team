package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.shop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldInputType
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductImage
import com.lion.finalprojectshoppingmallservice3team.Component.PaymentButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor



/***********************************수정사항**************************************/
// 상품이 여러개 들어갈 수 있게 해야함
// 체크박스 한번 풀리면 다시 안됨
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopOrderSheetWrite() {

    // 상품 정보 드롭다운 상태
    var productExpanded by remember { mutableStateOf(true) }
    // 배송지 정보 드롭다운 상태
    var deliveryExpanded by remember { mutableStateOf(true) }
    // 포인트 사용 드롭다운 상태
    var couponExpanded by remember { mutableStateOf(true) }
    // 총 주문금액 드롭다운 상태
    var orderAmountExpanded by remember { mutableStateOf(true) }
    // 결제방법 드롭다운 상태
    var paymentAmountExpanded by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "주문서 작성",
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
                text = "결제하기",
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

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {

            /***********************************상품 정보**************************************/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 드롭다운 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { productExpanded = !productExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "상품 정보",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f),

                        )
                    Text(
                        text = "총 주문금액 14,500",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Icon(
                        imageVector = if (productExpanded) ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
                        else ImageVector.vectorResource(R.drawable.arrow_drop_down_24px),
                        contentDescription = null
                    )
                }

                // 드롭다운 내용 (애니메이션 처리)
                AnimatedVisibility(visible = productExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {

                        Row {
                            Text(
                                text = "크리에이터 이름",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                        LikeLionDivider(paddingTop = 4.dp)

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "마크샵 배송",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Spacer(Modifier.padding(horizontal = 4.dp))

                            Icon(
                                painter = painterResource(R.drawable.deliverybox),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }

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
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "상품 카테고리명",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = "25,000원",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Text(
                                        text = "25,000원",
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(bottom = 8.dp)

                                    )
                                }
                            }
                        }
                        LikeLionDivider()

                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "배송비",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,

                                )

                            Spacer(modifier = Modifier.padding(horizontal = 2.dp))

//                            Icon(
//                                imageVector = ImageVector.vectorResource(R.drawable.error_outline_24),
//                                contentDescription = null,
//                                modifier = Modifier.clickable {
//
//                                }
//                            )

                            Text(
                                text = "100,000 원 이상 구매시 무료",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                            )

                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "3,000원",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                            )


                        }
                    }
                }
            }

            LikeLionDivider(thickness = 6.dp)


            /***********************************배송지 정보**************************************/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 드롭다운 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { deliveryExpanded = !deliveryExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "배송지 정보", style = MaterialTheme.typography.titleLarge)
                    Icon(
                        imageVector = if (deliveryExpanded) ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
                        else ImageVector.vectorResource(R.drawable.arrow_drop_down_24px),
                        contentDescription = null
                    )
                }

                // 드롭다운 내용 (애니메이션 처리)
                AnimatedVisibility(visible = deliveryExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Row(){

                            LikeLionCheckBox(
                                text = "주문자와 동일",
                                fontSize = 14.sp,
                                textModifier = Modifier.scale(1f),
                                modifier = Modifier.padding(start = 0.dp)
                            )

                            Spacer(modifier = Modifier.padding(horizontal = 2.dp))

                            LikeLionCheckBox(
                                text = "배송지 저장",
                                fontSize = 14.sp,
                                textModifier = Modifier.scale(1f),
                                modifier = Modifier.padding(start = 0.dp)
                            )

                        }




                        Text(
                            text = "수령인",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        LikeLionOutlinedTextField(
                            label = "수령인",
                            placeHolder = "이름을 입력주세요",
                            inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "연락처1",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        LikeLionOutlinedTextField(
                            label = "연락처1",
                            placeHolder = "-없이 숫자만 입력하세요",
                            inputType = LikeLionOutlinedTextFieldInputType.NUMBER,
                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "연락처2",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        LikeLionOutlinedTextField(
                            label = "연락처2",
                            placeHolder = "-없이 숫자만 입력하세요",
                            inputType = LikeLionOutlinedTextFieldInputType.NUMBER,
                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "배송지",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LikeLionOutlinedTextField(
                                label = "우편번호",
                                placeHolder = "우편번호",
                                inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                                modifier = Modifier
                                    .weight(1f)
                            )

                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                            LikeLionFilledButton(
                                text = "우편번호 검색",
                                modifier = Modifier
                                    .wrapContentSize(),
                                onClick = {/* 우편번호 검색 */ }

                            )
                        }
                        LikeLionOutlinedTextField(
                            label = "주소",
                            placeHolder = "주소",
                            inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                            modifier = Modifier
                                .fillMaxWidth(),

                            )
                        LikeLionOutlinedTextField(
                            label = "상세 주소",
                            placeHolder = "상세 주소를 입력해 주세요",
                            inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )

                        Spacer(modifier = Modifier.height(8.dp))


                        Text(
                            text = "배송 메모",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        LikeLionOutlinedTextField(
                            label = "배송 메모",
                            placeHolder = "배송 메모를 입력해 주세요",
                            inputType = LikeLionOutlinedTextFieldInputType.TEXT,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }
            }

            LikeLionDivider(thickness = 6.dp)


            /***********************************포인트 사용**************************************/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 드롭다운 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { couponExpanded = !couponExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "포인트 사용", style = MaterialTheme.typography.titleLarge)
                    Icon(
                        imageVector = if (couponExpanded) ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
                        else ImageVector.vectorResource(R.drawable.arrow_drop_down_24px),
                        contentDescription = null
                    )
                }

                // 드롭다운 내용 (애니메이션 처리)
                AnimatedVisibility(visible = couponExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {

                        Text(
                            text = "포인트",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LikeLionOutlinedTextField(
                                label = "포인트",
                                inputType = LikeLionOutlinedTextFieldInputType.NUMBER,
                                modifier = Modifier
                                    .weight(1f)
                            )

                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                            LikeLionFilledButton(
                                text = "전액 사용",
                                modifier = Modifier
                                    .wrapContentSize(),
                                onClick = {/* 포인트 전액 사용 */ }

                            )
                        }

                        Text(
                            text = "보유 포인트: 0 (1,000 포인트부터 사용 가능합니다.)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MainColor,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            LikeLionDivider(thickness = 6.dp)

            /***********************************총 주문 금액**************************************/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 드롭다운 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { orderAmountExpanded = !orderAmountExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "총 주문 금액", style = MaterialTheme.typography.bodyLarge)
                    Icon(
                        imageVector = if (orderAmountExpanded) ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
                        else ImageVector.vectorResource(R.drawable.arrow_drop_down_24px),
                        contentDescription = null
                    )
                }

                // 드롭다운 내용 (애니메이션 처리)
                AnimatedVisibility(visible = orderAmountExpanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {

                        Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                        ) {
                        Text(
                            text = "총 수량",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "1개",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "총 상품금액",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "25,000원",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "총 배송비",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "3,000원",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }

                        LikeLionDivider(paddingTop = 4.dp)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "총 주문금액",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "28,000원",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }

            LikeLionDivider(thickness = 6.dp)

            /***********************************결제 방법**************************************/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 드롭다운 헤더
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { paymentAmountExpanded = !paymentAmountExpanded },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "결제 방법 선택", style = MaterialTheme.typography.titleLarge)
                    Icon(
                        imageVector = if (paymentAmountExpanded)
                            ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
                        else ImageVector.vectorResource(R.drawable.arrow_drop_down_24px),
                        contentDescription = null
                    )
                }

                // 현재 선택된 버튼의 텍스트를 저장하는 상태 변수
                var selectedButton by remember { mutableStateOf("") }

                // 드롭다운 내용 (애니메이션 처리)
                AnimatedVisibility(visible = paymentAmountExpanded) {


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            PaymentButton(
                                text = "신용카드",
                                isSelected = selectedButton == "신용카드",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp),
                                onClick = { selectedButton = "신용카드" }
                            )

                            PaymentButton(
                                text = "네이버페이",
                                isSelected = selectedButton == "네이버페이",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp),
                                onClick = { selectedButton = "네이버페이" }
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp)) // 두 줄 사이 간격 추가

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween

                        ) {
                            PaymentButton(
                                text = "카카오페이",
                                isSelected = selectedButton == "카카오페이",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp),
                                onClick = { selectedButton = "카카오페이" }
                            )
                            PaymentButton(
                                text = "토스",
                                isSelected = selectedButton == "토스",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp),
                                onClick = { selectedButton = "토스" }
                            )
                        }
                    }
                }
            }

            LikeLionDivider(thickness = 6.dp)

            /***********************************결제 및 배송 동의**************************************/

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "결제 및 배송 동의 ", style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.padding(vertical = 2.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "개인 정보 수집 및 이용 동의",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "보기",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 2.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(
                        text = "이용약관 동의",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "보기",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                LikeLionCheckBox(
                    text = "(필수) 주문 내용 및 위의 내용을 모두 확인하였으며, 결제에 동의합니다.",
                    fontSize = 14.sp,
                    textModifier = Modifier.scale(0.8f),
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ShopOrderSheetWritePreview() {
    FinalProjectShoppingMallService3teamTheme {
        ShopOrderSheetWrite()
    }
}