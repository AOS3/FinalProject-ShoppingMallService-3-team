package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lion.finalprojectshoppingmallservice3team.Component.DottedOutlineIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFilledButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheerUpWriteScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "응원하기",
                        style = TextStyle(fontSize = 24.sp)
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        iconButtonOnClick = {

                        },
                    )
                }
            )
        },
        bottomBar = {
            LikeLionFilledButton(
                text = "응원하기",
                containerColor = MainColor,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {

                }
            )
        }
    ){ paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
        ) {

            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                onValueChange = { text = it},
                placeholder = {Text("크리에이터를 위한 응원의 한 마디를 남겨주세요")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .border(1.dp, Color.Gray),
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent, // 포커스 상태의 배경색
                    unfocusedContainerColor = Color.Transparent, // 비포커스 상태의 배경색
                    disabledContainerColor = Color.Transparent, // 비활성화 상태의 배경색
                )
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "이미지 첨부",
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "* 최대 6장 까지 첨부 가능합니다.",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)

                )
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                DottedOutlineIconButton(
                    onClick = {
                    },
                    icon = Icons.Default.Add,
                    contentDescription = "Add Image",
                    modifier = Modifier.padding(start = 8.dp)
                )

            }
        }

    }
}


@Preview
@Composable
fun CheerUpScreenPreView(){
    FinalProjectShoppingMallService3teamTheme {
        CheerUpWriteScreen()
    }
}
