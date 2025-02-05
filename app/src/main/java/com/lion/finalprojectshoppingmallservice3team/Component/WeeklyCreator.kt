package com.lion.finalprojectshoppingmallservice3team.Component

import android.graphics.Bitmap
import android.net.Uri
import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.res.painterResource
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
    @DrawableRes drawableRes: Int? = null,
    @DrawableRes subDrawableRes: Int? = null,
    items: MutableList<WeeklyItem> = mutableListOf(),
    navigationIconOnClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    onSeeMoreClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            onClick = navigationIconOnClick
        ) {
            Box {
                // 배경 이미지: drawableRes가 있으면 drawable 리소스 사용, 없으면 imageUrl로 로드
                if (subDrawableRes != null) {
                    Image(
                        painter = painterResource(id = subDrawableRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(16.dp)
                            .size(500.dp),
                        contentScale = ContentScale.FillBounds,
                    )
                } else if (imageUrl.isNotEmpty()) {
                    // 네트워크 또는 URL로 이미지를 로드하는 함수 (ex. Coil을 사용하는 LikeLionProductImage)
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
                    // 필요한 경우 중앙에 다른 콘텐츠 표시
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, top = 8.dp),
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

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // 프로필 이미지 (동일하게 drawableRes가 있으면 drawable 리소스, 없으면 네트워크 URL)
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(250.dp)
                                .clip(CircleShape)
                        ) {
                            if (drawableRes != null) {
                                Image(
                                    painter = painterResource(id = drawableRes),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else if (imageUrl.isNotEmpty()) {
                                LikeLionProfileImg(
                                    imgUrl = imageUrl,
                                    iconTint = Color.Transparent,
                                    profileBack = Color.Transparent,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    Column(
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

                    // 하단 아이템 리스트 (이미지 버튼들)
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        items.forEach { item ->
                            if (item.drawableRes != null) {
                                Image(
                                    painter = painterResource(id = item.drawableRes),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { navigationIconOnClick() }
                                )
                            } else if (item.imageUrl.isNotEmpty()) {
                                LikeLionProductImage(
                                    imgUrl = item.imageUrl,
                                    size = 80.dp,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { navigationIconOnClick() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class WeeklyItem(
    val imageUrl: String = "",
    @DrawableRes val drawableRes: Int? = null
)

