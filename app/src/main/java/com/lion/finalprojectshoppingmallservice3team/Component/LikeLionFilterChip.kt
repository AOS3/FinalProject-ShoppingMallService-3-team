package com.lion.finalprojectshoppingmallservice3team.Component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun LikeLionFilterChip(
    text: String,
    selected: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    chipTextStyle: TextStyle,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    @SuppressLint("ModifierParameter")
    chipModifier: Modifier,
    modifier: Modifier = Modifier,
    onChipClicked: (String, Boolean) -> Unit,
    onDeleteButtonClicked: (String) -> Unit  = {}// 삭제 버튼 콜백 추가
) {

    Surface(
        color = when {
            selected -> selectedColor
            else -> unselectedColor
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onChipClicked(text, selected) }
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ){
            Text(
                text = text,
                color = when {
                    selected -> selectedTextColor
                    else -> unselectedTextColor
                },
                style = chipTextStyle,
                modifier = chipModifier
                    .clickable { onChipClicked(text, selected) }
            )

            IconButton(
                onClick = { onDeleteButtonClicked(text) },
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear, // 삭제 아이콘 설정
                    contentDescription = "Delete Chip",
                    tint = Color.Gray
                )
            }
        }
    }
}
