package com.lion.a02_boardcloneproject.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun LikeLionImage(
    painter : Painter,
    contentScale: ContentScale = ContentScale.Fit,
    tintColor : Color? = null
) {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        colorFilter = if(tintColor != null){
            ColorFilter.tint(tintColor)
        } else {
            null
        },
    )
}