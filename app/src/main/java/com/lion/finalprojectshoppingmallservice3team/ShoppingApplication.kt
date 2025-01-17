package com.lion.finalprojectshoppingmallservice3team

import android.app.Application
import androidx.navigation.NavHostController
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController
}