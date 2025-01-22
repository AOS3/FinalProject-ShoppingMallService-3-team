package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionInquiryCard
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage.InquiryReadViewModel

@Composable
fun InquiryReadScreen(inquiryReadViewModel: InquiryReadViewModel = hiltViewModel()) {
    val inquiries = listOf(
        mapOf(
            "author" to "작성자 닉네임",
            "title" to "제목제목제목제목제목",
            "content" to "내용내용내용내용내용내용내용내용",
            "attachment" to "-",
            "date" to "2025.01.09 16:03",
            "isAnswer" to false // 문의
        ),
        mapOf(
            "author" to "운영진 닉네임",
            "content" to "답변 내용답변 내용답변 내용답변 내용",
            "attachment" to "-",
            "date" to "2025.01.10 12:03",
            "isAnswer" to true // 답변
        )
    )
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.Transparent,
                title = "문의 상세",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    inquiryReadViewModel.navigationOnClick()
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
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 10.dp)
        ) {
            inquiries.forEach { item ->
                LikeLionInquiryCard(item = item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}