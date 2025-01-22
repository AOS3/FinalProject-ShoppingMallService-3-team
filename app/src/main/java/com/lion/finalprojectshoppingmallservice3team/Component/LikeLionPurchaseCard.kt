package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun LikeLionPurchaseCard(purchase: Map<String, *>, showCreatorName: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        if (showCreatorName) {
            Text(
                text = purchase["creatorName"] as String,
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

        // 상단 상태 및 날짜
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = purchase["status"] as String,
                fontSize = 14.sp,
                color = MainColor, // 보라색
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${purchase["date"]} ${purchase["deliveryStatus"]}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 상품 이미지 및 정보
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 상품 이미지
            Image(
                painter = painterResource(id = purchase["productImage"] as Int),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 상품 정보
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = purchase["productName"] as String,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = purchase["productCategory"] as String,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = purchase["price"] as String,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 하단 버튼
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LikeLionFilledButton(
                text = "배송 조회",
                onClick = {
                    // 배송 조회 클릭 동작
                },
                modifier = Modifier.weight(1f).padding(end = 4.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                containerColor = Color.Transparent,
                contentColor = Color.Black
            )

            LikeLionFilledButton(
                text = "리뷰 작성하기",
                onClick = {
                    // 리뷰 작성 클릭 동작
                },
                modifier = Modifier.weight(1f).padding(start = 4.dp),
                containerColor = SubColor,
                contentColor = MainColor
            )
        }
    }
}
