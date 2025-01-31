package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.ui.theme.SubColor

@Composable
fun LikeLionInquiryListItem(item: Map<String, *>, isShop: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        if (isShop) {
            // 상태 텍스트
            Text(
                text = item["state"] as String,
                fontWeight = FontWeight.Bold,
                color = if (item["state"] == "문의 대기") Color.Gray else MainColor, // 상태에 따른 색상
                fontSize = 14.sp,
                modifier = Modifier
                    .background(
                        color = if (item["state"] == "문의 대기") Color.LightGray else SubColor, // 상태에 따른 배경색
                        shape = RoundedCornerShape(5.dp)
                    ) // 둥근 모서리 설정
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        } else {
            // 상태 텍스트
            Text(
                text = item["state"] as String,
                fontWeight = FontWeight.Bold,
                color = if (item["state"] == "답변 대기") Color.Gray else MainColor, // 상태에 따른 색상
                fontSize = 14.sp,
                modifier = Modifier
                    .background(
                        color = if (item["state"] == "답변 대기") Color.LightGray else SubColor, // 상태에 따른 배경색
                        shape = RoundedCornerShape(5.dp)
                    ) // 둥근 모서리 설정
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }

        // 작성자와 날짜
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 제목 텍스트
            Text(
                text = item["title"] as String,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            )
            Text(
                text = (item["createdAt"]).toString(),
                color = Color.Gray,
                fontSize = 16.sp
            )
        }

        if (isShop) {
            // 작성자와 날짜
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "샵이름",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Text(
                        text = item["shopName"] as String,
                        modifier = Modifier.padding(start = 10.dp).clickable {

                        },
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = "상품명",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )

                    Text(
                        text = item["productName"] as String,
                        modifier = Modifier.padding(start = 10.dp).clickable {

                        },
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp
                    )
                }
            }

            // 구분선
            LikeLionDivider(
                color = Color.LightGray,
                thickness = 1.dp,
            )
        } else {
            // 작성자와 날짜
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
            ) {
                Text(
                    text = item["nickname"] as String,
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
}