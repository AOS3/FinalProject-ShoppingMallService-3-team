package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionFloatingActionButton(
    icon: ImageVector,
    contentDescription: String = "",
    onClick:() -> Unit = {}
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MainColor // 버튼 배경색 설정
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.White // 아이콘 색상
        )
    }
}
