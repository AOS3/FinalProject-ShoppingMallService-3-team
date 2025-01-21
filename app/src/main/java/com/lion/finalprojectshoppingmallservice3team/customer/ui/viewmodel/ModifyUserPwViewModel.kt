package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyUserPwViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    val textFieldModifyCurrentPwValue = mutableStateOf("")
    val textFieldModifyNewPwValue = mutableStateOf("")
    val textFieldModifyCheckPwValue = mutableStateOf("")
    val customerid = shoppingApplication.loginCustomerModel.customerUserId

    val isButtonEnabled = mutableStateOf(false)

    // 조건 충족 여부 상태
    // 10자 이상
    val isModifyPwLengthValid = mutableStateOf(false)
    // 아이디랑 같은지
    val isModifyPwContainsIdValid = mutableStateOf(false)
    // 새 비밀번호랑 같은지
    val isModifyPwContainsNewPwValid = mutableStateOf(false)

    init {
        updateButtonState()
    }

    fun updateButtonState() {
        isButtonEnabled.value = textFieldModifyCurrentPwValue.value.isNotEmpty() &&
                isModifyPwLengthValid.value &&
                isModifyPwContainsIdValid.value &&
                isModifyPwContainsNewPwValid.value
    }

    fun modifyNewPwConditions() {
        val newPw = textFieldModifyNewPwValue.value

        // 비밀번호가 10자 이상이고, 영문과 숫자를 포함해야 함
        isModifyPwLengthValid.value = newPw.length >= 10 &&
                newPw.contains(Regex("[a-zA-Z]")) &&
                newPw.contains(Regex("[0-9]"))

        // 비밀번호가 아이디와 동일하지 않을 때만 유효
        isModifyPwContainsIdValid.value = newPw.isNotEmpty() && newPw != customerid

//        isModifyPwContainsIdValid.value = newPw != textFieldModifyCurrentPwValue.value
    }

    fun modifyCheckPwConditions() {
        val newPw = textFieldModifyNewPwValue.value
        val checkPw = textFieldModifyCheckPwValue.value

        isModifyPwContainsNewPwValid.value = checkPw == newPw
    }

    fun menuCloseOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun modifyCancelOnClick() {
        shoppingApplication.navHostController.popBackStack()
    }

    fun modifySuccessOnClick() {
        shoppingApplication.loginCustomerModel.customerUserPw = textFieldModifyNewPwValue.value

        // 수정한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO) {
                customerService.updateUserPwData(shoppingApplication.loginCustomerModel)
            }
            work1.join()

            Toast.makeText(shoppingApplication, "비밀번호 수정이 완료되었습니다", Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.popBackStack()
        }
    }
}