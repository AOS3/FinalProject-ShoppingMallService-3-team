package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LikeLionMyReviewItem(review: Map<String, *>, showCreatorName: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (showCreatorName) {
            Text(
                text = review["creatorName"] as String,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            LikeLionDivider(
                color = Color.Black,
                thickness = 2.dp,
            )
        }

        // 상품명과 날짜
        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = review["productName"] as String,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )

            Text(
                text = review["date"] as String,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                color = Color.Gray
            )
        }

        // 별점 표시
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            val rating = review["rating"] as? Int ?: 0
            for (i in 1..5) {
                Icon(
                    imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarOutline,
                    contentDescription = "별점 $i",
                    tint = Color(0xFF9C27B0), // 보라색 별
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // 리뷰 내용
        Text(
            text = review["reviewContent"] as String,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        // 구분선
        LikeLionDivider(
            color = Color.LightGray,
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.padding(bottom = 10.dp))
    }
}