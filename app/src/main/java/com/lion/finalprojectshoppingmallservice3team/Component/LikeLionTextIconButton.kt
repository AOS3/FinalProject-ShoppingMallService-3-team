package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.onClick
import android.R.attr.paddingTop
import android.R.attr.text
import android.R.attr.textColor
import androidx.compose.animation.SharedTransitionScope.PlaceHolderSize.Companion.contentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LikeLionTextIconButton(
    // 버튼 텍스트
    text:String = "FilledButton",
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    // 위에 패딩
    paddingTop: Dp = 0.dp,
    // 버튼 활성화 여부
    isEnabled: Boolean = true,
    // 아이콘
    icon: ImageVector,
    modifier: Modifier = Modifier,
    // 배경색
    containerColor: Color = Color.White,
    // 글자색
    textColor: Color = LocalContentColor.current,
    // 아이콘 컬러
    iconColor: Color = LocalContentColor.current,
    //사이즈
    iconSize: Dp = 28.dp,
    contentSize:Dp = 63.dp,
    // 가로 채우기
    fillWidth: Boolean = false,
    // 클릭 이벤트
    onClick:(() -> Unit)? = {},
    cornerRadius: Dp = 100.dp,
) {
    Card(
        modifier = if (fillWidth)
            modifier.padding(top = paddingTop).fillMaxWidth()
                .clip(RoundedCornerShape(cornerRadius))
            else
            modifier.padding(top = paddingTop).size(contentSize ,iconSize + 10.dp)
            .clip(RoundedCornerShape(cornerRadius)),
        colors = CardDefaults.cardColors(
            containerColor
        ),
        border = border,
    ){
        Row(
            modifier = if (fillWidth){
                if (onClick==null)Modifier.fillMaxWidth().height(iconSize+10.dp)
                else Modifier.fillMaxWidth().height(iconSize+10.dp)
                    .clickable(onClick = {
                        if (isEnabled) {
                            onClick()
                        }
                    })
            }
            else{
                if (onClick==null)Modifier.size(contentSize ,iconSize + 10.dp)
                else Modifier.size(contentSize ,iconSize + 10.dp).clickable(onClick = {
                    if (isEnabled) {
                        onClick()
                    }
                })
            }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // ImageVector 아이콘 표시
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize), // 아이콘 크기
                tint = iconColor
            )
            Text(
                text = text,
                fontSize = 12.sp,
                color = textColor
            )
        }
    }
}
