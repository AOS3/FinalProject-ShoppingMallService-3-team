package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO
import kotlinx.coroutines.tasks.await
import java.io.File

class CustomerRepository {
    // 사용자 정보를 추가하는 메서드
    fun addCustomerData(customerVO: CustomerVO) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        collectionReference.add(customerVO)
    }

    // 사용자 닉네임을 통해 사용자 데이터를 가져오는 메서드
    suspend fun selectUserDataByUserNickName(customerUserNickName: String): MutableList<CustomerVO> {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result =
            collectionReference.whereEqualTo("customerUserNickName", customerUserNickName).get()
                .await()
        val userVoList = result.toObjects(CustomerVO::class.java)
        return userVoList
    }

    // 사용자 아이디를 통해 사용자 데이터를 가져오는 메서드
    suspend fun selectUserDataByUserId(customerUserId: String): MutableList<CustomerVO> {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result =
            collectionReference.whereEqualTo("customerUserId", customerUserId).get().await()
        val userVoList = result.toObjects(CustomerVO::class.java)
        return userVoList
    }

    // 사용자 아이디와 동일한 사용자의 정보 하나를 반환하는 메서드
    suspend fun selectUserDataByUserIdOne(customerUserId: String): MutableMap<String, *> {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result =
            collectionReference.whereEqualTo("customerUserId", customerUserId).get().await()
        val customerVoList = result.toObjects(CustomerVO::class.java)

        val customerMap = mutableMapOf(
            "customer_document_id" to result.documents[0].id,
            "customer_vo" to customerVoList[0]
        )
        return customerMap
    }

    // 사용자 데이터를 수정한다.
    suspend fun updateUserData(customerVO: CustomerVO, customerDocumentId: String) {
        // 수정할 데이터를 담을 맵
        val customerMap = mapOf(
            "customerUserNickName" to customerVO.customerUserNickName,
            "customerUserProfileImage" to customerVO.customerUserProfileImage,
            "customerUserName" to customerVO.customerUserName,
            "customerUserPhoneNumber" to customerVO.customerUserPhoneNumber,
            "customerUserAddress" to customerVO.customerUserAddress,
            "customerUserDetailAddress" to customerVO.customerUserDetailAddress,
            "customerUserBirthDate" to customerVO.customerUserBirthDate,
            "customerUserGender" to customerVO.customerUserGender,
            "customerUserSmsAgree" to customerVO.customerUserSmsAgree,
            "customerUserAppPushAgree" to customerVO.customerUserAppPushAgree,
        )
        // 수정할 문서에 접근할 수 있는 객체를 가져온다.
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)
        documentReference.update(customerMap).await()
    }

    // 사용자 비밀번호를 수정한다.
    suspend fun updateUserPwData(customerVO: CustomerVO, customerDocumentId: String) {
        // 수정할 데이터를 담을 맵
        val customerMap = mapOf(
            "customerUserPw" to customerVO.customerUserPw,
        )
        // 수정할 문서에 접근할 수 있는 객체를 가져온다.
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)
        documentReference.update(customerMap).await()
    }

    // 사용자 크리에이터 여부를 수정한다.
    suspend fun updateUserIsCreatorData(customerVO: CustomerVO, customerDocumentId: String) {
        // 수정할 데이터를 담을 맵
        val customerMap = mapOf(
            "isCreator" to customerVO.isCreator,
        )
        // 수정할 문서에 접근할 수 있는 객체를 가져온다.
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)
        documentReference.update(customerMap).await()
    }

    // 사용자의 상태를 변경하는 메서드
    suspend fun updateUserState(customerDocumentId: String, newState: UserState) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)

        val updateMap = mapOf(
            "customerUserState" to newState.number
        )

        documentReference.update(updateMap).await()
    }

    // 이미지 데이터를 서버로 업로드 하는 메서드
    suspend fun uploadImage(sourceFilePath: String, serverFilePath: String) {
        // 저장되어 있는 이미지의 경로
        val file = File(sourceFilePath)
        val fileUri = Uri.fromFile(file)
        // 업로드 한다.
        val firebaseStorage = FirebaseStorage.getInstance()
        val childReference = firebaseStorage.reference.child("image/$serverFilePath")
        childReference.putFile(fileUri).await()
    }

    suspend fun uploadImageUri(sourceFilePath: String, serverFilePath: String): String {
        val firebaseStorage = FirebaseStorage.getInstance()
        val file = File(sourceFilePath)
        val fileUri = Uri.fromFile(file)
        val childReference = firebaseStorage.reference.child("image/$serverFilePath")

        return try {
            // 파일 업로드
            childReference.putFile(fileUri).await()
            // 업로드된 파일의 다운로드 URL 반환
            childReference.downloadUrl.await().toString()
        } catch (e: Exception) {
            // 업로드 실패 시 빈 문자열 반환
            ""
        }
    }

    suspend fun uploadImages(sourceFilePaths: List<String>): List<String> {
        val uploadedUrls = mutableListOf<String>()

        sourceFilePaths.forEachIndexed { index, sourceFilePath ->
            val serverFilePath = "image/${System.currentTimeMillis()}_${index}.jpg"
            val uploadedUrl = uploadImageUri(sourceFilePath, serverFilePath) // URL 반환됨
            if (uploadedUrl.isNotEmpty()) { // 빈 값 방지
                uploadedUrls.add(uploadedUrl)
            }
        }

        return uploadedUrls // 올바른 URL 리스트 반환
    }

    // 서버에서 이미지 파일을 삭제한다.
    suspend fun removeImageFile(imageFileName: String) {
        if (imageFileName.isBlank() || imageFileName == "none") {
            // 파일 이름이 비어있거나 기본값("none")인 경우 삭제하지 않음
            Log.d(
                "RemoveImageFile",
                "File name is invalid or set to 'none'. No deletion performed."
            )
            return
        }

        try {
            // Firebase Storage의 경로 참조
            val imageReference =
                FirebaseStorage.getInstance().reference.child("image/$imageFileName")

            // 삭제 요청
            imageReference.delete().await()
            Log.d("RemoveImageFile", "Successfully deleted file: $imageFileName")

        } catch (e: Exception) {
            // 오류 처리: 파일이 존재하지 않는 경우 또는 기타 예외
            if (e is StorageException && e.errorCode == StorageException.ERROR_OBJECT_NOT_FOUND) {
                Log.w("RemoveImageFile", "File not found in Firebase Storage: $imageFileName")
            } else {
                Log.e("RemoveImageFile", "Error deleting file: $imageFileName", e)
            }
        }
    }

    // Firebase에 사용자 데이터를 저장하는 메서드
    suspend fun saveKakaoUserToFirebase(customerMap: Map<String, *>): Boolean {
        val firestore = FirebaseFirestore.getInstance()
        return try {
            firestore.collection("CustomerData").add(customerMap).await()
            true
        } catch (e: Exception) {
            println("회원가입 실패: ${e.localizedMessage}")
            false
        }
    }

    suspend fun fetchCustomerData(documentId: String): CustomerModel? {
        val db = FirebaseFirestore.getInstance()
        val documentSnapshot = db.collection("CustomerData")
            .document(documentId)
            .get()
            .await()

        if (documentSnapshot.exists()) {
            val customerModel = CustomerModel()

            customerModel.customerUserId = documentSnapshot.getString("customerUserId") ?: ""
            customerModel.customerUserPw = documentSnapshot.getString("customerUserPw") ?: ""
            customerModel.customerUserNickName = documentSnapshot.getString("customerUserNickName") ?: ""
            customerModel.customerUserName = documentSnapshot.getString("customerUserName") ?: ""
            customerModel.customerUserPhoneNumber = documentSnapshot.getString("customerUserPhoneNumber") ?: ""
            customerModel.customerUserProfileImage = documentSnapshot.getString("customerUserProfileImage") ?: "none"
            customerModel.customerUserGender = documentSnapshot.getString("customerUserGender") ?: ""
            customerModel.customerUserBirthDate = documentSnapshot.getString("customerUserBirthDate") ?: ""
            customerModel.customerUserAddress = documentSnapshot.getString("customerUserAddress") ?: ""
            customerModel.customerUserDetailAddress = documentSnapshot.getString("customerUserDetailAddress") ?: ""

            val stateNumber = documentSnapshot.getLong("customerUserState")?.toInt() ?: 0
            customerModel.customerUserState = UserState.values().find { state -> state.number == stateNumber }
                ?: UserState.USER_STATE_NORMAL

            customerModel.customerUserAdvAgree = documentSnapshot.getBoolean("customerUserAdvAgree") ?: false
            customerModel.customerPersonInfoAgree = documentSnapshot.getBoolean("customerPersonInfoAgree") ?: true
            customerModel.customerUserSmsAgree = documentSnapshot.getString("customerUserSmsAgree") ?: "미동의"
            customerModel.customerUserAppPushAgree = documentSnapshot.getString("customerUserAppPushAgree") ?: "미동의"
            customerModel.fcmToken = documentSnapshot.getString("fcmToken") ?: ""
            customerModel.customerUserCreatedAt = documentSnapshot.getLong("customerUserCreatedAt") ?: 0L
            customerModel.customerUserUpdatedAt = documentSnapshot.getLong("customerUserUpdatedAt") ?: 0L
            customerModel.isCreator = documentSnapshot.getBoolean("isCreator") ?: false

            customerModel.customerDocumentId = documentId

            return customerModel
        }
        return null
    }

    // Firebase에서 토큰 삭제
    suspend fun clearAutoLoginToken(customerDocumentId: String) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)

        val updateMap = mapOf(
            "autoLoginToken" to ""
        )

        try {
            documentReference.update(updateMap).await()
        } catch (e: Exception) {
            throw e // 실패 시 예외를 던집니다.
        }
    }

    suspend fun saveNaverUserToFirebase(customerMap: HashMap<String, *>): Boolean {
        return try {
            val db = FirebaseFirestore.getInstance()
            val userId = customerMap["customerUserId"].toString()

            // Firebase의 "users" 컬렉션에 데이터 저장
            db.collection("CustomerData")
                .document(userId)
                .set(customerMap)
                .await() // 코루틴 처리
            true // 성공
        } catch (e: Exception) {
            e.printStackTrace()
            false // 실패
        }
    }

    // Firebase에 Google 사용자 데이터를 저장하는 메서드
    suspend fun saveGoogleUserToFirebase(customerMap: Map<String, *>): Boolean {
        val firestore = FirebaseFirestore.getInstance()
        return try {
            firestore.collection("CustomerData").add(customerMap).await()
            true
        } catch (e: Exception) {
            println("Google 회원가입 실패: ${e.localizedMessage}")
            false
        }
    }
}