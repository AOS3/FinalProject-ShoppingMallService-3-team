package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.kakao.sdk.user.model.User
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

    // 사용자 데이터를 수정한다.
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

    // 이미지 데이터를 가져온다.
    suspend fun gettingImage(imageFileName: String): Uri {
        val storageReference = FirebaseStorage.getInstance().reference
        // 파일명을 지정하여 이미지 데이터를 가져온다.
        val childStorageReference = storageReference.child("image/$imageFileName")
        val imageUri = childStorageReference.downloadUrl.await()
        Log.d("test100", imageUri.path!!)
        return imageUri
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
}