package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.SnackbarHostState
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
class UserSettingViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    val textFieldModifyNicknameValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserNickName)
    val textFieldModifyNameValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserName)
    val textFieldModifyPhoneValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserPhoneNumber)
    val textFieldModifyAddressValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserAddress)
    val textFieldModifyDetailAddressValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserDetailAddress)
    val textFieldModifyBirthValue =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserBirthDate)

    // 조건 충족 여부 상태
    // 2~10자
    val isLengthValid = mutableStateOf(false)

    // 특수문자 불가
    val isSpecialCharInvalid = mutableStateOf(true)

    // 자음 모음 단독 사용 불가
    val isConsonantVowelValid = mutableStateOf(false)

    // 라디오 버튼 클릭 여부
    val selectedGender = mutableStateOf(
        if (shoppingApplication.loginCustomerModel.customerUserGender.isBlank()) {
            "상관없음"
        } else {
            shoppingApplication.loginCustomerModel.customerUserGender
        }
    )
    val selectedSmsAgree =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserSmsAgree)
    val selectedPushAgree =
        mutableStateOf(shoppingApplication.loginCustomerModel.customerUserAppPushAgree)

    // 닉네임 중복확인을 했는지
    val isCheckNickName = mutableStateOf(true)

    // 다이얼로그를 제어하는 상태 변수
    val showDialogNickNameEmptyState = mutableStateOf(false)
    val showDialogNickNameIsNotCheckState = mutableStateOf(false)
    val showDialogNickNameOk = mutableStateOf(false)
    val showDialogNickNameNo = mutableStateOf(false)

    // 회원 탈퇴 다이얼로그 상태 변수
    val showDialogWithdrawalState = mutableStateOf(false)

    // 네비게이션 클릭시 다이얼로그 상태 변수
    val showDialogBackArrowState = mutableStateOf(false)

    // 중복확인 버튼 클릭 가능 여부
    val isButtonNicknameEnabled = mutableStateOf(false)

    init {
        // 초기화 시 닉네임 조건 업데이트
        updateNicknameConditions()
    }

    fun updateNicknameConditions() {
        val nickname = textFieldModifyNicknameValue.value

        // 2~10자 조건
        isLengthValid.value = nickname.length in 2..10

        // 특수문자 불가 조건 (빈 문자열 처리 추가)
        isSpecialCharInvalid.value = !nickname.contains(Regex("[^ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z]"))

        // 자음 모음 단독 사용 불가 조건
        isConsonantVowelValid.value = !nickname.contains(Regex("[ㄱ-ㅎㅏ-ㅣ]"))

        // 버튼 활성화 상태
        isButtonNicknameEnabled.value =
            isLengthValid.value && isSpecialCharInvalid.value && isConsonantVowelValid.value
    }

    // 뒤로가기 버튼을 눌렀을때
    fun navigationIconOnClick() {
        showDialogBackArrowState.value = true
    }

    // 다이얼로그 바로가기 버튼을 눌렀을때
    fun dialogConfirmOnClick() {
        shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
        shoppingApplication.navHostController.navigate("loginMyPage")
    }

    // 다이얼로그 취소 버튼을 눌렀을때
    fun dialogDismissOnClick() {
        showDialogBackArrowState.value = false
    }

    // 비밀번호 변경을 눌렀을때
    fun modifyPwOnClick() {
        shoppingApplication.navHostController.navigate("modifyUserPw")
    }

    // 회원 탈퇴를 눌렀을때
    fun withdrawalOnClick() {
        showDialogWithdrawalState.value = true
        shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
        shoppingApplication.navHostController.navigate("logoutMyPage")
    }

    // 저장하기를 눌렀을때
    fun saveSettingButtonOnClick() {
        // 닉네임
        val nickName = textFieldModifyNicknameValue.value

        // 원래의 닉네임과 입력 요소에 입력된 닉네임이 서로 다르고 중복 검사를 안했을 경우 입력 오류로 처리
        if (shoppingApplication.loginCustomerModel.customerUserNickName != nickName) {
            if (isCheckNickName.value == false) {
                showDialogNickNameIsNotCheckState.value = true
                return
            }
        }

        shoppingApplication.loginCustomerModel.customerUserNickName = nickName
        shoppingApplication.loginCustomerModel.customerUserName = textFieldModifyNameValue.value
        shoppingApplication.loginCustomerModel.customerUserPhoneNumber = textFieldModifyPhoneValue.value
        shoppingApplication.loginCustomerModel.customerUserAddress = textFieldModifyAddressValue.value
        shoppingApplication.loginCustomerModel.customerUserDetailAddress = textFieldModifyDetailAddressValue.value
        shoppingApplication.loginCustomerModel.customerUserBirthDate = textFieldModifyBirthValue.value
        shoppingApplication.loginCustomerModel.customerUserGender = selectedGender.value
        shoppingApplication.loginCustomerModel.customerUserSmsAgree = selectedSmsAgree.value
        shoppingApplication.loginCustomerModel.customerUserAppPushAgree = selectedPushAgree.value

        // 수정한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO) {
                customerService.updateUserData(shoppingApplication.loginCustomerModel)
            }
            work1.join()

            Toast.makeText(shoppingApplication, "수정이 완료되었습니다", Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
            shoppingApplication.navHostController.navigate("loginMyPage")
        }
    }

    // 중복 확인 버튼을 눌렀을 때 호출되는 메서드
    fun buttonCheckNickNameOnClick(){
        // 사용자가 입력한 닉네임
        val nickName = textFieldModifyNicknameValue.value

        // 비어 있다면
        if(nickName.isEmpty()){
            showDialogNickNameEmptyState.value = true
            return
        }

        // 사용할 수 있는 닉네임인지 검사한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.checkJoinUserNickName(nickName)
            }
            isCheckNickName.value = work1.await()

            if(isCheckNickName.value){
                showDialogNickNameOk.value = true
            } else{
                textFieldModifyNicknameValue.value = ""
                showDialogNickNameNo.value = true
                updateNicknameConditions()
            }
        }
    }
}