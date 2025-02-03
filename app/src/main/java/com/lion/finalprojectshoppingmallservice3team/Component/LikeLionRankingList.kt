package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor


@Composable
fun LikeLionRankingList(creators: List<Creator>) {
    // 상태 관리: 각 크리에이터의 확장 여부를 기억
    val creatorStates = remember { mutableStateListOf(*creators.toTypedArray()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        creatorStates.forEachIndexed { index, creator ->
            LikeLionCreatorCard(
                creator = creator,
                onExpandClick = { isExpanded ->
                    creatorStates[index] = creator.copy(isExpanded = isExpanded)
                }
            )
        }
    }
}

@Composable
fun LikeLionCreatorCard(
    creator: Creator,
    onExpandClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) ,
        colors = CardDefaults.cardColors(if (creator.isExpanded) MainColor else Color.LightGray.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${creator.rank}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(end = 16.dp),
                    color = if (creator.isExpanded) Color.White else Color.Black // 텍스트 색상 변경
                )

                LikeLionProfileImg(
                    imgUrl = creator.imageRes,
                    modifier = Modifier.size(48.dp).clip(CircleShape),
                    iconTint = Color.White,
                    profileBack = Color.LightGray,
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = creator.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (creator.isExpanded) Color.White else Color.Black // 텍스트 색상 변경
                    )
                    Text(text = creator.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (creator.isExpanded) Color.White else Color.Gray // 텍스트 색상 변경
                    )
                }
                IconButton(onClick = { onExpandClick(!creator.isExpanded) }) {
                    Icon(
                        imageVector =
                        if (creator.isExpanded) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                        contentDescription =
                        if (creator.isExpanded) "접기" else "펼치기",
                        tint =
                        if (creator.isExpanded) Color.White else Color.Black
                    )
                }
            }
            // 드롭다운이 활성화되었을 때 추가 콘텐츠 표시
            if (creator.isExpanded) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 최대 4개의 이미지만 표시
                    creator.products.take(4).forEach {  productImageUrl ->
                        LikeLionProductImage(
                            imgUrl = productImageUrl, // 이미지 URL
                            size = 80.dp, // 이미지 크기
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp)) // 모서리 둥글게 처리
                                .clickable {

                                }
                        )
                    }
                }
            }
        }
    }
}

data class Creator(
    val rank: Int,
    val name: String,
    val category: String,
    val imageRes: String,
    val products: List<String>, // 추가 이미지 리스트
    var isExpanded: Boolean = false // 드롭다운 상태
)