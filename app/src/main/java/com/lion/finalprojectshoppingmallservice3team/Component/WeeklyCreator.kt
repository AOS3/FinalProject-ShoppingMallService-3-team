package com.lion.finalprojectshoppingmallservice3team.Component

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import okhttp3.internal.wait
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Calendar
import java.util.Locale

@Composable
fun WeeklyCreator(
    rank: String,
    title: String,
    subtitle: String,
    imageUrl: String,
    items: MutableList<String>,
    navigationIconOnClick: () -> Unit = {},
    onSeeMoreClick: () -> Unit = {}
) {

    // 현재 월과 주 계산
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1
    val currentWeek = calendar.get(Calendar.WEEK_OF_MONTH)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // 상단 텍스트 (동적으로 월과 주 표시)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "${currentMonth}월 ${currentWeek}주차 인기 크리에이터 랭킹",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "WEEKLY RANKING",
                    style = TextStyle(
                        fontSize = 26.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.weight(1f)) // 텍스트를 오른쪽으로 밀기

                Text(
                    text = "See More",
                    style = TextStyle(fontSize = 12.sp, color = Color.Gray),
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable { onSeeMoreClick() } // 클릭 이벤트 처리
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            onClick = navigationIconOnClick
        ) {
            Box {
                // 블러 처리된 배경 이미지 추가
                if (imageUrl.isNotEmpty()) {

                    LikeLionProductImage(
                        imgUrl = imageUrl,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp),
                        size = 500.dp
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {


                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, top = 8.dp),

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {

                    // 랭킹 텍스트
                    Text(
                        text = "WEEKLY RANKING",
                        style = TextStyle(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "TOP $rank",
                        style = TextStyle(fontSize = 34.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

//                Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),

                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // 상단 랭킹 및 하트 이미지
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(250.dp)
                                .clip(CircleShape)

                        ) {
                            LikeLionProfileImg(
                                imgUrl = imageUrl,
                                iconTint = Color.Transparent,
                                profileBack = Color.Transparent,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }


                    Column(
//                    modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        // 제목 및 부제목
                        Text(
                            text = title,
                            style = TextStyle(fontSize = 26.sp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = subtitle,
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


//                     하단 아이템 리스트 (이미지 버튼들)
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        ) {
                        items.forEach { item ->

                            LikeLionProductImage(
                                imgUrl = item,
                                size = 80.dp,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        navigationIconOnClick()
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

