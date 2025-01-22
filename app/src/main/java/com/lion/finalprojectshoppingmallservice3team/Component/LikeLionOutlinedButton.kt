package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LikeLionOutlinedButton(
    text:String = "OutlinedButton",
    paddingTop:Dp = 0.dp,
    // 아이콘
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
    onClick:() -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = paddingTop),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Text(text = text)

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp).fillMaxWidth(), // 아이콘 크기 설정
                tint = Color.LightGray // 아이콘 색상 설정
            )
        }
    }
}