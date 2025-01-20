package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun LikeLionInquiryCard(item: Map<String, *>) {
    val isAnswer = item["isAnswer"] as Boolean // 답변 여부를 가져옴
    val backgroundColor = if (isAnswer) SubColor else Color(0xFFE0E0E0) // 답변 여부에 따른 배경색

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        // 작성자 및 날짜
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item["author"] as String,
                fontSize = 14.sp,
                color = if (isAnswer) Color(0xFF673AB7) else Color.Black // 답변이면 보라색
            )
            Text(
                text = item["date"] as String,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 제목 (문의일 때만 표시)
        if (!isAnswer) {
            Text(
                text = "제목: ${(item["title"] ?: "제목 없음") as String}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        // 내용
        Text(
            text = if (isAnswer) "답변 내용: ${(item["content"] as String)}" else "내용: ${(item["content"] as String)}",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // 첨부 파일
        Text(
            text = "첨부파일: ${(item["attachment"] ?: "-") as String}",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}