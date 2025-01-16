package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun LikeLionIconButton(
    icon:ImageVector,
    iconButtonOnClick : () -> Unit = {},
) {
    IconButton(
        onClick = {
            iconButtonOnClick()
        },
        modifier = Modifier.size(20.dp)
    ){
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}