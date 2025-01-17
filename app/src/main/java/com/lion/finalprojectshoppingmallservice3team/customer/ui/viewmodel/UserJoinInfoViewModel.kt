package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class UserJoinInfoViewModel @Inject constructor(
    @ApplicationContext context: Context
) :ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    // 아이디 입력 요소
    val textFieldUserJoinIdValue = mutableStateOf("")
    // 비밀번호 입력 요소
    val textFieldUserJoinPwValue = mutableStateOf("")
    // 닉네임 입력 요소
    val textFieldUserJoinNicknameValue = mutableStateOf("")

    // 조건 충족 여부 상태
    // 2~10자
    val isJoinLengthValid = mutableStateOf(false)
    // 특수문자 불가
    val isJoinSpecialCharInvalid = mutableStateOf(true)
    // 자음 모음 단독 사용 불가
    val isJoinConsonantVowelValid = mutableStateOf(false)

    // 2~10자
    val isJoinPwLengthValid = mutableStateOf(false)
    // 아이디랑 같은지
    val isJoinContainsIdValid = mutableStateOf(false)

    // 전체동의 입력 요소
    val triStateCheckBoxUserJoinInfoAllValue = mutableStateOf(ToggleableState.Off)

    // 약관들 입력 요소
    val checkBoxUserJoinInfo1Value = mutableStateOf(false)
    val checkBoxUserJoinInfo2Value = mutableStateOf(false)
    val checkBoxUserJoinInfo3Value = mutableStateOf(false)
    val checkBoxUserJoinInfo4Value = mutableStateOf(false)

    val isButtonIdEnabled = mutableStateOf(false)
    val isButtonNicknameEnabled = mutableStateOf(false)
    val isButtonJoinEnabled = mutableStateOf(false)

    fun joinNicknameConditions() {
        val nickname = textFieldUserJoinNicknameValue.value

        // 2~10자 조건
        isJoinLengthValid.value = nickname.length in 2..10

        // 특수문자 불가 조건 (빈 문자열 처리 추가)
        isJoinSpecialCharInvalid.value = !nickname.contains(Regex("[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]"))

        // 자음 모음 단독 사용 불가 조건
        isJoinConsonantVowelValid.value = !nickname.contains(Regex("[ㄱ-ㅎㅏ-ㅣ]"))

        // 버튼 활성화 상태
        isButtonNicknameEnabled.value = isJoinLengthValid.value &&
                isJoinSpecialCharInvalid.value && isJoinConsonantVowelValid.value
    }

    fun joinPwConditions() {
        val password = textFieldUserJoinPwValue.value
        val id = textFieldUserJoinIdValue.value

        // 비밀번호가 10자 이상이고, 영문과 숫자를 포함해야 함
        isJoinPwLengthValid.value = password.length >= 10 &&
                password.contains(Regex("[a-zA-Z]")) &&
                password.contains(Regex("[0-9]"))

        isJoinContainsIdValid.value = password != id
    }

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    init {
        updateCheckIdButtonState()
    }

    // 아이디와 닉네임 중복확인 여부, 비밀번호, 체크박스 필수가 체크되어있으면 활성화

    fun updateCheckIdButtonState() {
        isButtonIdEnabled.value = textFieldUserJoinIdValue.value.isNotBlank()
    }

    // 약관 체크박스를 눌렀을 때 호출되는 메서드
    fun triStateCheckBoxUserJoinInfoAllOnClick(){
        if(triStateCheckBoxUserJoinInfoAllValue.value == ToggleableState.On){
            checkBoxUserJoinInfo1Value.value = true
            checkBoxUserJoinInfo2Value.value = true
            checkBoxUserJoinInfo3Value.value = true
            checkBoxUserJoinInfo4Value.value = true
        } else if(triStateCheckBoxUserJoinInfoAllValue.value == ToggleableState.Off){
            checkBoxUserJoinInfo1Value.value = false
            checkBoxUserJoinInfo2Value.value = false
            checkBoxUserJoinInfo3Value.value = false
            checkBoxUserJoinInfo4Value.value = false

        }
    }

    // 약관 요소들의 상태가 변경되었을 때
    fun checkBoxUserJoinInfoOnChanged(){
        // 체크된 체박스의 개수
        var checkedCount = 0

        if(checkBoxUserJoinInfo1Value.value == true){
            checkedCount++
        }
        if(checkBoxUserJoinInfo2Value.value == true){
            checkedCount++
        }
        if(checkBoxUserJoinInfo3Value.value == true){
            checkedCount++
        }
        if(checkBoxUserJoinInfo4Value.value == true){
            checkedCount++
        }

        triStateCheckBoxUserJoinInfoAllValue.value = if(checkedCount == 4){
            ToggleableState.On
        } else if(checkedCount == 0){
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }
    }
}