package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionAccordion
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.CancelRefundFAQViewModel

@Composable
fun CancelRefundFAQScreen (cancelRefundFAQViewModel: CancelRefundFAQViewModel = hiltViewModel()){
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                title = "FAQ",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    cancelRefundFAQViewModel.navigationButtonClick()
                },
            )
        },
    ){
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
                .verticalScroll(state = scrollState)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                text = "취소/환불",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            LikeLionAccordion(
                title ="주문을 취소하고 싶어요." ,
                content = {
                    Text(
                        text = "특정 단계까지는 마이페이지 > 구매내역 > 상세보기에서 직접 주문취소 할 수 있습니다.\n" +
                                "\n" +
                                "<마크샵 배송 상품>\n" +
                                "[제작준비중] 단계까지는 주문취소가 가능합니다.\n" +
                                "[제작중] 단계부터는 주문 취소가 불가하니, 제작이 시작되기 전에 취소해주세요.\n" +
                                "주문제작 오더메이드 상품의 특성상 단순변심으로 인한 주문취소/교환/반품은 어렵습니다.\n" +
                                "주문 취소에 관한 자세한 내용은 마플샵 FAQ의 취소/환불에서 확인하실 수 있습니다.\n" +
                                "※ 주문취소 버튼은 [제작준비중] 단계까지만 보실 수 있습니다.\n" +
                                "마플샵은 사용하는 상품만 생산 함으로써 낭비없는 생산으로 자원의 낭비를 막고 환경을 생각합니다. 신중한 구매 부탁드립니다.\n" +
                                "디지털굿즈의 경우 구매 완료 후 주문취소가 불가하며 디지털 콘텐츠의 특성상, 구매 완료 된 이미지에 대해서는 환불이 불가능합니다.\n" +
                                "디바이스별 이미지의 비율 또는 해상도의 차이에 따른 환불이 불가능합니다.\n"
                                ,
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="여러 개의 상품을 주문했는데, 일부만 취소하고 싶어요." ,
                content = {
                    Text(
                        text = "<마크샵 배송 상품>\n" +
                                "부분 취소 관련 신청은 고객센터(1566-5496) 또는 1:1 문의로 요청해주세요.\n" +
                                "주문제작 상품의 특성상 단순변심으로 인한 주문취소/교환/반품은 어렵습니다.",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="주문취소했는데 언제 환불되나요?" ,
                content = {
                    Text(
                        text = "환불은 환불 승인일로부터 영업일 기준 2~5일 정도 소요됩니다.\n" +
                                "정확한 환불일자는 해당 금융기관에서 확인하실 수 있습니다.\n" +
                                "주문취소 완료 후 신용카드 결제는 승인취소, 계좌이체 및 가상계좌 결제는 고객님이 입력하신 환불계좌로 환불처리 해드립니다.\n" +
                                "주문취소 완료 후 포인트는 다시 재적립 됩니다.\n" +
                                "\n" +
                                "결제 방식에 따라 환불 처리 기간이 상이합니다. \n" +
                                "\n" +
                                "신용카드 : 카드 취소일로부터 2~5일 이내 카드사별 개별 승인취소 확인이 가능 결제일에 카드 대금이 청구된 경우에는 다음달 카드 내역서에서 취소 확인 가능\n" +
                                "간편 결제 : 네이버페이, 토스, 카카오페이 일주일 이내에 환불",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="환불이 완료되면 어떤 방법으로 알 수 있나요?" ,
                content = {
                    Text(
                        text = "마이페이지 > 구매내역에서 주문 취소 내역을 확인하실 수 있으며 취소완료 이메일을 보내드립니다.",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="입금자명과 다른 명의의 계좌 또는 다른 결제 방식으로 환불 받을 수 있나요?" ,
                content = {
                    Text(
                        text = "다른 명의로의 환불은 불가하며, 주문 시 입금하셨던 입금자명의 계좌 또는 결제 방식으로만 환불 가능합니다.",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="결제수단 변경 가능한가요?" ,
                content = {
                    Text(
                        text = "결제 수단 변경은 주문 취소 후 재주문을 권장드립니다.\n" +
                                "\n" +
                                "<마플샵 배송 상품>\n" +
                                "마플샵 배송 상품 결제 수단 변경은 고객센터(1566-5496) 또는 마플샵 채팅 상담으로 문의주세요.",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
            LikeLionAccordion(
                title ="마플샵 배송 상품 결제 완료되었는데 상품이 왜 품절되었나요?" ,
                content = {
                    Text(
                        text = "마플샵 배송 상품은 주문제작 상품이지만 초기 판매 가능 수량과 달리 포장 검수 과정에서 불량이 확인되거나\n" +
                                "재고 관리 중 손실되기도 하며 제조사의 사정상 상품의 추가 입고가 어려운 상황이 발생하는 경우가 있습니다.\n" +
                                "\n" +
                                "이러한 경우는 개별적으로 상품 품절 및 주문취소를 안내하고 있습니다.\n" +
                                "이 점 너그러운 양해 부탁드립니다 .",
                        fontSize = 14.sp
                    )
                },
                coroutineScope = coroutineScope,
                scrollState = scrollState
            )
        }
    }

}