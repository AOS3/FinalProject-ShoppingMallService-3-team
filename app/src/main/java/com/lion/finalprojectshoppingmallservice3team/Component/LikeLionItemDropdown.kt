package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun LikeLionItemDropdown() {
    var showFirstDialog by remember { mutableStateOf(false) }
    var selectedFirstOption by remember { mutableStateOf("전체") }
    val firstOptions = listOf("전체", "상시판매", "한정판",)

    var showBigCategoryDialog by remember { mutableStateOf(false) }
    var selectedBigCategoryOption by remember { mutableStateOf("전체 상품") }

    var showCategoryDialog by remember { mutableStateOf(false) }
    var selectedCategoryOption by remember { mutableStateOf("전체") }

    // 대분류 별 중분류 매핑
    val categories = remember {
        mapOf(
            "의류" to listOf("티셔츠", "맨투맨", "후드",),
            "굿즈" to listOf("아크릴굿즈", "키링", "거울/핀버튼", "슬리퍼"),
            "패션잡화" to listOf("가방"),
            "쿠션/패브릭" to listOf("쿠션/방석"),
            "문구/오피스" to listOf("마우스패드"),
            "폰액세서리" to listOf("스마트톡"),
            "스티커/지류" to listOf("카드"),
            "리빙" to listOf("머그컵"),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // ... 기존 상단 코드 ...

        // 첫 번째 선택 박스
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .clickable { showFirstDialog = true }
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selectedFirstOption)
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            }
        }

        if (showFirstDialog) {
            Dialog(onDismissRequest = { showFirstDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        firstOptions.forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedFirstOption = option
                                        showFirstDialog = false
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(option)
                                if (option == selectedFirstOption) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color(0xFF8B62FF)
                                    )
                                }
                            }
                            if (option != firstOptions.last()) {
                                Divider()
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 대분류 선택 박스
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .clickable { showBigCategoryDialog = true }
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(selectedBigCategoryOption)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }

            // 중분류 선택 박스
            Box(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                    .clickable {
                        if (selectedBigCategoryOption != "전체 상품") showCategoryDialog = true
                    }
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(selectedCategoryOption)
                    Icon(Icons.Default.ArrowDropDown, null)
                }
            }

        }

        // 대분류 다이얼로그
        if (showBigCategoryDialog) {
            Dialog(onDismissRequest = { showBigCategoryDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        (listOf("전체 상품") + categories.keys).forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedBigCategoryOption = option
                                        selectedCategoryOption = "전체"
                                        showBigCategoryDialog = false
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(option)
                                if (option == selectedBigCategoryOption) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color(0xFF8B62FF)
                                    )
                                }
                            }
                            if (option != categories.keys.last()) {
                                Divider()
                            }
                        }
                    }
                }
            }
        }

        // 중분류 다이얼로그
        if (showCategoryDialog) {
            Dialog(onDismissRequest = { showCategoryDialog = false }) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        categories[selectedBigCategoryOption]?.forEach { option ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedCategoryOption = option
                                        showCategoryDialog = false
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(option)
                                if (option == selectedCategoryOption) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color(0xFF8B62FF)
                                    )
                                }
                            }
                            if (option != categories[selectedBigCategoryOption]?.last()) Divider()
                        }
                    }
                }
            }

        }
    }
}