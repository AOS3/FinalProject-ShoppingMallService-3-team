package com.lion.finalprojectshoppingmallservice3team.Component

import android.R.attr.text
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

@Composable
fun LikeLonPostView(randomUser: CustomerModel){

    Card(  // Card View
        modifier = Modifier
            .size(width = 300.dp, height = 180.dp).padding(10.dp), // fillMaxWidth로 가로를 꽉 차게
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
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Row {
                    LikeLionProfileImg(
                        imgUrl = randomUser.customerUserProfileImage,
                        iconTint = Color.White,
                        profileBack = Color(0xFFA16DEB),
                        profileSize = 28.dp
                    )

                    Column(
                        modifier = Modifier.padding(start = 15.dp)
                    ) {
                        Text(
                            text = "사용자",
                            style = typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "2025-01-26",
                            style = typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                Spacer(Modifier.height(3.dp))

                    Row {
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "제목",
                                style = typography.bodyMedium,
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.55f),
                                text = "내용",
                                style = typography.bodyMedium,
                                fontWeight = FontWeight.Thin,
                            )
                        }
                    }

                    Spacer(Modifier.height(5.dp))
                    Row {
                        LikeLionTextIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.visibility_24px),
                            text = "999+",
                            onClick = null,
                            iconSize = 21.dp
                        )
                        Text(text = "·")
                        LikeLionTextIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                            text = "999+",
                            iconSize = 25.dp
                        )
                    }
            }

        }
    }
}

@Composable
fun LikeLonPostListView(randomUsers: List<CustomerModel>){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyRow (){
        items(randomUsers.size) { idx ->
            LikeLonPostView(randomUsers[idx])
        }
    }
}