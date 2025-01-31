package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.InquiryVO
import kotlinx.coroutines.tasks.await
import java.io.File

class InquiryRepository {
    // 사용자 정보를 추가하는 메서드
    suspend fun addInquiryData(inquiryVO: InquiryVO) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("InquiryData")
        collectionReference.add(inquiryVO)
    }

    // 글 목록을 가져오는 메서드
    suspend fun gettingInquiryList() : MutableList<Map<String, *>>{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("InquiryData")
        // 데이터를 가져온다.
        val result = collectionReference.orderBy("inquiryCreatedAt", Query.Direction.DESCENDING).get().await()

        // 반환할 리스트
        val resultList = mutableListOf<Map<String, *>>()
        // 데이터의 수 만큼 반환한다.
        result.forEach {
            val map = mapOf(
                // 문서의 id
                "documentId" to it.id,
                // 데이터를 가지고 있는 객체
                "inquiryVO" to it.toObject(InquiryVO::class.java)
            )
            resultList.add(map)
        }
        return resultList
    }

    // 글의 문서 id를 통해 글 데이터를 가져온다.
    suspend fun selectInquiryDataOneById(documentId:String) : InquiryVO{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("InquiryData")
        val documentReference = collectionReference.document(documentId)
        val documentSnapShot = documentReference.get().await()
        val inquiryVO = documentSnapShot.toObject(InquiryVO::class.java)!!
        return inquiryVO
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
}