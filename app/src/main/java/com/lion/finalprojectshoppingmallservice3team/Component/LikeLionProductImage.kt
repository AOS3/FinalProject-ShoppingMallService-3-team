package com.lion.finalprojectshoppingmallservice3team.Component

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lion.finalprojectshoppingmallservice3team.R

@Composable
fun LikeLionProductImage(
    imgUrl: String,
    size:Dp,
    fixedImage : Int = R.drawable.product,
    contentScale: ContentScale = ContentScale.Inside,
    modifier: Modifier = Modifier
) {
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(LocalContext.current)
        .asBitmap() // 뭘로 변활 할 것?
        .load(imgUrl) // 어디서 가지고 올 것?
        .into(object : CustomTarget<Bitmap>(){  // 어디에 넣을 것?
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                // resource가 다운 받은 이미지
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
    // bitmap에 데이터가 있다면? -> 이미지를 다운 받았다면
    bitmap.value?.asImageBitmap()?.let {
        Image(bitmap = it
            , contentScale = contentScale
            , contentDescription = null
            , modifier = modifier
                .size(size).clip(RectangleShape)
        )
    } ?: Image(bitmap = ImageBitmap.imageResource(fixedImage)
        , contentScale = contentScale
        , contentDescription = null
        , modifier = modifier
            .size(size).clip(RectangleShape)
    )
}

@Composable
fun LikeLionProductListView(randomImg: List<String>,size:Dp){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyRow(
        modifier = Modifier.padding(start = 20.dp)
    ){
        items(randomImg.size) { idx ->
            LikeLionProductImage(randomImg[idx], size)
        }
    }
}