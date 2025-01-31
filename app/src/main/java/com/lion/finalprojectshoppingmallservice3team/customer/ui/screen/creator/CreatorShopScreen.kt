package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import android.R.attr.top
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.CreatorCheerUpList
import com.lion.finalprojectshoppingmallservice3team.Component.CreatorNoticeList
import com.lion.finalprojectshoppingmallservice3team.Component.CreatorReviewList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFixedTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductImage
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductList
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProfileImg
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTextIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorShopViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.MainColor
import me.onebone.toolbar.CollapsingToolbarScaffold
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.data.Storage
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun CreatorShopScreen(
    viewModel: CreatorShopViewModel = hiltViewModel(),
    creator: CustomerModel = CustomerModel()
) {
    val CreatorName by remember { mutableStateOf("크리에이터") }
    val state = rememberCollapsingToolbarScaffoldState()
    val cardState = rememberScrollableState({0.1f})
    var title by remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(true) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        it
        Box{

            CollapsingToolbarScaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                state = state,
                scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
                toolbarModifier = Modifier.background(Color.White),
                enabled = enabled,
                toolbar = {
                    // Collapsing toolbar collapses its size as small as the that of
                    // a smallest child. To make the toolbar collapse to 50dp, we create
                    // a dummy Spacer composable.
                    // You may replace it with TopAppBar or other preferred composable.
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(370.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.55f)
                            .background(Color.White)
                            .scrollable(rememberScrollableState({ 1f }), Orientation.Vertical)

                    ){


                        LikeLionProductImage(
                            imgUrl = creator.customerUserProfileImage,
                            size = 310.dp,
                            fixedImage = R.drawable.product,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.offset(90.dp,0.dp)
                        )
                        Card(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .offset(0.dp, 60.dp)
                                .scrollable(cardState, Orientation.Vertical),
                            shape = RoundedCornerShape(0.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        ) {

                            LikeLionTextIconButton(
                                icon = ImageVector.vectorResource(id = R.drawable.favorite_24px),
                                text = "999+",
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .offset((-10).dp, 0.dp)
                            )
                        }

                        LikeLionProfileImg(
                            imgUrl = creator.customerUserProfileImage,
                            iconTint = Color.White,
                            profileBack = MainColor,
                            profileSize = 100.dp,
                            modifier = Modifier.align(Alignment.BottomStart),
                            offsetX = 20.dp,
                            offsetY = 50.dp
                        )


                    }
                }
            ) {
                if(state.offsetY != 0 ){
                    title = CreatorName
                }
                else
                    title = ""
                // 탭에 따른 화면 변경
                val creatorShopTab = CreatorShopTab.entries[selectedTabIndex]
                when (creatorShopTab) {
                    CreatorShopTab.PRODUCT_LIST -> // 상품 리스트를 2열 그리드로 표시
                    {

                        LikeLionProductList(
                            productList = Storage.products,
                            onCreatorNameClick = null,
                            onItemClick = { /*shopViewModel.listItemImageOnClick(it.productDocumentId) */ },
                            onLikeClick = { /*shopViewModel.onLikeClick(it)*/ },
                            columns = 2,
                            modifier = Modifier
                                .padding(top = 120.dp)
                                .background(Color.White)
                        ) // 상품 화면
                    }

                    CreatorShopTab.CHEER_UP_LIST -> {
                        val list = List(50){ CustomerModel() }
                        CreatorCheerUpList(
                            list,
                            entryPaddingValues = 20.dp,
                            modifier = Modifier
                                .padding(top = 120.dp)
                                .background(Color.White)
                        )
                    }  // 응원하기 화면
                    CreatorShopTab.REVIEW_LIST -> {
                        val reviewList = List(100){""}
                        CreatorReviewList(
                            reviewList,
                            modifier = Modifier
                        )
                    } // 리뷰 화면
                    CreatorShopTab.NOTICE_LIST -> {
                        val noticeList = List(100){""}
                        CreatorNoticeList(
                            noticeList,
                            modifier = Modifier,

                        )
                    } // 크리에이터 공지사항 화면
                    else -> {

                    } // 에러
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ){
                    Column {

                        Spacer(Modifier.height(5.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(start = 18.dp)
                        ) {
                            Text(
                                text = if (title == "") CreatorName
                                else "",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                        }

                        val list = listOf("상품", "응원하기", "리뷰", "소식")
                        LikeLionFixedTabs(
                            tabTitleWithCounts = list,
                            selectedTabIndex = selectedTabIndex,
                            onTabSelected = { index ->
                                selectedTabIndex = index
                            },
                            TabModifier = Modifier
                                .background(Color.White)
                                .padding(top = 30.dp)
                        )
                        HorizontalDivider()
                    }
                }

            }

        }
    }

}

enum class CreatorShopTab(val idx:Int,val selectName: String){
    PRODUCT_LIST(0,"LikeLionProductList"),
    CHEER_UP_LIST(1,"CreatorCheerUpList"),
    REVIEW_LIST(2,""),
    NOTICE_LIST(3,"")
}