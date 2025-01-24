package com.lion.finalprojectshoppingmallservice3team

import android.app.Application

import androidx.navigation.NavHostController
import com.kakao.sdk.common.KakaoSdk

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController

    // 로그인한 사용자 객체
    lateinit var loginCustomerModel:CustomerModel

    override fun onCreate() {
        super.onCreate()

        // 카카오 SDK 초기화
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}