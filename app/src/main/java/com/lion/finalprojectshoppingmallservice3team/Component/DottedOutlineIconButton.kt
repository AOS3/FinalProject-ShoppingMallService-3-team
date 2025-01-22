package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DottedOutlineIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(50.dp) // 버튼 크기 설정
            .drawBehind {
                val borderStrokeWidth = 2.dp.toPx()
                val dashWidth = 10.dp.toPx()
                val dashGap = 5.dp.toPx()

                // 점선 테두리 설정
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, dashGap), 0f)

                drawRoundRect(
                    color = Color.LightGray, // 테두리 색상
                    topLeft = Offset.Zero,
                    size = size,
                    cornerRadius = CornerRadius(8.dp.toPx()), // 모서리 둥글게
                    style = Stroke(
                        width = borderStrokeWidth,
                        pathEffect = pathEffect // 점선 효과 적용
                    )
                )
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp) // 아이콘 크기 설정
        )
    }
}