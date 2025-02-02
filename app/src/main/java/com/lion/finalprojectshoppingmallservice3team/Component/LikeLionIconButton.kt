package com.lion.a02_boardcloneproject.component

import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun LikeLionIconButton(
    icon:ImageVector,
    iconButtonOnClick : () -> Unit = {},
) {
    IconButton(
        onClick = {
            iconButtonOnClick()
        }
    ){
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}