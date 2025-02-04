package com.lion.finalprojectshoppingmallservice3team.Seller.ui.screen.Item

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDropDownMenu
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionScrollableTabs
import com.lion.finalprojectshoppingmallservice3team.Seller.ui.viewmodel.Item.ItemViewModel

@Composable
fun ItemProductScreen(
    itemViewModel: ItemViewModel = hiltViewModel()
) {


    // 상품 탭 컨텐츠
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "마플샵 상품 만들기",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            "디자인만 있다면 단 3분만에 만드는 마플샵 상품을 만들어보세요.",
            style = TextStyle(fontSize = 14.sp),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        // 드롭다운 메뉴
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            // 카테고리 드롭다운
            LikeLionDropDownMenu(
                options = listOf("전체카테고리", "의류", "신발", "액세서리", "기타"),
                selectedOption = itemViewModel.selectedCategory,
                onOptionSelected = { itemViewModel.onCategorySelected(it) },
                modifier = Modifier.padding(end = 8.dp)
            )

            // 공개여부 드롭다운
            LikeLionDropDownMenu(
                options = listOf("공개여부", "공개", "비공개"),
                selectedOption = itemViewModel.selectedVisibility,
                onOptionSelected = { itemViewModel.onVisibilitySelected(it) },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        // 상품 추가 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { itemViewModel.onAddProductClick() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Product",
                    tint = Color(0xFF8B62FF),
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    "나만의 브랜드 상품을\n등록 해 볼까요?",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}