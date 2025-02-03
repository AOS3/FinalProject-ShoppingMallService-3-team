package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import android.util.Log
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionChipGroup
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionIconButton
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextField
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionOutlinedTextFieldEndIconMode
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.SearchViewModel

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
                .clickable(
                    onClick = {focusManager.clearFocus()},
                    interactionSource = remember{ MutableInteractionSource() },
                    indication = null,
                )
        ) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
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
                    padding = 10.dp,
                    iconButtonOnClick = {
                        navController.popBackStack()
                    },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Log.d("st","${viewModel.shoppingApplication.recentSearches}")
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "최근 검색어", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                // ChipGroup으로 최근 검색어 표시
                LikeLionChipGroup(
                    modifier = Modifier.fillMaxWidth(),
                    elements = viewModel.shoppingApplication.recentSearches.map { ChipState(it, mutableStateOf(false)) },
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


