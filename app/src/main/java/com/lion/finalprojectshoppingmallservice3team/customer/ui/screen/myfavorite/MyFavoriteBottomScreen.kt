package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProfileImg
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite.MyFavoriteBottomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavoriteBottomScreen(viewModel: MyFavoriteBottomViewModel = hiltViewModel()){
    val items = List(20){ CustomerModel() }
    val bottomBackColor = Color(0xFFD9D9D9)
    Scaffold(
        containerColor = bottomBackColor
    ){
        Scaffold(
            topBar = {
                LikeLionBottomSheetTopAppBar(
                    title = "좋아요한 크리에이터",
                    backColor = Color.White,
                    navigationIconImage = null,
                    menuItems = {
                        LikeLionIconButton(
                            icon = ImageVector.vectorResource(id = R.drawable.close_24px),
                            borderNull = true,
                            iconButtonOnClick = {
                                viewModel.popBack()
                            }
                        )
                    },
                )
            },
            modifier = Modifier.padding(it),
            contentWindowInsets = WindowInsets(top = 0.dp),
            containerColor = bottomBackColor
        ) {

            LazyColumn(modifier = Modifier.padding(it).background(Color.White)) {
                items(items.size){ idx ->
                    CreatorList(items[idx])
                }
            }
        }


    }



}

@Composable
fun CreatorList(randomUser: CustomerModel){
    Row(
        modifier = Modifier.padding(top = 10.dp).fillMaxWidth()
    ) {
        Box(
            Modifier.padding(top = 7.dp, bottom = 7.dp)
        ){
            LikeLionProfileImg(
                imgUrl = randomUser.customerUserProfileImage,
                iconTint = Color.White,
                profileBack = Color(0xFFA16DEB),
                profileSize = 45.dp,
            )
        }


        Column(
            modifier = Modifier.padding(start = 25.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = randomUser.customerUserNickName,
                style = typography.bodyMedium,
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = randomUser.customerUserNickName,
                style = typography.bodySmall
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeLionBottomSheetTopAppBar(
    title:String = "",
    backColor:Color,
    navigationIconImage:ImageVector? = null,
    navigationIconOnClick:() -> Unit = {},
    menuItems : @Composable RowScope.() -> Unit = {},
){
    TopAppBar(
        // 타이틀
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(backColor),
        // 네비게이션 아이콘
        navigationIcon = if(navigationIconImage == null){
            {}
        } else {
            {
                IconButton(
                    onClick = navigationIconOnClick
                ) {
                    Icon(
                        imageVector = navigationIconImage,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            menuItems()
        },
        windowInsets = WindowInsets(top = 0.dp),
        modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    )
}