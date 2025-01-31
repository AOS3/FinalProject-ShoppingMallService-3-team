package com.lion.finalprojectshoppingmallservice3team.Component


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun PaymentButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LikeLionFilledButton(
        text = text,
        containerColor = if (isSelected) MainColor else Color.Transparent, // 선택된 경우와 기본 색상
        contentColor = if (isSelected) Color.White else Color.Black, // 텍스트 색상
        onClick = onClick,
        modifier = modifier
    )
}