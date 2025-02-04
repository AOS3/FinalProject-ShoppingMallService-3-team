package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen.creator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionBigUserListView
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionDivider
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionFixedTabs
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionSearchBar
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionTopAppBar
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator.CreatorMainViewModel
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.util.stream.Collectors.toList

@Composable
fun CreatorMainScreen(viewModel: CreatorMainViewModel = hiltViewModel()) {
    val state = rememberCollapsingToolbarScaffoldState()

    var enabled by remember { mutableStateOf(true) }
    val defaultPadding = 20.dp
    var titlePadding by remember { mutableStateOf(defaultPadding) }
    MaterialTheme.colorScheme.primary
    var selectedTabIndex by remember { mutableStateOf(0) }
    Log.d("st","${state.offsetY}")
    titlePadding = collapsingState(state,defaultPadding)

    Scaffold(
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
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
                            .height(110.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .background(Color.White)

                    ){
                        LikeLionTopAppBar(
                            title = "Creator",
                            backColor = Color.White,
                            menuItems = {
                                LikeLionIconButton(
                                    icon = ImageVector.vectorResource(id = R.drawable.search_24px),
                                    iconButtonOnClick = {

                                    },
                                    iconBackColor = Color(0x00FFFFFF),
                                )

                                LikeLionIconButton(
                                    icon = ImageVector.vectorResource(id = R.drawable.shopping_cart_24px),
                                    iconButtonOnClick = {

                                    },
                                    iconBackColor = Color(0x00FFFFFF),
                                )
                            }
                        )

                    }
                }
            ) {


                Box{
                    Column{
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(top = titlePadding)
                                .verticalScroll(rememberScrollState()))
                        Row {
                            LikeLionChipGroup(
                                modifier = Modifier.padding(bottom = 5.dp),
                                elements = viewModel.chipElements,
                                chipStyle = viewModel.chipState,
                                onChipClicked = {content,isSelected,idx ->
                                    viewModel.setEvent(idx)
                                }
                            )
                        }
                        if (viewModel.current.value == "기업") {
                            if (viewModel.companySearch.value == "") {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                ) {
                                    //viewModel.companyList.addAll(List(100) { "항목" })
                                    // 검색 뷰

                                    LikeLionSearchBar(
                                        searchValue = viewModel.searchText,
                                        placeholder = "기업명을 입력해주세요",
                                        searchResultComposable = {
                                            LazyColumn {
                                                itemsIndexed(viewModel.companyList.filter {
                                                    it.contains(viewModel.searchText.value)
                                                }) { position, item ->
                                                    val text = buildAnnotatedString {
                                                        val startIndex =
                                                            item.indexOf(viewModel.searchText.value)
                                                        append(
                                                            item.substring(0, startIndex)
                                                        )
                                                        withStyle(
                                                            style = SpanStyle(
                                                                color = Color.Blue,
                                                                fontWeight = FontWeight.Bold
                                                            )
                                                        ) {
                                                            append(
                                                                item.substring(
                                                                    startIndex,
                                                                    startIndex + viewModel.searchText.value.length
                                                                )
                                                            )
                                                        }
                                                        append(
                                                            item.substring(startIndex + viewModel.searchText.value.length)
                                                        )
                                                    }
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .clickable {
                                                                viewModel.clickSystem(viewModel.companyList[position])
                                                            },
                                                    ) {
                                                        Text(
                                                            text = text,
                                                            modifier = Modifier
                                                                .padding(10.dp)
                                                                .fillMaxWidth()
                                                        )
                                                        LikeLionDivider(
                                                            color = Color.LightGray
                                                        )
                                                    }
                                                }
                                            }
                                        },
                                        composableContent = {

                                        },
                                        contentComposable = {
                                            LazyColumn {
                                                items(viewModel.companyList.size) { position ->
                                                    Column(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .clickable {
                                                                viewModel.clickSystem(viewModel.companyList[position])
                                                            },
                                                    ) {
                                                        Text(
                                                            text = viewModel.companyList[position],
                                                            modifier = Modifier
                                                                .padding(10.dp)
                                                                .fillMaxWidth()
                                                        )
                                                        LikeLionDivider(
                                                            color = Color.LightGray
                                                        )
                                                    }
                                                }
                                            }
                                        },
                                        onSearch = {
                                            viewModel.searchSystem()
                                        },
                                    )
                                }
                            } else {
                                Spacer(
                                    Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(top = 20.dp)
                                        .verticalScroll(rememberScrollState())
                                )
                                if (viewModel.companySearch.value.toString() != "") {
                                    Row(
                                        modifier = Modifier.background(Color.White).fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Spacer(Modifier.width(20.dp))
                                        LikeLionIconButton(
                                            icon = ImageVector.vectorResource(R.drawable.search_24px),
                                            iconButtonOnClick = {
                                                viewModel.companySearch.value = ""
                                                viewModel.searchFilterList.value = listOf()
                                            }
                                        )
                                        Log.d("st", viewModel.companySearch.value)
                                        Text(text = viewModel.companySearch.value)

                                    }

                                }
                            }
                        }

                            val tabList = listOf("유튜버", "틱톡커", "포토그래퍼",)
                            LikeLionFixedTabs(
                                tabTitleWithCounts = tabList,
                                selectedTabIndex = selectedTabIndex,
                                onTabSelected = { index ->
                                    selectedTabIndex = index
                                },
                                TabModifier = Modifier
                                    .background(Color.White)
                                    .height(40.dp)
                            )
                            LikeLionDivider()
                            // 탭에 따른 화면 변경
                            when(selectedTabIndex){
                                0 ->{
                                    LikeLionBigUserListView(
                                        if (viewModel.tab.value == "기업")viewModel.searchFilterList.value
                                        else viewModel.creatorList.value,
                                        modifier = Modifier.background(Color(0x0F585759)).fillMaxHeight(),
                                        entryPaddingValues = 20.dp,
                                        bottomPaddingValues = 80.dp,
                                        productService = viewModel.productService
                                    ) // 크리에이터 목록 화면
                                }
                                1 ->{
                                    Row(
                                        modifier = Modifier.fillMaxSize().padding(bottom = 300.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "해당 크리에이터는 없습니다")
                                    }

                                }
                                2 ->{
                                    Row(
                                        modifier = Modifier.fillMaxSize().padding(bottom = 300.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "해당 크리에이터는 없습니다")
                                    }

                                }
                            }





                    }
                }


            }

        }
    }

}

fun collapsingState(state:CollapsingToolbarScaffoldState, defaultPadding:Dp)
        = if(state.offsetY < -200 ){
    val last = 23
    defaultPadding + last.dp
}
else{
    val count = 10
    defaultPadding + (-(state.offsetY)/count).dp
}

@Composable
fun SelectedChipMotion(
    elements: List<ChipState>,
    chipStyle: ChipStyle,
    onChipClicked: (String, Boolean, Int) -> Unit, // 삭제 버튼 콜백 추가
) {
    var selectIndex = 0
    elements.forEachIndexed {thisIndex,it->
        if (it.isSelected.value == true) {
            selectIndex = thisIndex
            return@forEachIndexed
        }
    }
    if (elements[selectIndex].text == "기업"){

    }
}





