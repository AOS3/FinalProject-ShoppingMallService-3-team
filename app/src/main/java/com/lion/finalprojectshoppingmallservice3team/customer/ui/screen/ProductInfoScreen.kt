package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionAccordion
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBottomSheet
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFloatingActionButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionImageSlider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionReviewItem
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.Product
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.ProductInfoViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import kotlinx.coroutines.launch

@Composable
fun ProductInfoScreen(
    productInfoViewModel: ProductInfoViewModel = hiltViewModel(),
    productDocumentId:String,
) {
    var product by remember { mutableStateOf<Product?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val isFavorite by productInfoViewModel.isFavorite.collectAsState()

    // 일정 스크롤 거리 이상 시 FloatingActionButton을 표시하도록 하는 상태
    val showFab = remember { mutableStateOf(false) }

    // 스크롤 위치를 감지하여 showFab을 업데이트
    LaunchedEffect(scrollState.value) {
        // 스크롤이 400 이상일 때만 FAB을 표시
        showFab.value = scrollState.value > 400
    }

    LaunchedEffect(productDocumentId) {
        productInfoViewModel.gettingProductData(productDocumentId) {
            product = it
        }
    }


    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    productInfoViewModel.navigationButtonClick()
                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.search_24px),
                        iconButtonOnClick = {
                            // 검색화면 이동
                        },
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(R.drawable.shopping_cart_24px),
                        iconButtonOnClick = {
                            // 장바구니 이동
                        },
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true
                    )
                },
                backColor = Color.Transparent,

                )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                product?.let {
                    // 좋아요 버튼
                    IconButton(
                        onClick = {
                            productInfoViewModel.onLikeClick()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isFavorite) R.drawable.favorite_fill_24px
                                else R.drawable.favorite_24px
                            ),
                            contentDescription = "Like Button",
                            tint = if (isFavorite) MainColor else Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                    }


                    if (it.stockQuantity == 0){
                        // 구매하기 버튼
                        LikeLionFilledButton(
                            text = "판매가 종료되었어요!",
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp),
                            contentColor = Color.White,
                            containerColor = Color.LightGray,
                            isEnabled = false
                        )
                    }
                    else{
                        // 구매하기 버튼
                        LikeLionFilledButton(
                            text = "구매하기",
                            onClick = {
                                showBottomSheet = true
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp),
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (showFab.value){
                FloatingActionButton(
                    modifier = Modifier.scale(0.8f),
                    onClick = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(0) // 코루틴 내에서 호출
                        }
                    },
                    containerColor = MainColor,
                    contentColor = Color.White
                ) {
                    Icon(painter = painterResource(R.drawable.arrow_upward_24px) , contentDescription = "Add Item")
                }
            }
        },
    ){
        Column (modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(it)
            .verticalScroll(scrollState)
        ){
            product?.let {
                // 이미지 슬라이더
                LikeLionImageSlider(imageUrls = it.imageUrls)

                Spacer(modifier = Modifier.height(10.dp))

                // 크리에이터 이름
                Text(
                    text = it.creator,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )

                // 상품명
                Text(
                    text = it.name,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                // 상품 카테고리
                Text(text = "${it.category} > ${it.subCategory}",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                )
                val formattedPrice = String.format("%,d", it.price)
                // 가격
                Text(text = "${formattedPrice}원",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp,))
            }

            Spacer(modifier = Modifier.height(10.dp))

            LikeLionDivider()

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                ){
                    Text(
                        text = "배송안내",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "기본배송비 3,000원\n일반 택배(택배사)",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.sp
                        ),
                        modifier = Modifier.weight(3f)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "배송 출발일",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "지금 주문하면 1/15 ~ 1/22 사이에 출발해요!\n특별함을 담아 제작해서 배송해드려요.\n설레는 마음으로 기다려주세요!",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 16.sp
                        ),
                        modifier = Modifier.weight(3f)
                    )

                }
            }
            LikeLionDivider()

            Column(modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding().padding(10.dp)
            ) {
                Text(
                    text = "상품 안내",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                product?.let { it ->
                    Text(
                        text = it.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 16.sp
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "구매 안내",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )

                // 구매 안내 -> 배송정보
                LikeLionAccordion(
                    title = "배송정보",
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "모든 상품은 고객님의 주문에 맞춰 새 상품으로 제작하여 배송됩니다 " +
                                        "지금 주문하시면 1월 15일부터 제작 프로세스가 시작되어 1월" +
                                        "22일 이전에 출고될 예정입니다.",
                                fontSize = 14.sp,
                                lineHeight = 16.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "배송 방법",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "일반택배 (CJ 대한통운)",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 14.sp
                                    ),
                                    modifier = Modifier.weight(3f)
                                )
                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "배송 지역",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "전국",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 14.sp
                                    ),
                                    modifier = Modifier.weight(3f)
                                )

                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "배송 기간",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    lineHeight = 16.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "상품 출고 후 영업일 기준 1~3일 이내 수령이 가능하며" +
                                            "도서 산간 지역이거나 택배사의 물량이 많은 경우 기간이" +
                                            "조금 더 소요될 수 있습니다.",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 16.sp
                                    ),
                                    modifier = Modifier.weight(3f)
                                )

                            }
                        }

                    }
                )

                // 구매 안내 -> 교환/환불 정보
                LikeLionAccordion(
                    title = "교환/환불 정보",
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "모든 마플샵 배송 상품은 주문 제작 방식으로 제작되어 단순 변심으로 인한 교환/환불이 불가합니다 " +
                                        "단, 수령하신 상품이 불량이거나 오배송된 경우에는 7일 이내 고객센터 또는 상품문의하기로 연락 주시면 교환 및 반품 환불이 가능합니다.",
                                fontSize = 14.sp,
                                lineHeight = 14.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "고객 센터",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "1566-5496",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 14.sp
                                    ),
                                    modifier = Modifier.weight(3f)
                                )
                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "이메일",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "cs@marcshop.com",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 14.sp
                                    ),
                                    modifier = Modifier.weight(3f)
                                )
                            }

                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                LikeLionFilledButton(
                                    text = "상품 문의하기",
                                    onClick = {
                                        // 문의하기 화면 이동
                                        productInfoViewModel.inquiryProductButtonOnClick()
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    contentColor = MainColor,
                                    containerColor = Color.White,
                                    border = BorderStroke(1.dp, Color.LightGray)
                                )

                                LikeLionFilledButton(
                                    text = "취소/환불 FAQ",
                                    onClick = {
                                        // 취소/환불 FAQ 화면 이동
                                        productInfoViewModel.cancelRefundFAQButtonOnClick()
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    contentColor = MainColor,
                                    containerColor = Color.White,
                                    border = BorderStroke(1.dp, Color.LightGray)
                                )
                            }
                        }

                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 예시 데이터
                val exampleData = mapOf(
                    "profileImage" to "https://s3.ap-northeast-2.amazonaws.com/univ-careet/FileData/Picture/202304/f50c81b9-3d33-49fd-b003-c530b53b0359_770x426.png", // 프로필 이미지 리소스
                    "nickname" to "작성자 닉네임",
                    "rating" to 4,
                    "date" to "2025.01.09",
                    "productName" to "티셔츠 Example Product",
                    "content" to "리뷰 아이템 만들기 어렵다 컴포즈도 너무 어렵다 리뷰 아이템 만들기 어렵다 컴포즈도 너무 어렵다" +
                            "길게 적어서 테스트 해야지",
                    "reviewImages" to "https://m.mondayfactory.co.kr/web/product/big/202202/cb4b8b4863da262f0671c705eb014bc2.jpg" // 리뷰 이미지 리소스

                )

                // LikeLionReviewItem 컴포넌트를 사용하여 예시 데이터 렌더링
                LikeLionReviewItem(
                    profileImage = exampleData["profileImage"] as String,
                    nickname = exampleData["nickname"] as String,
                    rating = exampleData["rating"] as Int,
                    date = exampleData["date"] as String,
                    productName = exampleData["productName"] as String,
                    content = exampleData["content"] as String,
                    reviewImages = exampleData["reviewImages"] as String
                )

                // LikeLionReviewItem 컴포넌트를 사용하여 예시 데이터 렌더링
                LikeLionReviewItem(
                    profileImage = exampleData["profileImage"] as String,
                    nickname = exampleData["nickname"] as String,
                    rating = exampleData["rating"] as Int,
                    date = exampleData["date"] as String,
                    productName = exampleData["productName"] as String,
                    content = exampleData["content"] as String,
                    reviewImages = exampleData["reviewImages"] as String
                )

            }
        }
        // LikeLionBottomSheet 표시
        if (showBottomSheet) {
            product?.let {
                LikeLionBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    productPrice = it.price, // 상품 가격 전달
                    selectedSize = null, // 사이즈 정보 없음
                    selectedColor = null, // 컬러 정보 없음
                )
            }
        }
    }
}