package com.lion.finalprojectshoppingmallservice3team

import android.app.Application
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.mutableStateListOf

import androidx.navigation.NavHostController
import com.kakao.sdk.common.KakaoSdk

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.MutableStateFlow

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController
    // 로그인한 사용자 객체
    lateinit var loginCustomerModel:CustomerModel

    // 로그인했는지
    var isLoggedIn = MutableStateFlow(false)

    override fun onCreate() {
        super.onCreate()

        // 카카오 SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
    // 최근 검색어 목록 관리
    val recentSearches = mutableStateListOf<String>()
}