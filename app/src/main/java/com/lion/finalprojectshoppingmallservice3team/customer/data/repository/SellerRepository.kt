package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.SellerVO
import kotlinx.coroutines.tasks.await

class SellerRepository {
    // 판매자 정보 전체를 가져오는 메서드
    suspend fun selectSellerDataAll() : MutableList<MutableMap<String, *>>{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("SellerData")
        val result = collectionReference.get().await()
        val sellerList = mutableListOf<MutableMap<String, *>>()
        result.forEach {
            val sellerMap = mutableMapOf(
                "seller_document_Id" to it.id,
                "seller_vo" to it.toObject(SellerVO::class.java)
            )
            sellerList.add(sellerMap)
        }
        return sellerList
    }
}