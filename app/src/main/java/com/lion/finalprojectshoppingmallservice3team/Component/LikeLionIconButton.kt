package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.text
import android.util.Log
import androidx.annotation.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.R
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LikeLionIconButton(
    icon:ImageVector,
    text: String? = null,
    iconColor: Color = LocalContentColor.current,
    color: Color = Color.White,
    iconBackColor: Color = Color.White,
    fontColor: Color = LocalContentColor.current,
    size: Dp = 35.dp,
    fontSize: TextUnit = 12.sp,
    padding: Dp = 0.dp,
    iconButtonOnClick : () -> Unit = {},
    borderNull: Boolean,
    fillWidth : Boolean = false
) {

    val iconSize = if (text==null) size - 8.dp else size - 30.dp
    val widthSize =  if (text==null) size else size + size/4 - 5.dp
    val heightSize =  if (text==null) iconSize + 10.dp else iconSize

    IconButton(
        onClick = {
            iconButtonOnClick()
        },
        modifier = if(fillWidth)
            Modifier.padding(padding).fillMaxWidth()
            else if (fillWidth && !borderNull) Modifier.padding(padding).border(1.dp,Color.Gray, shape = RoundedCornerShape(9.dp))
                .fillMaxWidth()
            else if (!fillWidth && borderNull)Modifier.padding(padding).size(widthSize,heightSize)
        else Modifier.padding(padding).border(1.dp,Color.Gray, shape = RoundedCornerShape(9.dp))
            .size(widthSize ,heightSize),
        colors = IconButtonDefaults.iconButtonColors(
            color
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Card(
                modifier = Modifier.clip(CircleShape).size(iconSize),
                colors = CardDefaults.cardColors(
                    iconBackColor
                )
            ) {
                Icon(
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                        .size(iconSize),
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                )
            }

            if (text != null) {
                Spacer(Modifier.width(2.dp))
                Text(
                    text = text,
                    fontSize = fontSize,
                    color = fontColor,
                    modifier = if(fillWidth)Modifier.fillMaxWidth(0.9f)
                    else Modifier.size(size)
                )
            }
        }
    }
}