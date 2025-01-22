package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionSmallUserListView
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLonPostListView
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite.MyFavoriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyFavoriteScreen(
    viewModel: MyFavoriteViewModel = hiltViewModel()
){

    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                navigationIconImage = null,
                title = "MyFavorite",
                backColor = Color.White,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        padding = 10.dp,
                        borderNull = true
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        padding = 10.dp,
                        borderNull = true
                    )
                }
            )
        },
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                LikeLionChipGroup(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(0.9f),
                    elements = viewModel.chipElements,
                    chipStyle =  viewModel.chipState,
                    onChipClicked = {content,isSelected,idx ->
                        viewModel.setEvent(idx)
                    }
                )
                LikeLionIconButton(
                    icon = ImageVector.vectorResource(id = R.drawable.menu_24px),
                    borderNull = true,
                    iconButtonOnClick = {
                        viewModel.myGroupScreen()
                    }
                )
            }


            var list: MutableList<CustomerModel> = MutableList(10, { CustomerModel() })

            LikeLionSmallUserListView(list)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "새글",
                    modifier = Modifier.fillMaxWidth(0.8f).padding(start = 20.dp)
                )
                Text(
                    text = "더보기",
                    modifier = Modifier.padding(end = 15.dp).clickable {

                    }
                )
            }

            LikeLonPostListView(list)

            //LikeLionBigUserListView(list)
        }

    }
}