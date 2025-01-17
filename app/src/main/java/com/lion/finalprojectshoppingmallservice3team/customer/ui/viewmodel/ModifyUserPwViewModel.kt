package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ModifyUserPwViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    val textFieldModifyCurrentPwValue = mutableStateOf("")
    val textFieldModifyNewPwValue = mutableStateOf("")
    val textFieldModifyCheckPwValue = mutableStateOf("")

    fun menuCloseOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun modifyCancelOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun modifySuccessOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }
}