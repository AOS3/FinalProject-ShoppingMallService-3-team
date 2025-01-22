package com.lion.finalprojectshoppingmallservice3team.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite.MyFavoriteViewModel

@Composable
fun LikeLionSmallUserView(randomUser: CustomerModel, viewModel: MyFavoriteViewModel = hiltViewModel()){

    Card(  // Card View
        modifier = Modifier
            .padding(10.dp)
            .clickable(
                onClick = {
                    viewModel.creatorShopScreen()
                }
            ), // fillMaxWidth로 가로를 꽉 차게
        colors = CardDefaults.cardColors(
            Color.White
        ),
    ) {
        Column(modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
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
                fontSize = 12.sp,
            )

        }

    }
}

@Composable
fun LikeLionSmallUserListView(randomUsers: List<CustomerModel> ,  viewModel: MyFavoriteViewModel = hiltViewModel()){
    // LazyColumn은 RecyclerView와 유사하다.
    LazyRow(){
        items(randomUsers.size) { idx ->
            LikeLionSmallUserView(randomUsers[idx])
        }
    }
}