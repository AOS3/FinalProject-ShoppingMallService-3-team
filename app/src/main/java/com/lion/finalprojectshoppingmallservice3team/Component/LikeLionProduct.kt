package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.bitmap
import android.R.attr.iconTint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.data.model.Customer

@Composable
fun LikeLionProduct(
    imgUrl: String,
    size:Dp
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
                , contentScale = ContentScale.Fit
                , contentDescription = null
                , modifier = Modifier
                    .size(size).clip(RectangleShape)
            )
        } ?: Image(bitmap = ImageBitmap.imageResource(R.drawable.product)
            , contentScale = ContentScale.Fit
            , contentDescription = null
            , modifier = Modifier
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
            LikeLionProduct(randomImg[idx], size)
        }
    }
}