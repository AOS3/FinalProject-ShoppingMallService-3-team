package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    val textFieldLoginIdValue = mutableStateOf("")
    val textFieldLoginPwValue = mutableStateOf("")

    val isButtonEnabled = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value = textFieldLoginIdValue.value.isNotBlank() &&
                                textFieldLoginPwValue.value.isNotBlank()
    }
}