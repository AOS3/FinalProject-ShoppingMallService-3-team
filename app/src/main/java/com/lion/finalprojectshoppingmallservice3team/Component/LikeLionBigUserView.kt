package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.lion.a02_boardcloneproject.component.LikeLionIconButton
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.data.model.Customer

@Composable
fun LikeLionBigUserView(randomUser: Customer){

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row {
                LikeLionProfileImg(
                    imgUrl = randomUser.customerUserProfileImage,
                    iconTint = Color.White,
                    profileBack = Color(0xFFA16DEB)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(
                        text = randomUser.customerUserNickName,
                        style = typography.bodyMedium
                    )
                    Text(
                        text = randomUser.customerUserNickName,
                        style = typography.bodySmall
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                    )

                }
            }


        }

    }
}

@Composable
fun LikeLionBigUserListView(randomUsers: List<Customer>){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyColumn(){
        items(randomUsers.size) { idx ->
            LikeLionBigUserView(randomUsers[idx])
        }
    }
}