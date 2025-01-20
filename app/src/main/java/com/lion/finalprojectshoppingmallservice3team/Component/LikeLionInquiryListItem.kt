package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun LikeLionInquiryListItem(item: Map<String, *>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        // 상태 텍스트
        Text(
            text = item["status"] as String,
            fontWeight = FontWeight.Bold,
            color = if (item["status"] == "답변 대기") Color.Gray else MainColor, // 상태에 따른 색상
            fontSize = 14.sp,
            modifier = Modifier
                .background(
                    color = if (item["status"] == "답변 대기") Color.LightGray else SubColor, // 상태에 따른 배경색
                    shape = RoundedCornerShape(5.dp)
                ) // 둥근 모서리 설정
                .padding(horizontal = 4.dp, vertical = 2.dp)
        )

        // 제목 텍스트
        Text(
            text = item["title"] as String,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
        )

        // 작성자와 날짜
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item["author"] as String,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = item["date"] as String,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        // 구분선
        LikeLionDivider(
            color = Color.LightGray,
            thickness = 1.dp,
        )
    }
}