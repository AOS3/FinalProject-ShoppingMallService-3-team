package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class UserSettingViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    val textFieldModifyNicknameValue = mutableStateOf(" ")
    val textFieldModifyNameValue = mutableStateOf(" ")
    val textFieldModifyPhoneValue = mutableStateOf(" ")
    val textFieldModifyAddressValue = mutableStateOf(" ")
    val textFieldModifyDetailAddressValue = mutableStateOf(" ")
    val textFieldModifyBirthValue = mutableStateOf(" ")

    // 조건 충족 여부 상태
    // 2~10자
    val isLengthValid = mutableStateOf(false)
    // 특수문자 불가
    val isSpecialCharInvalid = mutableStateOf(true)
    // 자음 모음 단독 사용 불가
    val isConsonantVowelValid = mutableStateOf(false)

    // 라디오 버튼 클릭 여부
    val selectedGender = mutableStateOf("상관없음")
    val selectedSmsAgree = mutableStateOf("동의")
    val selectedPushAgree = mutableStateOf("동의")

    // 회원 탈퇴 다이얼로그 상태 변수
    val showDialogWithdrawalState = mutableStateOf(false)
    // 네비게이션 클릭시 다이얼로그 상태 변수
    val showDialogBackArrowState = mutableStateOf(false)

    // 중복확인 버튼 클릭 가능 여부
    val isButtonNicknameEnabled = mutableStateOf(false)

    fun updateNicknameConditions() {
        val nickname = textFieldModifyNicknameValue.value

        // 2~10자 조건
        isLengthValid.value = nickname.length in 2..10

        // 특수문자 불가 조건 (빈 문자열 처리 추가)
        isSpecialCharInvalid.value = !nickname.contains(Regex("[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]"))

        // 자음 모음 단독 사용 불가 조건
        isConsonantVowelValid.value = !nickname.contains(Regex("[ㄱ-ㅎㅏ-ㅣ]"))

        // 버튼 활성화 상태
        isButtonNicknameEnabled.value = isLengthValid.value && isSpecialCharInvalid.value && isConsonantVowelValid.value
    }

    // 뒤로가기 버튼을 눌렀을때
    fun navigationIconOnClick(){
        showDialogBackArrowState.value = true

    }

    // 다이얼로그 바로가기 버튼을 눌렀을때
    fun dialogConfirmOnClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    // 다이얼로그 취소 버튼을 눌렀을때
    fun dialogDismissOnClick(){
        showDialogBackArrowState.value = false
    }

    // 회원 탈퇴를 눌렀을때
    fun modifyPwOnClick(){
        shoppingApplication.navHostController.navigate("modifyUserPw")
    }

    // 회원 탈퇴를 눌렀을때
    fun withdrawalOnClick(){
        showDialogWithdrawalState.value = true
    }

    // 저장하기를 눌렀을때
    fun saveSettingButtonOnClick(){
        shoppingApplication.navHostController.navigate("loginMyPage"){
            popUpTo("userSetting") { inclusive = true }
        }
    }
}