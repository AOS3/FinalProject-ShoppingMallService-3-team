package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionGroupEditListView
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite.MyFavoriteGroupViewModel

@Composable
fun MyFavoriteGroupScreen(viewModel: MyFavoriteGroupViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = "MY 그룹 편집",
                backColor = Color.White,
                navigationIconImage = null,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.close_24px),
                        iconButtonOnClick = {
                            viewModel.popBack()
                        }
                    )
                }

            )
        },
        modifier = Modifier.background(Color.White)
    ) {
        it
        val myGroups = List<String>(5){"text"}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LikeLionGroupEditListView(myGroups)
            LikeLionFilledButton(
                text = "+ 새그룹 만들기",
                onClick = {
                    viewModel.createGroup()
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }
}