package com.lion.finalprojectshoppingmallservice3team.customer.data.service

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

    // 이미지 데이터를 가져온다.
    suspend fun gettingImage(imageFileName:String) : Uri {
        val imageUri = customerRepository.gettingImage(imageFileName)
        return imageUri
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

    fun saveKakaoUserToFirebase(user: User) {
        val db = FirebaseFirestore.getInstance()

        var gender = ""

        if (user.kakaoAccount?.gender?.name == "MALE") {
            gender = "남자"
        } else if (user.kakaoAccount?.gender?.name == "FEMALE") {
            gender = "여자"
        } else {
            gender = "상관없음"
        }

        var birthDate = ""

        if (user.kakaoAccount?.birthyear != null && user.kakaoAccount?.birthday != null) {
            birthDate = user.kakaoAccount?.birthyear + user.kakaoAccount?.birthday
        }

        // 사용자 정보 매핑
        val customerMap = hashMapOf(
            "customerUserId" to user.id.toString(),
            "customerUserPw" to "",
            "customerUserNickName" to (user.kakaoAccount?.profile?.nickname ?: ""),
            "customerUserName" to (user.kakaoAccount?.name ?: ""),
            "customerUserPhoneNumber" to (user.kakaoAccount?.phoneNumber ?: ""),
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

        // Firestore에 데이터 저장
        db.collection("CustomerData")
            .add(customerMap) // 데이터를 저장
            .addOnSuccessListener {
                println("회원가입 성공: ${user.id}")
            }
            .addOnFailureListener { e ->
                println("회원가입 실패: ${e.localizedMessage}")
            }
    }

    fun fetchCustomerData(documentId: String, onResult: (CustomerModel) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("CustomerData")
            .document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // CustomerModel 객체 생성
                    val customerModel = CustomerModel()

                    // 문서 데이터를 CustomerModel에 수동 매핑
                    customerModel.customerUserId = document.getString("customerUserId") ?: ""
                    customerModel.customerUserPw = document.getString("customerUserPw") ?: ""
                    customerModel.customerUserNickName = document.getString("customerUserNickName") ?: ""
                    customerModel.customerUserName = document.getString("customerUserName") ?: ""
                    customerModel.customerUserPhoneNumber = document.getString("customerUserPhoneNumber") ?: ""
                    customerModel.customerUserProfileImage = document.getString("customerUserProfileImage") ?: "none"
                    customerModel.customerUserGender = document.getString("customerUserGender") ?: ""
                    customerModel.customerUserBirthDate = document.getString("customerUserBirthDate") ?: ""
                    customerModel.customerUserAddress = document.getString("customerUserAddress") ?: ""
                    customerModel.customerUserDetailAddress = document.getString("customerUserDetailAddress") ?: ""

                    // customerUserState 값을 Enum으로 변환
                    val stateNumber = document.getLong("customerUserState")?.toInt() ?: 0
                    customerModel.customerUserState = UserState.values().find { state -> state.number == stateNumber }
                        ?: UserState.USER_STATE_NORMAL

                    customerModel.customerUserAdvAgree = document.getBoolean("customerUserAdvAgree") ?: false
                    customerModel.customerPersonInfoAgree = document.getBoolean("customerPersonInfoAgree") ?: true
                    customerModel.customerUserSmsAgree = document.getString("customerUserSmsAgree") ?: "미동의"
                    customerModel.customerUserAppPushAgree = document.getString("customerUserAppPushAgree") ?: "미동의"
                    customerModel.fcmToken = document.getString("fcmToken") ?: ""
                    customerModel.customerUserCreatedAt = document.getLong("customerUserCreatedAt") ?: 0L
                    customerModel.customerUserUpdatedAt = document.getLong("customerUserUpdatedAt") ?: 0L
                    customerModel.isCreator = document.getBoolean("isCreator") ?: false

                    customerModel.customerDocumentId = documentId // 문서 ID 매핑

                    // 결과 반환
                    onResult(customerModel)
                }
            }
            .addOnFailureListener { e ->
                println("사용자 데이터 로드 실패: ${e.localizedMessage}")
            }
    }
}