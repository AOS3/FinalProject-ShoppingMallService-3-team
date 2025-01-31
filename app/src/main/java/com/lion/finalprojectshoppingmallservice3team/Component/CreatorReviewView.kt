package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.contentDescription
import android.R.attr.text
import android.R.attr.top
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.AggregateField.count
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import org.checkerframework.checker.units.qual.h

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorReviewView(reviewModel: String) {
    val backColors = Color(0xFFFFFFFF)

    var value by remember { mutableFloatStateOf(0.5f) }
    val starSize = 22
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

                })
        ) {
            Column(
                modifier = Modifier.padding(top = 5.dp, start = 13.dp, end = 20.dp, bottom = 5.dp)
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
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp),
                    ) {
                        Text(
                            text = "사용자",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )

                        StarSlider(
                            value = value,
                            starSize = starSize,
                            enabled = true
                        )
                    }

                    Text(
                        text = "2025-01-09",
                        fontSize = 12.sp,
                        color = Color(0xFF919191)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "크리에이터에게 하고싶은 말 아무거나 마구 적어",
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(end = 15.dp)
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
    HorizontalDivider()
    Spacer(Modifier.height(25.dp))
}

@Composable
fun CreatorReviewList(
    reviewList: List<String>,
    topPadding:Dp = 120.dp,
    modifier: Modifier,
    entryPaddingValues: Dp = 0.dp,
    bottomPaddingValues: Dp = 30.dp,
){
    val size =reviewList.size
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = topPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(rememberScrollableState { 1f }, Orientation.Vertical)
        ) {
            Spacer(Modifier.height(25.dp))
            Text(
                text = "리뷰 $size",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            )
        }

        Log.d("st","$size")
        LazyColumn(
            contentPadding = PaddingValues(top = entryPaddingValues, start = 15.dp, end = 15.dp, bottom = bottomPaddingValues)
        ) {
            items(size){ position ->
                CreatorReviewView(reviewList[position])
            }
        }
    }
}