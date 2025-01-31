package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTextIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorNoticeViewModel

@Composable
fun CreatorNoticeScreen(
    viewModel: CreatorNoticeViewModel = hiltViewModel(),
    noticeModel: CustomerModel = CustomerModel()
) {
    var title by remember { mutableStateOf("크리에이터") }
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = title,
                backColor = Color(0x00FFFFFF),
                navigationIconImage = Icons.AutoMirrored.Filled.ArrowBack,
                navigationIconOnClick = {
                    viewModel.popBack()
                },
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        iconButtonOnClick = {
                            viewModel.popBack()
                        },
                        color = Color(0x00FFFFFF),
                    )

                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                        iconButtonOnClick = {
                            viewModel.popBack()
                        },
                        color = Color(0x00FFFFFF),
                    )
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier.padding(20.dp)
            ){
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
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        it

        Column(
            modifier = Modifier.padding(it)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "제목",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(20.dp))

                Text(
                    text = "크리에이터가 하고싶은 말 아무거나 마구 적어",
                    maxLines =1,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth().border(1.dp,Color.Black)
                )
            }

        }
    }


}