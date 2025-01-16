package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.text
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.lion.finalprojectshoppingmallservice3team.customer.ui.data.model.Customer

@Composable
fun LikeLionSmallUserView(randomUser: Customer){

    Card(  // Card View
        modifier = Modifier
            .padding(10.dp), // fillMaxWidth로 가로를 꽉 차게
        colors = CardDefaults.cardColors(
            Color.White
        ),
    ) {
        Column(modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

//            Box(
//                modifier = Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(Color.Red)
//            ) {}

                LikeLionProfileImg(
                    imgUrl = randomUser.customerUserProfileImage,
                    iconTint = Color.White,
                    profileBack = Color(0xFFA16DEB)
                )


            Text(
                text = randomUser.customerUserNickName,
                style = typography.titleMedium
            )
        }

    }
}

@Composable
fun LikeLionSmallUserListView(randomUsers: List<Customer>){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyRow(){
        items(randomUsers.size) { idx ->
            LikeLionSmallUserView(randomUsers[idx])
        }
    }
}