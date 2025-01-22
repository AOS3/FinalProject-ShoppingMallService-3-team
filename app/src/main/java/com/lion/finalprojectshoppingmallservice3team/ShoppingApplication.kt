package com.lion.finalprojectshoppingmallservice3team

import android.app.Application
import androidx.navigation.NavHostController

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShoppingApplication : Application(){
    // 네비게이션
    lateinit var navHostController: NavHostController
  
    // 로그인한 사용자 객체
    lateinit var loginCustomerModel:CustomerModel
}