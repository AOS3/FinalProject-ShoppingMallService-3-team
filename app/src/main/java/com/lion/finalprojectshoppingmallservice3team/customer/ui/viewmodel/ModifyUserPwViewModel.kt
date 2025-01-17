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

    // 조건 충족 여부 상태
    // 2~10자
    val isModifyPwLengthValid = mutableStateOf(false)
    // 특수문자 불가
    val isModifyPwSpecialCharInvalid = mutableStateOf(true)
    // 자음 모음 단독 사용 불가
    val isModifyPwConsonantVowelValid = mutableStateOf(false)
    // 아이디랑 같은지
    val isModifyPwContainsIdValid = mutableStateOf(false)
    // 새 비밀번호랑 같은지
    val isModifyPwContainsNewPwValid = mutableStateOf(false)

    fun modifyNewPwConditions() {
        val newPw = textFieldModifyNewPwValue.value

        // 2~10자 조건
        isModifyPwLengthValid.value = newPw.length in 2..10

        // 특수문자 불가 조건 (빈 문자열 처리 추가)
        isModifyPwSpecialCharInvalid.value = !newPw.contains(Regex("[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]"))

        // 자음 모음 단독 사용 불가 조건
        isModifyPwConsonantVowelValid.value = !newPw.contains(Regex("[ㄱ-ㅎㅏ-ㅣ]"))

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
        shoppingApplication.navHostController.popBackStack()
    }
}