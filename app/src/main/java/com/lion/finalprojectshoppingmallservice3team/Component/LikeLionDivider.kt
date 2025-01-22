package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LikeLionDivider(
    paddingTop:Dp = 0.dp,
    color:Color = Color.LightGray,
    thickness:Dp = 1.dp
) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop),
        thickness = thickness,
        color = color
    )
}