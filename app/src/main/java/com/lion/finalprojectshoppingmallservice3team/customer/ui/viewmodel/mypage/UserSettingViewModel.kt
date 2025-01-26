package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
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

    // 보여줄 이미지 요소
    val showImage1State = mutableStateOf(false)
    val showImage2State = mutableStateOf(false)
    val showImage3State = mutableStateOf(false)

    // 카메라나 앨범을 통해 가져온 사진을 담을 상태변수
    val imageBitmapState = mutableStateOf<Bitmap?>(null)
    // 서버로 부터 이미지를 받아올 수 있는 Uri를 담을 상태 변수
    val imageUriState = mutableStateOf<Uri?>(null)
    // 서버상에서의 파일 이름
    var newFileName = shoppingApplication.loginCustomerModel.customerUserProfileImage

    fun loadProfileImage() {
        val profileImage = shoppingApplication.loginCustomerModel.customerUserProfileImage

        // 값이 비어 있거나 기본값으로 설정된 경우
        if (profileImage.isNullOrEmpty() || profileImage == "none") {
            // 기본 이미지 표시
            showImage1State.value = true
        }
        // 외부 URL인 경우 (카카오 이미지)
        else if (profileImage.startsWith("http://") || profileImage.startsWith("https://")) {
            loadImageFromUrl(profileImage)
        }
        // Firebase Storage 파일 이름인 경우 (기존 이미지)
        else {
            loadImageFromFirebaseStorage(profileImage)
        }
    }

    // 외부 URL에서 이미지 로드
    fun loadImageFromUrl(url: String) {
        try {
            val imageUri = Uri.parse(url) // URL을 URI로 변환
            showImage2State.value = true
            imageUriState.value = imageUri
        } catch (e: Exception) {
            println("URL 이미지 로드 실패: ${e.localizedMessage}")
            showImage1State.value = true // 기본 이미지 표시
        }
    }

    // Firebase Storage에서 이미지 로드
    fun loadImageFromFirebaseStorage(fileName: String) {
        val storageRef = FirebaseStorage.getInstance().reference.child("profile_images/$fileName")
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            showImage2State.value = true
            imageUriState.value = uri
        }.addOnFailureListener { e ->
            println("Firebase Storage 이미지 로드 실패: ${e.localizedMessage}")
            showImage1State.value = true // 기본 이미지 표시
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

    // 이미지 삭제 버튼을 눌렀을 때
    fun deleteImageOnClick(){
        showImage1State.value = false
        showImage2State.value = false
        showImage3State.value = false

        // 카메라나 앨범에서 가져온 사진이 있다면 삭제한다.
        imageBitmapState.value = null

        // 기본 이미지를 보여준다.
        showImage1State.value = true
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

            shoppingApplication.isLoggedIn.value = false
            shoppingApplication.navHostController.popBackStack("userSetting", inclusive = true)
            shoppingApplication.navHostController.navigate("logoutMyPage")
        }
    }

    fun saveSettingButtonOnClick() {
        val nickName = textFieldModifyNicknameValue.value

        if (shoppingApplication.loginCustomerModel.customerUserNickName != nickName) {
            if (!isCheckNickName.value) {
                showDialogNickNameIsNotCheckState.value = true
                return
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            // 첨부 이미지가 있다면
            if(imageUriState.value != null){
                // 만약 이미지를 삭제했다면
                if(showImage1State.value){
                    // 이미지 파일을 삭제한다.
                    val work1 = async(Dispatchers.IO) {
                        customerService.removeImageFile(shoppingApplication.loginCustomerModel.customerUserNickName)
                    }
                    work1.join()
                    newFileName = "none"
                }
            }
            // 카메라나 앨범에서 사진을 가져온 적이 있다면
            if(showImage3State.value){
                // 첨부 이미지가 있다면
                if(imageUriState.value != null){
                    // 이미지 파일을 삭제한다.
                    val work1 = async(Dispatchers.IO) {
                        customerService.removeImageFile(shoppingApplication.loginCustomerModel.customerUserNickName)
                    }
                    work1.join()
                }

                // 서버상에서의 파일 이름
                newFileName = "image_${System.currentTimeMillis()}.jpg"
                // 로컬에 ImageView에 있는 이미지 데이터를 저장한다.
                Tools.saveBitmap(shoppingApplication,  imageBitmapState.value!!)

                val work2 = async(Dispatchers.IO){
                    val filePath = shoppingApplication.getExternalFilesDir(null).toString()
                    customerService.uploadImage("${filePath}/uploadTemp.jpg", newFileName)
                }
                work2.join()
            }

            shoppingApplication.loginCustomerModel.customerUserNickName = nickName
            shoppingApplication.loginCustomerModel.customerUserProfileImage = newFileName
            shoppingApplication.loginCustomerModel.customerUserName = textFieldModifyNameValue.value
            shoppingApplication.loginCustomerModel.customerUserPhoneNumber = textFieldModifyPhoneValue.value
            shoppingApplication.loginCustomerModel.customerUserAddress = textFieldModifyAddressValue.value
            shoppingApplication.loginCustomerModel.customerUserDetailAddress = textFieldModifyDetailAddressValue.value
            shoppingApplication.loginCustomerModel.customerUserBirthDate = textFieldModifyBirthValue.value
            shoppingApplication.loginCustomerModel.customerUserGender = selectedGender.value
            shoppingApplication.loginCustomerModel.customerUserSmsAgree = selectedSmsAgree.value
            shoppingApplication.loginCustomerModel.customerUserAppPushAgree = selectedPushAgree.value

            val work3 = async(Dispatchers.IO) {
                customerService.updateUserData(shoppingApplication.loginCustomerModel)
            }
            work3.join()

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