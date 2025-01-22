package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionImage
import com.lion.finalprojectshoppingmallservice3team.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFailScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.arrow_back_24px),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        iconButtonOnClick = {
                            navController.popBackStack()
                        },
                        borderNull = true,
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        iconButtonOnClick = {
//                            navController.navigate("search")
                        },
                        borderNull = true,
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_shopping_cart_24),
                        text = "",
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        padding = 10.dp,
                        borderNull = true,
                    )
                }
            )
        },

        )
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LikeLionImage(
                painter = painterResource(R.drawable.marcshop_logo),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.width(150.dp).height(200.dp).padding(bottom = 16.dp),
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "검색 결과가 없어요",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "다른 검색어를 입력하거나\n 철자와 띄어쓰기를 확인해 보세요",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SearchScreenPreView() {
//    FinalProjectShoppingMallService3teamTheme {
//        SearchFailScreen()
//    }
//}