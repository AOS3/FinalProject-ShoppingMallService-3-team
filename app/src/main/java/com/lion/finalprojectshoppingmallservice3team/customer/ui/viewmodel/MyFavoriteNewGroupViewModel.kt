package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bumptech.glide.Glide.init
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyFavoriteNewGroupViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel()  {
    val shoppingApplication = context as ShoppingApplication

    val textFieldNameValue = mutableStateOf("")

    val isButtonEnabled = mutableStateOf(false)

    val bottomSheet = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value = textFieldNameValue.value.isNotBlank()
    }

    fun backAllPop(){
        shoppingApplication.navHostController.popBackStack()
        shoppingApplication.navHostController.popBackStack("myFavoriteGroup",true)
    }

    fun creatorBottom(){
        shoppingApplication.navHostController.navigate("MyFavoriteBottom")
    }

}