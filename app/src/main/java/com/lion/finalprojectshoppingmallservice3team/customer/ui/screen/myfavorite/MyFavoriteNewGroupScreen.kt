package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.myfavorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.myfavorite.MyFavoriteNewGroupViewModel
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor

@Composable
fun MyFavoriteNewGroupScreen(viewModel: MyFavoriteNewGroupViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            LikeLionTopAppBar(
                title = "새 그룹 만들기",
                backColor = Color.White,
                navigationIconImage = null,
                menuItems = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.close_24px),
                        borderNull = true,
                        iconButtonOnClick = {
                            viewModel.backAllPop()
                        }
                    )
                }
            )
        }

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(15.dp)
                .padding(it)
        ) {
            LikeLionOutlinedTextField(
                textFieldValue = viewModel.textFieldNameValue,
                label = "그룹명",
                placeHolder = "그룹명을 입력 해주세요",
                singleLine = true,
                onValueChange = {
                    viewModel.updateButtonState()
                },
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "크리에이터(0)")
            Spacer(modifier = Modifier.height(15.dp))

            LikeLionIconButton(
                icon = ImageVector.vectorResource(id = R.drawable.add_24px),
                iconBackColor = MainColor,
                iconColor = Color.White,
                color = Color.White,
                text = "크리에이터 추가",
                fontColor = MainColor,
                fontSize = 20.sp,
                size = 70.dp,
                borderNull = true,
                fillWidth = true,
                iconButtonOnClick = { viewModel.creatorBottom()}
            )
            LazyColumn {
                
            }


        }
    }
}



@Preview
@Composable
fun SimpleComposablePreview() {
    MyFavoriteNewGroupScreen()
}