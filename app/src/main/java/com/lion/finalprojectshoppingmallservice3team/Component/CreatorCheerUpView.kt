package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

@Composable
fun CreatorCheerUpView(customerModel: CustomerModel) {
    val backColors = Color(0xFFF5F5F5)
    Card(
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 30.dp),
        colors = CardDefaults.cardColors(
            backColors
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = {

                })
        ) {
            Column(
                modifier = Modifier.padding(top = 15.dp, start = 13.dp, end = 20.dp, bottom = 17.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {
                    LikeLionProfileImg(
                        imgUrl = "",
                        iconTint = Color.White,
                        profileBack = MainColor,
                        profileSize = 30.dp
                    )
                    Text(
                        modifier = Modifier.weight(1f).padding(start = 10.dp),
                        text = "사용자",
                        fontSize = 15.sp
                    )
                    Text(
                        text = "2025-01-09",
                        fontSize = 12.sp,
                        color = Color(0xFF919191)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth().height(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "크리에이터에게 하고싶은 말 아무거나 마구 적어",
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(0.7f).padding(end = 15.dp)
                    )
                    Row(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp))
                    ) {
                        LikeLionImageBitmap(
                            imageBitmap = ImageBitmap.imageResource(R.drawable.product),
                        )
                    }

                }
                LikeLionTextIconButton(
                    icon = ImageVector.vectorResource(R.drawable.favorite_24px),
                    text = "999+",
                    modifier = Modifier,
                    containerColor = backColors
                )

            }
        }

    }
}

@Composable
fun CreatorCheerUpList(
    list:List<CustomerModel>,
    entryPaddingValues: Dp = 0.dp,
    bottomPaddingValues: Dp = 10.dp,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.fillMaxWidth()
            .scrollable(rememberScrollableState { 1f }, Orientation.Vertical)
        ) {
            Spacer(Modifier.height(14.dp))
            Text("크리에이터를 응원하는 한마디")
            Spacer(Modifier.height(10.dp))
            Row {
                Text(modifier = Modifier.fillMaxWidth().weight(1f),
                    text = "CHEER UP",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
                LikeLionIconButton(
                    icon = ImageVector.vectorResource(R.drawable.edit_24px),
                    color = MainColor,
                    iconColor = Color.White,
                    iconButtonOnClick = {

                    },
                    size = 45.dp,
                    modifier = Modifier.size(58.dp,45.dp)
                )
            }
            Spacer(Modifier.height(10.dp))
        }

        LazyColumn(
            contentPadding = PaddingValues(top = entryPaddingValues, bottom = bottomPaddingValues)
        ) {
            items(list.size){ position ->
                CreatorCheerUpView(list[position])
            }
        }
    }

}