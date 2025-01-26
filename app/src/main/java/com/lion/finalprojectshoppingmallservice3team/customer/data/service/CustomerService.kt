package com.lion.finalprojectshoppingmallservice3team.customer.data.service

import android.content.Context
import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.user.model.User
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.CustomerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.LoginResult
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CustomerService(val customerRepository: CustomerRepository) {
    // 사용자 정보를 추가하는 메서드
    fun addCustomerData(customerModel: CustomerModel){
        // 데이터를 VO에 담아준다.
        val customerVO = customerModel.toCustomerVO()
        // 저장하는 메서드를 호출한다.
        customerRepository.addCustomerData(customerVO)
    }

    // 사용하려는 닉네임이 존재하는지 확인하는 메서드
    suspend fun checkJoinUserNickName(customerUserNickName:String) : Boolean{
        // 닉네임을 통해 사용자 정보를 가져온다.
        val customerVoList = customerRepository.selectUserDataByUserNickName(customerUserNickName)
        // 가져온 데이터가 있다면
        if(customerVoList.isNotEmpty()){
            return false
        }
        // 가져온 데이터가 없다면
        else {
            return true
        }
    }

    // 로그인 처리 메서드
    suspend fun checkLogin(loginUserId:String, loginUserPw:String) : LoginResult {
        // 로그인 결과
        var result = LoginResult.LOGIN_RESULT_SUCCESS

        // 입력한 아이디로 사용자 정보를 가져온다.
        val CustomerVoList = customerRepository.selectUserDataByUserId(loginUserId)

        // 가져온 사용자 정보가 없다면
        if(CustomerVoList.isEmpty()){
            result = LoginResult.LOGIN_RESULT_ID_NOT_EXIST
        } else {
            if(loginUserPw != CustomerVoList[0].customerUserPw){
                // 비밀번호가 다르다면
                result = LoginResult.LOGIN_RESULT_PASSWORD_INCORRECT
            }
            // 탈퇴한 회원이라면
            if(CustomerVoList[0].customerUserState == UserState.USER_STATE_SIGNOUT.number){
                result = LoginResult.LOGIN_RESULT_SIGNOUT_MEMBER
            }
        }
        return result
    }

    // 사용자 아이디를 통해 문서 id와 사용자 정보를 가져온다.
    // 사용자 아이디와 동일한 사용자의 정보 하나를 반환하는 메서드
    suspend fun selectUserDataByUserIdOne(customerUserId:String) : CustomerModel{
        val tempMap = customerRepository.selectUserDataByUserIdOne(customerUserId)

        val loginCustomerVo = tempMap["customer_vo"] as CustomerVO
        val loginCustomerDocumentId = tempMap["customer_document_id"] as String

        val loginCustomerModel = loginCustomerVo.toCustomerModel(loginCustomerDocumentId)

        return loginCustomerModel
    }

    // 사용하려는 닉네임이 존재하는지 확인하는 메서드
    suspend fun checkJoinUserId(customerUserId:String) : Boolean{
        // 닉네임을 통해 사용자 정보를 가져온다.
        val customerVoList = customerRepository.selectUserDataByUserId(customerUserId)
        // 가져온 데이터가 있다면
        if(customerVoList.isNotEmpty()){
            return false
        }
        // 가져온 데이터가 없다면
        else {
            return true
        }
    }

    // 사용자 데이터를 수정한다.
    suspend fun updateUserData(customerModel: CustomerModel){
        val customerVO = customerModel.toCustomerVO()
        customerRepository.updateUserData(customerVO, customerModel.customerDocumentId)
    }

    // 사용자 비밀번호 데이터를 수정한다.
    suspend fun updateUserPwData(customerModel: CustomerModel){
        val customerVO = customerModel.toCustomerVO()
        customerRepository.updateUserPwData(customerVO, customerModel.customerDocumentId)
    }

    // 사용자의 상태를 변경하는 메서드
    suspend fun updateUserState(customerDocumentId:String, newState:UserState){
        customerRepository.updateUserState(customerDocumentId, newState)
    }

    // 이미지 데이터를 서버로 업로드 하는 메서드
    suspend fun uploadImage(sourceFilePath:String, serverFilePath:String){
        customerRepository.uploadImage(sourceFilePath, serverFilePath)
    }

    // 서버에서 이미지 파일을 삭제한다.
    suspend fun removeImageFile(imageFileName:String){
        customerRepository.removeImageFile(imageFileName)
    }

    fun checkExistingUser(userId: String, onResult: (Boolean, String?) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("CustomerData")
            .whereEqualTo("customerUserId", userId)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val documentId = documents.documents[0].id
                    onResult(true, documentId) // 사용자 존재 시 documentId 반환
                } else {
                    onResult(false, null) // 사용자 존재하지 않음
                }
            }
            .addOnFailureListener { e ->
                println("사용자 확인 실패: ${e.localizedMessage}")
                onResult(false, null)
            }
    }

    // 카카오 사용자 정보를 Firebase에 저장
    suspend fun saveKakaoUser(user: User): Boolean {
        val gender = when (user.kakaoAccount?.gender?.name) {
            "MALE" -> "남자"
            "FEMALE" -> "여자"
            else -> "상관없음"
        }

        val birthDate = if (user.kakaoAccount?.birthyear != null && user.kakaoAccount?.birthday != null) {
            user.kakaoAccount!!.birthyear + user.kakaoAccount!!.birthday
        } else {
            ""
        }

        val defaultMobile = user.kakaoAccount?.phoneNumber?.replace("+82 ", "0")
        val replaceMobile = defaultMobile?.replace("-", "")

        // 사용자 정보를 HashMap으로 매핑
        val customerMap = hashMapOf(
            "customerUserId" to user.id.toString(),
            "customerUserPw" to "",
            "customerUserNickName" to (user.kakaoAccount?.profile?.nickname ?: ""),
            "customerUserName" to (user.kakaoAccount?.name ?: ""),
            "customerUserPhoneNumber" to replaceMobile,
            "customerUserProfileImage" to (user.kakaoAccount?.profile?.profileImageUrl ?: "none"),
            "customerUserGender" to gender,
            "customerUserBirthDate" to birthDate,
            "customerUserAddress" to (user.kakaoAccount?.email ?: ""),
            "customerUserDetailAddress" to "",
            "customerUserState" to UserState.USER_STATE_NORMAL.number,
            "customerUserAdvAgree" to false,
            "customerPersonInfoAgree" to true,
            "customerUserSmsAgree" to "미동의",
            "customerUserAppPushAgree" to "미동의",
            "fcmToken" to "",
            "customerUserCreatedAt" to System.currentTimeMillis(),
            "customerUserUpdatedAt" to 0L,
            "isCreator" to false
        )

        return customerRepository.saveKakaoUserToFirebase(customerMap)
    }

    // Firestore에서 사용자 데이터를 가져옴
    suspend fun fetchCustomerData(documentId: String): CustomerModel? {
        return customerRepository.fetchCustomerData(documentId)
    }

    suspend fun logoutUser(customerDocumentId: String, context: Context) {
        // SharedPreferences에서 토큰 삭제
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Firebase에서 토큰 삭제
        try {
            customerRepository.clearAutoLoginToken(customerDocumentId)
            println("Firebase에서 토큰 삭제 성공")
        } catch (e: Exception) {
            println("Firebase에서 토큰 삭제 실패: ${e.localizedMessage}")
            throw e
        }
    }

    // 네이버 사용자 정보를 Firebase에 저장
    suspend fun saveNaverUser(userId: String, nickname: String, profileImage: String, gender: String,
                              mobile: String, name: String, birthday: String, birthyear: String): Boolean {
        // 성별, 프로필 이미지 등 기본 값 설정 (네이버 API는 일부 정보가 선택적 제공)
        var naverGender = ""
        if (gender == "M") {
            naverGender = "남자"
        } else if (gender == "F") {
            naverGender = "여자"
        } else {
            naverGender = "상관없음"
        }

        var replaceBirthday = birthday.replace("-","")

        var replaceMobile = mobile.replace("-","")

        var naverBirthDate = ""
        if (birthday.isNotEmpty() && birthyear.isNotEmpty()) {
            naverBirthDate = birthyear + replaceBirthday
        }

        // 사용자 정보를 HashMap으로 매핑
        val customerMap = hashMapOf(
            "customerUserId" to userId,
            "customerUserPw" to "",
            "customerUserNickName" to nickname, // 네이버 사용자 이름
            "customerUserName" to name, // 네이버 사용자 이름
            "customerUserPhoneNumber" to replaceMobile, // 네이버 API로부터 전화번호를 가져오려면 권한 필요
            "customerUserProfileImage" to profileImage,
            "customerUserGender" to naverGender,
            "customerUserBirthDate" to naverBirthDate, // 네이버 API에서 생년월일 제공 시 추가
            "customerUserAddress" to "",
            "customerUserDetailAddress" to "",
            "customerUserState" to UserState.USER_STATE_NORMAL.number,
            "customerUserAdvAgree" to false,
            "customerPersonInfoAgree" to true,
            "customerUserSmsAgree" to "미동의",
            "customerUserAppPushAgree" to "미동의",
            "fcmToken" to "",
            "customerUserCreatedAt" to System.currentTimeMillis(),
            "customerUserUpdatedAt" to 0L,
            "isCreator" to false
        )

        // Firebase에 저장
        return customerRepository.saveNaverUserToFirebase(customerMap)
    }

    suspend fun saveGoogleUser(userId: String, name: String, profileImage: String, phoneNumber: String, birthDate: String,
        gender: String, address: String
    ): Boolean {

        val changeGender = if (gender == "male") {
            "남자"
        } else if (gender == "female"){
            "여자"
        } else {
            "상관없음"
        }

        val defaultMobile = phoneNumber.replace("+82", "0")

        // 사용자 정보를 HashMap으로 매핑
        val customerMap = hashMapOf(
            "customerUserId" to userId,
            "customerUserPw" to "",
            "customerUserNickName" to name,
            "customerUserName" to name,
            "customerUserPhoneNumber" to defaultMobile,
            "customerUserProfileImage" to profileImage ,
            "customerUserGender" to changeGender,
            "customerUserBirthDate" to birthDate,
            "customerUserAddress" to address,
            "customerUserDetailAddress" to "",
            "customerUserState" to UserState.USER_STATE_NORMAL.number,
            "customerUserAdvAgree" to false,
            "customerPersonInfoAgree" to true,
            "customerUserSmsAgree" to "미동의",
            "customerUserAppPushAgree" to "미동의",
            "fcmToken" to "",
            "customerUserCreatedAt" to System.currentTimeMillis(),
            "customerUserUpdatedAt" to 0L,
            "isCreator" to false
        )

        // Firebase에 저장
        return customerRepository.saveGoogleUserToFirebase(customerMap)
    }
}