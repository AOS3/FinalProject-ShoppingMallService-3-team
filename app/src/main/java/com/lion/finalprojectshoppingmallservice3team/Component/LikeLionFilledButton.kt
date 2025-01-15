package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LikeLionFilledButton(
    // 버튼 텍스트
    text:String = "FilledButton",
    // 위에 패딩
    paddingTop: Dp = 0.dp,
    // 버튼 활성화 여부
    isEnabled: Boolean = true,
    // 클릭 이벤트
    onClick:() -> Unit = {}
) {
    Button(
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) Color(0xFFA16DEB) else Color.LightGray,
            contentColor = if (isEnabled) Color.White else Color.Gray,
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = text)
    }
}