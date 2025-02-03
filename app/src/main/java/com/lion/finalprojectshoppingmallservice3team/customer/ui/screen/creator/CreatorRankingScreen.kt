package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import com.lion.finalprojectshoppingmallservice3team.Component.Creator
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionCreatorProductList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionRankingList
import com.lion.finalprojectshoppingmallservice3team.Component.WeeklyCreator
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorRankingViewModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.shop.ShopViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme
import java.util.Calendar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorRankingScreen(
    shopViewModel: ShopViewModel = hiltViewModel(),
    creatorRankingViewModel: CreatorRankingViewModel = hiltViewModel()
) {
    val filteredProducts by shopViewModel.filteredProductList.collectAsState()

    LaunchedEffect(Unit) {
        shopViewModel.loadProductList()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Creator Ranking",
                        style = TextStyle(fontSize = 24.sp)
                    )
                },
                navigationIcon = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.arrow_back_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {
                            creatorRankingViewModel.popBack()
                        },
                        borderNull = true,
                    )
                },
                actions = {
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {
//                            navController.navigate("search")
                        },
                        borderNull = true,
                    )
                    LikeLionIconButton(
                        icon = ImageVector.vectorResource(id = R.drawable.baseline_shopping_cart_24),
                        color = Color.Transparent,
                        iconBackColor = Color.Transparent,
                        iconButtonOnClick = {

                        },
                        borderNull = true,
                    )
                },
            )
        },

        ) { paddingValues ->

        val sampleCreators = listOf(
            Creator(
                rank = 2,
                name = "싸이코드 하루토",
                category = "유튜버",
                imageRes = "",
                products = listOf("https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg","https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg","https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg","https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",)
            ),
            Creator(
                rank = 3,
                name = "싸이코드 연이",
                category = "크리에이터",
                imageRes = "",
                products = listOf("https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg","https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",),
                isExpanded = false // 기본적으로 닫힘 상태
            )
        )

        // 2. NestedScroll 연결 객체 생성
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                    // 스크롤 이벤트 전파 로직
                    return super.onPostScroll(consumed, available, source)
                }
            }
        }

        val scrollState = rememberScrollState()

        // 현재 월과 주 계산
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1
        val currentWeek = calendar.get(Calendar.WEEK_OF_MONTH)

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .nestedScroll(nestedScrollConnection)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
            ) {


                Text(
                    text = "크리에이터 인기 랭킹",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = "WEEKLY",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp, end = 16.dp),
                        text = "${currentYear}년 ${currentMonth}월 ${currentWeek}주차",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                WeeklyCreator(
                    rank = "1",
                    title = "허블사무소",
                    subtitle = "유튜버",
                    imageUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                    items = mutableListOf(
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
                        "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg"
                    )
                )
            }


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .nestedScroll(nestedScrollConnection),
                ) {
                    LikeLionCreatorProductList(
                        productList = filteredProducts,
                        onCreatorNameClick = { /*크리에이터 화면으로 이동*/ },
                        onItemClick = { shopViewModel.listItemImageOnClick(it.productDocumentId) },
                        onLikeClick = { shopViewModel.onLikeClick(it) },
                        rows = 1
                    )

                }
            }
                LikeLionRankingList(creators = sampleCreators)
        }
    }
}


@Preview
@Composable
fun CreatorRankingScreenPreView() {
    FinalProjectShoppingMallService3teamTheme {
        CreatorRankingScreen()
    }
}