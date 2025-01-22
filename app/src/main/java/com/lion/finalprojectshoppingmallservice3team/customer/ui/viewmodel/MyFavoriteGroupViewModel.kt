package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyFavoriteGroupViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel()  {
    val shoppingApplication = context as ShoppingApplication

    fun popBack(){
        shoppingApplication.navHostController.popBackStack()
    }

    fun createGroup(){
        shoppingApplication.navHostController.navigate("MyFavoriteNewGroup")
    }
}