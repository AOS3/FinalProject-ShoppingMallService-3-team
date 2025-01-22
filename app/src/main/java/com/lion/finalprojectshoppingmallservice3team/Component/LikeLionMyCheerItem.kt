package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

@Composable
fun LikeLionMyCheerItem(cheer: Map<String, *>, showCreatorName: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth().padding(bottom = 10.dp)
    ) {
        val creatorName = cheer["creatorName"] as? String
        // 크리에이터 이름 및 구분선 (카드 외부)
        if (showCreatorName && !creatorName.isNullOrEmpty()) {
            Text(
                text = creatorName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            LikeLionDivider(
                thickness = 2.dp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        // 카드 내부 내용
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(8.dp), // 카드의 둥근 모서리
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 상단 영역: 이메일 및 날짜
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        // 프로필 이미지
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.LightGray, shape = CircleShape)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // 이메일 텍스트
                        Text(
                            text = cheer["email"] as String,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // 날짜
                    Text(
                        text = cheer["date"] as String,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // 내용 텍스트와 첨부 이미지
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // 텍스트 내용
                    Text(
                        text = cheer["content"] as String,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f) // 텍스트가 남은 공간을 채움
                    )

                    // 첨부 이미지 (있을 경우 표시)
                    if (cheer["attachment"] != null) {
                        Image(
                            painter = painterResource(id = cheer["attachment"] as Int), // 로컬 리소스 사용
                            contentDescription = "첨부 이미지",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.LightGray, RoundedCornerShape(4.dp))
                        )
                    }
                }

                // 하단 좋아요
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "좋아요",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = cheer["likes"].toString(),
                        modifier = Modifier.padding(start = 5.dp),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}