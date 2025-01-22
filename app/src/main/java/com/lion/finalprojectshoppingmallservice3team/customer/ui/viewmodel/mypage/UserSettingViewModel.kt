package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.lion.finalprojectshoppingmallservice3team.R
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.Tools
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    val imageUri = mutableStateOf<Bitmap?>(null)
    var imageFileName = "none"

    fun loadProfileImage() {
        val profileImageUrl = shoppingApplication.loginCustomerModel.customerUserProfileImage

        viewModelScope.launch(Dispatchers.IO) {
            val context = shoppingApplication.applicationContext
            val bitmap = try {
                if (profileImageUrl.isBlank()) {
                    // 기본 이미지 로드
                    Glide.with(context)
                        .asBitmap()
                        .load(R.drawable.account_circle_24px) // 기본 이미지
                        .submit()
                        .get()
                } else {
                    // URL 이미지 로드
                    Glide.with(context)
                        .asBitmap()
                        .load(profileImageUrl) // URL 이미지
                        .submit()
                        .get()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null // 실패 시 null
            }

            withContext(Dispatchers.Main) {
                imageUri.value = bitmap // Bitmap 상태 업데이트
            }
        }
    }

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
        loadProfileImage()
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
            isLengthValid.value && isSpecialCharInvalid.value && isConsonantVowelValid.value &&
                    shoppingApplication.loginCustomerModel.customerUserNickName != nickname
    }

    fun navigationIconOnClick() {
        // 서버에서 가져온 값과 현재 상태를 리스트로 매핑
        val serverValues = listOf(
            shoppingApplication.loginCustomerModel.customerUserNickName,
//            shoppingApplication.loginCustomerModel.customerUserProfileImage,
            shoppingApplication.loginCustomerModel.customerUserName,
            shoppingApplication.loginCustomerModel.customerUserPhoneNumber,
            shoppingApplication.loginCustomerModel.customerUserAddress,
            shoppingApplication.loginCustomerModel.customerUserDetailAddress,
            shoppingApplication.loginCustomerModel.customerUserBirthDate,
            shoppingApplication.loginCustomerModel.customerUserGender,
            shoppingApplication.loginCustomerModel.customerUserSmsAgree,
            shoppingApplication.loginCustomerModel.customerUserAppPushAgree
        )

        val currentValues = listOf(
            textFieldModifyNicknameValue.value,
//            imageUri.value,
            textFieldModifyNameValue.value,
            textFieldModifyPhoneValue.value,
            textFieldModifyAddressValue.value,
            textFieldModifyDetailAddressValue.value,
            textFieldModifyBirthValue.value,
            selectedGender.value,
            selectedSmsAgree.value,
            selectedPushAgree.value
        )

        // 값이 하나라도 다르면 다이얼로그를 표시
        if (serverValues != currentValues) {
            showDialogBackArrowState.value = true
        } else {
            // 값이 모두 같을 때만 네비게이션 실행
            shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
            shoppingApplication.navHostController.navigate("loginMyPage")
        }
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
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                customerService.updateUserState(shoppingApplication.loginCustomerModel.customerDocumentId, UserState.USER_STATE_SIGNOUT)
            }
            work1.join()

            shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
            shoppingApplication.navHostController.navigate("logoutMyPage")
        }
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

        CoroutineScope(Dispatchers.Main).launch {
            // 이미지가 첨부되어 있다면
            if(imageUri.value != null){
                // 서버상에서의 파일 이름
                imageFileName = "image_${System.currentTimeMillis()}.jpg"
                // 로컬에 ImageView에 있는 이미지 데이터를 저장한다.
                Tools.saveBitmap(shoppingApplication, imageUri.value!!)

                val work1 = async(Dispatchers.IO){
                    // 외부 저장소의 경로를 가져온다.
                    val filePath = shoppingApplication.getExternalFilesDir(null).toString()
//                    customerService.uploadImage("${filePath}/uploadTemp.jpg", imageFileName)
                }
                work1.join()
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

            val work2 = async(Dispatchers.IO) {
                customerService.updateUserData(shoppingApplication.loginCustomerModel)
            }
            work2.join()

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