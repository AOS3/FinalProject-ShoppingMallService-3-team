package com.lion.finalprojectshoppingmallservice3team

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController
}