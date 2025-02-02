package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

@Composable
fun LikeLionBigUserView(
    randomUser: CustomerModel,
    modifier: Modifier = Modifier
){

    Card(  // Card View
        modifier = modifier
            .fillMaxWidth().padding(10.dp), // fillMaxWidth로 가로를 꽉 차게
        colors = CardDefaults.cardColors(
            Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .clickable(
                onClick = {

                }
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
                        modifier = Modifier.padding(start = 25.dp).fillMaxSize()
                    ) {
                        Text(
                            text = "randomUser.customerUserNickName",
                            style = typography.bodyMedium
                        )
                        Text(
                            text = "randomUser.customerUserNickName",
                            style = typography.bodySmall
                        )
                        LikeLionTextIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                            text = "999+",
                        )
                    }

                }

                val Img = List<String>(4){" "}
                LikeLionProductListView(Img,80.dp)
            }
        }


    }
}

@Composable
fun LikeLionBigUserListView(
    randomUsers: List<CustomerModel>,
    modifier: Modifier = Modifier,
    entryPaddingValues: Dp = 0.dp,
    bottomPaddingValues: Dp = 0.dp,
    cardModifier: Modifier = Modifier
){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = entryPaddingValues, bottom = bottomPaddingValues)
    ){
        items(randomUsers.size) { idx ->
            LikeLionBigUserView(randomUsers[idx],cardModifier)
        }
    }
}