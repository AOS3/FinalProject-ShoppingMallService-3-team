package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO
import kotlinx.coroutines.tasks.await

class CustomerRepository {
    // 사용자 정보를 추가하는 메서드
    fun addCustomerData(customerVO: CustomerVO){
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        collectionReference.add(customerVO)
    }

    // 사용자 닉네임을 통해 사용자 데이터를 가져오는 메서드
    suspend fun selectUserDataByUserNickName(customerUserNickName:String) : MutableList<CustomerVO>{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result = collectionReference.whereEqualTo("customerUserNickName", customerUserNickName).get().await()
        val userVoList = result.toObjects(CustomerVO::class.java)
        return userVoList
    }

    // 사용자 아이디를 통해 사용자 데이터를 가져오는 메서드
    suspend fun selectUserDataByUserId(customerUserId:String) : MutableList<CustomerVO>{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result = collectionReference.whereEqualTo("customerUserId", customerUserId).get().await()
        val userVoList = result.toObjects(CustomerVO::class.java)
        return userVoList
    }

    // 사용자 아이디와 동일한 사용자의 정보 하나를 반환하는 메서드
    suspend fun selectUserDataByUserIdOne(customerUserId:String) : MutableMap<String, *>{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val result = collectionReference.whereEqualTo("customerUserId", customerUserId).get().await()
        val customerVoList = result.toObjects(CustomerVO::class.java)

        val customerMap = mutableMapOf(
            "customer_document_id" to result.documents[0].id,
            "customer_vo" to customerVoList[0]
        )
        return customerMap
    }

    // 사용자 데이터를 수정한다.
    suspend fun updateUserData(customerVO: CustomerVO, customerDocumentId: String){
        // 수정할 데이터를 담을 맵
        val customerMap = mapOf(
            "customerUserNickName" to customerVO.customerUserNickName,
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
    suspend fun updateUserPwData(customerVO: CustomerVO, customerDocumentId: String){
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
    suspend fun updateUserState(customerDocumentId:String, newState: UserState){
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CustomerData")
        val documentReference = collectionReference.document(customerDocumentId)

        val updateMap = mapOf(
            "customerUserState" to newState.number
        )

        documentReference.update(updateMap).await()
    }
}