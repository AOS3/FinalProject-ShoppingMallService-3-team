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

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.popBackStack()
    }

    init {
        updateCheckIdButtonState()
        updateCheckNicknameButtonState()
    }

    // 아이디와 닉네임 중복확인 여부, 비밀번호, 체크박스 필수가 체크되어있으면 활성화

    fun updateCheckIdButtonState() {
        isButtonIdEnabled.value = textFieldUserJoinIdValue.value.isNotBlank()
    }

    fun updateCheckNicknameButtonState() {
        isButtonNicknameEnabled.value = textFieldUserJoinNicknameValue.value.isNotBlank()
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