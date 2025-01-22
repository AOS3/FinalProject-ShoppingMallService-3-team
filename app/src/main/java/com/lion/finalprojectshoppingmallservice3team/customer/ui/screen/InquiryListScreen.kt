package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

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

import androidx.compose.ui.text.font.FontWeight

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
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.InquiryListViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun InquiryListScreen(inquiryListViewModel: InquiryListViewModel = hiltViewModel()) {
    val selectedOption = remember { mutableStateOf("전체") }

    // 샘플 데이터
    val inquiryList = remember {
        mutableStateListOf<Map<String, *>>(
            mapOf(
                "status" to "답변 대기",
                "title" to "문의 제목 1",
                "name" to "작성자 닉네임",
                "date" to "2025.01.09"
            ),
            mapOf(
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
                backColor = Color.Transparent,
                title = "문의",
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    inquiryListViewModel.navigationOnClick()
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
        floatingActionButton = {
            LikeLionFloatingActionButton(
                icon = ImageVector.vectorResource(id = R.drawable.edit_24px),
                contentDescription = "inquiryWrite",
                onClick = {
                    inquiryListViewModel.inquiryWriteFabOnClick()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End // 오른쪽 하단 정렬
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    listOf("전체", "답변대기", "답변완료").forEach { option ->
                        Text(
                            text = option,
                            fontSize = 14.sp,
                            textDecoration = if (selectedOption.value == option) TextDecoration.Underline else null,
                            color = if (selectedOption.value == option) MainColor else Color.Gray,
                            modifier = Modifier
                                .clickable { selectedOption.value = option }
                                .padding(end = 10.dp)
                        )
                    }
                }

                Row {
                    LikeLionCheckBox(
                        fontSize = 14.sp,
                        text = "나의 문의",
                        checkedValue = inquiryListViewModel.checkBoxMyinquiryValue,
                        textModifier = Modifier.padding(start = 0.dp),
                        modifier = Modifier,
                        onCheckedChange = {

                        }
                    )
                }
            }

            // 필터링된 리스트 출력
            val filteredList = when (selectedOption.value) {
                "전체" -> inquiryList // 전체 데이터
                "답변대기" -> inquiryList.filter { it["status"] == "답변 대기" }
                "답변완료" -> inquiryList.filter { it["status"] == "답변 완료" }
                else -> inquiryList
            }

            // 리스트 컴포넌트
            LikeLionList(
                dataList = filteredList.toMutableStateList(),
                rowComposable = { item ->
                    LikeLionInquiryListItem(item)
                },
                onRowClick = {
                    inquiryListViewModel.inquiryListOnClick()
                }
            )
        }
    }
}