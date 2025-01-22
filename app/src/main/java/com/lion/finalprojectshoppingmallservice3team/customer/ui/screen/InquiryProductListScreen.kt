package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCheckBox
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFloatingActionButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionInquiryListItem
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.InquiryProductListViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun InquiryProductListScreen (
    inquiryProductListViewModel: InquiryProductListViewModel = hiltViewModel(),
    ){

    // 샘플 데이터
    val inquiryList = remember {
        mutableStateListOf<Map<String, *>>(
            mapOf(
                "shopName" to "크리에이터이름1",
                "productName" to "상품명1",
                "status" to "답변 대기",
                "title" to "문의 제목 1",
                "name" to "작성자 닉네임",
                "date" to "2025.01.09"
            ),
            mapOf(
                "shopName" to "크리에이터이름2",
                "productName" to "상품명2",
                "status" to "답변 완료",
                "title" to "문의 제목 2",
                "name" to "작성자 닉네임",
                "date" to "2025.01.09"
            )
        )
    }

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                backColor = Color.White,
                title = "크리에이터 문의 내역 확인",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    inquiryProductListViewModel.navigationButtonClick()
                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
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
    ){
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {

            // 리스트 컴포넌트
            LikeLionList(
                dataList = inquiryList.toMutableStateList(),
                rowComposable = { item ->
                    LikeLionInquiryListItem(item, true)
                },
                onRowClick = {
                   inquiryProductListViewModel.inquiryProductListOnClick()
                }
            )
        }
    }
}