package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

@Composable
fun LikeLionBigUserView(randomUser: CustomerModel){

    Card(  // Card View
        modifier = Modifier
            .fillMaxWidth().padding(10.dp), // fillMaxWidth로 가로를 꽉 차게
        colors = CardDefaults.cardColors(
            Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                LikeLionProfileImg(
                    imgUrl = randomUser.customerUserProfileImage,
                    iconTint = Color.White,
                    profileBack = Color(0xFFA16DEB)
                )

                Column(
                    modifier = Modifier.padding(start = 25.dp)
                ) {
                    Text(
                        text = randomUser.customerUserNickName,
                        style = typography.bodyMedium
                    )
                    Text(
                        text = randomUser.customerUserNickName,
                        style = typography.bodySmall
                    )
                    LikeLionTextIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                        text = "999+",
                        modifier = Modifier.align(Alignment.End).offset((-10.dp), 0.dp)
                    )

                }
            }

            val Img = List<String>(4){" "}
            LikeLionProductListView(Img,80.dp)
        }

    }
}

@Composable
fun LikeLionBigUserListView(randomUsers: List<CustomerModel>){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyColumn(){
        items(randomUsers.size) { idx ->
            LikeLionBigUserView(randomUsers[idx])
        }
    }
}