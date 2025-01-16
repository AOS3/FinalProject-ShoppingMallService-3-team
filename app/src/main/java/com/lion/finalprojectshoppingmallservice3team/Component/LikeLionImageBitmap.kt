package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale


@Composable
fun LikeLionImageBitmap(
    imageBitmap: ImageBitmap,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        modifier = Modifier.fillMaxWidth(),
        bitmap = imageBitmap,
        contentDescription = null,
        contentScale = contentScale,
    )
}