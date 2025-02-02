package com.lion.finalprojectshoppingmallservice3team

import android.app.Application
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.mutableStateListOf

import androidx.navigation.NavHostController

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController
    // 로그인한 사용자 객체
    lateinit var loginCustomerModel:CustomerModel
    // 최근 검색어 목록 관리
    val recentSearches = mutableStateListOf<String>()
}