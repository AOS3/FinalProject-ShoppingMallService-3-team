package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionInquiryCard
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.InquiryProductReadViewModel

@Composable
fun InquiryProductReadScreen (inquiryProductReadViewModel: InquiryProductReadViewModel = hiltViewModel()){
    val inquiries = listOf(
        mapOf(
            "author" to "문의 내용",
            "title" to "제목제목제목제목제목",
            "content" to "내용내용내용내용내용내용내용내용",
            "attachment" to "-",
            "date" to "2025.01.09 16:03",
            "isAnswer" to false // 문의
        ),
        mapOf(
            "author" to "답변 완료",
            "content" to "답변 내용답변 내용답변 내용답변 내용",
            "attachment" to "-",
            "date" to "2025.01.10 12:03",
            "isAnswer" to true // 답변
        )
    )
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                title = "문의 상세",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    inquiryProductReadViewModel.navigationButtonClick()
                },
                menuItems = {
                    LikeLionIconButton(
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        padding = 10.dp,
                        borderNull = true
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true
                    )
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {

            Text(
                text = "사용자 정보",
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (Modifier.fillMaxWidth()
                .padding(bottom = 5.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "이름",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.weight(3f),
                    text = "유저이름",
                    fontSize = 14.sp,
                )
            }

            Row (Modifier.fillMaxWidth()
                .padding(bottom = 5.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "연락처",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.weight(3f),
                    text = "01012345678",
                    fontSize = 14.sp,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "문의 정보",
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (Modifier.fillMaxWidth()
                .padding(bottom = 5.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "샵이름",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier
                        .weight(3f)
                        .clickable {  },
                    text = "크리에이터 샵이름",
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline
                )
            }

            Row (Modifier.fillMaxWidth()
                .padding(bottom = 5.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "상품명",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier
                        .weight(3f)
                        .clickable {  },
                    text = "상품 이름",
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            inquiries.forEach { item ->
                LikeLionInquiryCard(item = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}