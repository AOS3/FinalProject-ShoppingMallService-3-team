package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun LikeLionReviewItem(
    profileImage: String,
    nickname: String,
    rating: Int,
    date: String,
    productName: String,
    content: String,
    reviewImages: String
) {
    var isLiked by remember { mutableStateOf(false) }
    var likesCount by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth()
            .clickable {  }
    ) {
        // 프로필 이미지와 닉네임, 날짜, 평점
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row{
                LikeLionProfileImg(
                    imgUrl = "",

                    modifier = Modifier.size(40.dp),
                  
                    iconTint = MainColor,
                    profileBack = Color.LightGray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = nickname, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Row {
                            val maxStars = 5
                            Row {
                                repeat(rating) {
                                    // 가득 찬 별 아이콘
                                    Icon(painter = painterResource(id = R.drawable.star_fill_24px), contentDescription = null, tint = MainColor, modifier = Modifier.size(16.dp))
                                }
                                repeat(maxStars - rating) {
                                    // 빈 별 아이콘
                                    Icon(painter = painterResource(id = R.drawable.star_24px), contentDescription = null, tint = MainColor, modifier = Modifier.size(16.dp))
                                }
                            }
                    }
                }
            }
            Text(text = date, fontSize = 12.sp, color = Color.Gray)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.weight(3f)
            ){
                Text(text = productName, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = content, fontSize = 14.sp, lineHeight = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                LikeLionProductImage(
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    imgUrl = reviewImages,
                    size = 100.dp,
                    fixedImage = R.drawable.marcshop_logo
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    if (isLiked) likesCount++ else likesCount--
                },
                modifier = Modifier.size(24.dp)
            ) { // 클릭 시 상태 변경
                Icon(
                    painter = painterResource(
                        if (isLiked) R.drawable.favorite_fill_24px
                        else R.drawable.favorite_24px
                    ),
                    modifier = Modifier.padding(0.dp)
                        .size(24.dp),
                    contentDescription = "Like Button",
                    tint = if (isLiked) MainColor else Color.LightGray,
                )
            }
            Text(modifier = Modifier.padding(5.dp), text = likesCount.toString(), fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LikeLionDivider()
    }
}
