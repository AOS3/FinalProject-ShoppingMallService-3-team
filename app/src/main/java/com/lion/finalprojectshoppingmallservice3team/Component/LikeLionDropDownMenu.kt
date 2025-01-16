package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R

@Composable
fun LikeLionDropDownMenu (
    // 드롭다운에 표시할 옵션 리스트
    options: List<String>,
    // 현재 선택된 옵션
    selectedOption: String,
    // 옵션이 선택되었을 때 호출되는 함수
    onOptionSelected: (String) -> Unit,
    // Modifier
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) } // 드롭다운 상태
    val icon = if (expanded) {
        ImageVector.vectorResource(R.drawable.arrow_drop_up_24px)
    } else {
        ImageVector.vectorResource(R.drawable.arrow_drop_down_24px)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = selectedOption,
            modifier = Modifier
                .clickable { expanded = !expanded }
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .clickable { expanded = !expanded }  // 아이콘 클릭 시 드롭다운 토글
                .padding(8.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }
    }
}