package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorNoticeViewModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorShopViewModel

@Composable
fun CreatorNoticeView(
    noticeModel: String,
    viewModel: CreatorShopViewModel = hiltViewModel()
){
    val backColors = Color(0xFFFFFFFF)
    Card(
        modifier = Modifier.padding(bottom = 5.dp),
        colors = CardDefaults.cardColors(
            backColors
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = {
                    viewModel.navigation()
                })
        ) {
            Column(
                modifier = Modifier.padding(top = 5.dp, start = 13.dp, end = 20.dp, bottom = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "제목",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "2025-01-09",
                        fontSize = 12.sp,
                        color = Color(0xFF919191)
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "크리에이터가 하고싶은 말 아무거나 마구 적어",
                        maxLines =1,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Spacer(Modifier.height(10.dp))
                LikeLionImageBitmap(
                    imageBitmap = ImageBitmap.imageResource(R.drawable.product),
                )
                Spacer(Modifier.height(10.dp))
                Row {
                    LikeLionTextIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.visibility_24px),
                        text = "999+",
                        onClick = null,
                        iconSize = 21.dp
                    )
                    Text(text = "·")
                    LikeLionTextIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                        text = "999+",
                        iconSize = 25.dp
                    )
                }

            }
        }
    }
    HorizontalDivider()
    Spacer(Modifier.height(30.dp))
}

@Composable
fun CreatorNoticeList(
    noticeList: List<String>,
    topPadding:Dp = 120.dp,
    modifier: Modifier,
    entryPaddingValues: Dp = 30.dp,
    bottomPaddingValues: Dp = 30.dp,
    viewModel: CreatorShopViewModel = hiltViewModel()
){
    val size =noticeList.size
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topPadding)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = entryPaddingValues, start = 15.dp, end = 15.dp, bottom = bottomPaddingValues)
        ) {
            items(size){ position ->
                CreatorNoticeView(
                    noticeList[position]
                )
            }
        }
    }
}

