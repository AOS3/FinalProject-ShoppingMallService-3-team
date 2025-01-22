package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserJoinInfoViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val customerService: CustomerService
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

//    // 약관들 입력 요소
//    val checkBoxUserJoinInfo1Value = mutableStateOf(false)
//    val checkBoxUserJoinInfo2Value = mutableStateOf(false)
//    val checkBoxUserJoinInfo3Value = mutableStateOf(false)
    val checkBoxUserJoinInfo4Value = mutableStateOf(false)

    val isButtonIdEnabled = mutableStateOf(false)
    val isButtonNicknameEnabled = mutableStateOf(false)
    val isButtonJoinEnabled = mutableStateOf(false)

    // 닉네임 중복확인을 했는지
    val isCheckNickName = mutableStateOf(false)
    // 아이디 중복확인을 했는지
    val isCheckId = mutableStateOf(false)

    // 다이얼로그를 제어하는 상태 변수
    val showDialogNickNameIsNotCheckState = mutableStateOf(false)
    val showDialogNickNameOk = mutableStateOf(false)
    val showDialogNickNameNo = mutableStateOf(false)
    val showDialogIdIsNotCheckState = mutableStateOf(false)
    val showDialogIdOk = mutableStateOf(false)
    val showDialogIdNo = mutableStateOf(false)

    // 네비게이션 아이콘을 누르면 호출되는 메서드
    fun navigationIconOnClick(){
        shoppingApplication.navHostController.popBackStack()
    }

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

        // 상태 업데이트 호출
        updateUserJoinButtonState()
    }

    fun joinPwConditions() {
        val password = textFieldUserJoinPwValue.value
        val id = textFieldUserJoinIdValue.value

        // 비밀번호가 10자 이상이고, 영문과 숫자를 포함해야 함
        isJoinPwLengthValid.value = password.length >= 10 &&
                password.contains(Regex("[a-zA-Z]")) &&
                password.contains(Regex("[0-9]"))

        // 비밀번호가 아이디와 동일하지 않을 때만 유효
        isJoinContainsIdValid.value = password.isNotEmpty() && password != id

        // 상태 업데이트 호출
        updateUserJoinButtonState()
    }

    init {
        updateCheckIdButtonState()
        updateUserJoinButtonState()
    }

    fun updateUserJoinButtonState() {
        // 조건: 아이디 중복 확인, 닉네임 중복 확인, 비밀번호 조건 만족, 약관 필수 체크
        isButtonJoinEnabled.value = isCheckId.value &&
                isCheckNickName.value &&
                isJoinPwLengthValid.value &&
                isJoinContainsIdValid.value
//                checkBoxUserJoinInfo1Value.value && // 필수 약관
//                checkBoxUserJoinInfo2Value.value && // 필수 약관
//                checkBoxUserJoinInfo3Value.value
    }

    fun updateCheckIdButtonState() {
        isButtonIdEnabled.value = textFieldUserJoinIdValue.value.isNotBlank()
    }

    // 약관 체크박스를 눌렀을 때 호출되는 메서드
    fun triStateCheckBoxUserJoinInfoAllOnClick(){
        if(triStateCheckBoxUserJoinInfoAllValue.value == ToggleableState.On){
//            checkBoxUserJoinInfo1Value.value = true
//            checkBoxUserJoinInfo2Value.value = true
//            checkBoxUserJoinInfo3Value.value = true
            checkBoxUserJoinInfo4Value.value = true
            updateUserJoinButtonState()
        } else if(triStateCheckBoxUserJoinInfoAllValue.value == ToggleableState.Off){
//            checkBoxUserJoinInfo1Value.value = false
//            checkBoxUserJoinInfo2Value.value = false
//            checkBoxUserJoinInfo3Value.value = false
            checkBoxUserJoinInfo4Value.value = false
            updateUserJoinButtonState()
        }
    }

    // 약관 요소들의 상태가 변경되었을 때
    fun checkBoxUserJoinInfoOnChanged(){
        // 체크된 체박스의 개수
        var checkedCount = 0

//        if(checkBoxUserJoinInfo1Value.value == true){
//            checkedCount++
//        }
//        if(checkBoxUserJoinInfo2Value.value == true){
//            checkedCount++
//        }
//        if(checkBoxUserJoinInfo3Value.value == true){
//            checkedCount++
//        }
        if(checkBoxUserJoinInfo4Value.value == true){
            checkedCount++
        }

        triStateCheckBoxUserJoinInfoAllValue.value = if(checkedCount == 1){
            ToggleableState.On
        } else if(checkedCount == 0){
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }

        // 상태 업데이트 호출
        updateUserJoinButtonState()
    }

    // 중복확인 버튼을 눌렀을 때
    fun checkUserId(){
        // 사용자가 입력한 아이디
        val userId = textFieldUserJoinIdValue.value

        // 사용할 수 있는 아이디인지 검사한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.checkJoinUserId(userId)
            }
            isCheckId.value = work1.await()

            if(isCheckId.value){
                showDialogIdOk.value = true
                updateUserJoinButtonState()
            } else{
                textFieldUserJoinIdValue.value = ""
                showDialogIdNo.value = true
                updateCheckIdButtonState()
                updateUserJoinButtonState()
            }
        }
    }

    // 중복확인 버튼을 눌렀을 때
    fun checkNickName(){
        // 사용자가 입력한 닉네임
        val userNickName = textFieldUserJoinNicknameValue.value

        // 사용할 수 있는 닉네임인지 검사한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.checkJoinUserNickName(userNickName)
            }
            isCheckNickName.value = work1.await()

            if(isCheckNickName.value){
                showDialogNickNameOk.value = true
                updateUserJoinButtonState()
            } else{
                textFieldUserJoinNicknameValue.value = ""
                showDialogNickNameNo.value = true
                joinNicknameConditions()
                updateUserJoinButtonState()
            }
        }
    }

    // 가입 완료 버튼을 눌렀을 때
    fun buttonUserJoinSubmitOnClick(){

        if(isCheckNickName.value == false){
            showDialogNickNameIsNotCheckState.value = true
            return
        }
        // 저장할 데이터를 추출한다.
        val customerModel = CustomerModel()

        customerModel.customerUserId = textFieldUserJoinIdValue.value
        customerModel.customerUserPw = textFieldUserJoinPwValue.value
        customerModel.customerUserNickName = textFieldUserJoinNicknameValue.value
//        customerModel.isAdult = checkBoxUserJoinInfo1Value.value
//        customerModel.useAgree = checkBoxUserJoinInfo2Value.value
//        customerModel.customerInformationAgree = checkBoxUserJoinInfo3Value.value
        customerModel.customerUserAdvAgree = checkBoxUserJoinInfo4Value.value
        customerModel.customerUserAdvAgree = checkBoxUserJoinInfo4Value.value
        if (checkBoxUserJoinInfo4Value.value == true){
            customerModel.customerUserSmsAgree = "동의"
            customerModel.customerUserAppPushAgree = "동의"
        } else {
            customerModel.customerUserSmsAgree = "미동의"
            customerModel.customerUserAppPushAgree = "미동의"
        }
        customerModel.customerUserCreatedAt = System.nanoTime()
        customerModel.customerUserState = UserState.USER_STATE_NORMAL

        // 저장한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.addCustomerData(customerModel)
            }
            work1.join()

            Toast.makeText(shoppingApplication, "가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.popBackStack()
            shoppingApplication.navHostController.popBackStack("userJoin", inclusive = true)
            shoppingApplication.navHostController.popBackStack("logoutMyPage", inclusive = true)
            shoppingApplication.navHostController.navigate("login")
        }
    }
}