package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.annotation.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LikeLionIconButton(
    icon:ImageVector,
    text: String,
    iconSize: Dp = 20.dp,
    size: Dp = 60.dp,
    padding: Dp = 0.dp,
    iconButtonOnClick : () -> Unit = {},
    borderNull: Boolean
) {
    IconButton(
        onClick = {
            iconButtonOnClick()
        },
        modifier = if (borderNull)Modifier.padding(padding).size(size,iconSize + 5.dp)
        else Modifier.padding(padding).border(1.dp,Color.Gray, shape = RoundedCornerShape(9.dp))
            .size(size,iconSize + 5.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                    .size(iconSize),
                imageVector = icon,
                contentDescription = null

            )
            Text(
                text = text,
                fontSize = 12.sp,
                modifier = Modifier.size(size)
            )
        }

    }
}