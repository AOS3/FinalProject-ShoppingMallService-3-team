package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lion.a02_boardcloneproject.component.LikeLionIconButton
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedTextField
import com.lion.a02_boardcloneproject.component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.a02_boardcloneproject.component.LikeLionSearchBar
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.SearchViewModel
import com.lion.finalprojectshoppingmallservice3team.ui.theme.FinalProjectShoppingMallService3teamTheme

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {


    // FocusManager 가져오기
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable {focusManager.clearFocus()}
        ) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically

            ) {
                // LikeLionOutlnedTextField를 검색창으로 사용
                LikeLionOutlinedTextField(
                    textFieldValue = viewModel.searchValue,
                    label = "",
                    placeHolder = "마플샵에서 찾아보는 나만의 취향!",
                    trailingIconMode = LikeLionOutlinedTextFieldEndIconMode.TEXT, // 우측에 지우기 버튼 추가
                    singleLine = true,
                    onValueChange = { newValue ->
                        viewModel.searchValue.value = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        val trimmedValue = viewModel.searchValue.value.trim()
                        if (trimmedValue.isNotEmpty()) {
                            viewModel.addSearchQuery(trimmedValue)
                            focusManager.clearFocus()
                            navController.navigate("searchSuccess")
                        } else {
                            navController.navigate("searchFail")
                        }
                    }),
                    modifier = Modifier
                        .weight(1f)
                )

                LikeLionIconButton(
                    icon = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                    text = "",
                    iconSize = 30.dp,
                    size = 35.dp,
                    padding = 10.dp,
                    iconButtonOnClick = {
                        navController.popBackStack()
                    },
                    borderNull = true,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "최근 검색어", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                // ChipGroup으로 최근 검색어 표시
                LikeLionChipGroup(
                    modifier = Modifier.fillMaxWidth(),
                    elements = viewModel.recentSearches.map { ChipState(it, mutableStateOf(false)) },
                    chipStyle = ChipStyle(
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.surface,
                        chipTextStyle = MaterialTheme.typography.bodyMedium,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    onChipClicked = { content, isSelected, index ->
                        println("Clicked: $content, Selected: $isSelected, Index: $index")
                    },
                    onDeleteButtonClicked = { _, index ->
                        viewModel.removeSearchQuery(index) // 칩 삭제 처리
                    }
                )
            }
        }
    }
}



//@Preview
//@Composable
//fun SearchPreview(){
//    FinalProjectShoppingMallService3teamTheme {
//        SearchScreen(navController.pop)
//    }
//}


