package com.lion.finalprojectshoppingmallservice3team.creator.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.CreatorVO
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.ShopVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.ProductCategory
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
import kotlinx.coroutines.tasks.await

class ShopRepository {
    // 사용자 정보를 추가하는 메서드
    fun addShopData(shopVO: ShopVO) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("ShopData")
        collectionReference.add(shopVO)
    }

    // 사용자들의 정보를 가져오는 매서드
    suspend fun selectAllShopData(shopCategory: String): MutableList<Map<String, *>> {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("ShopData")

        val result = if(shopCategory == "전체"){
            collectionReference.orderBy("shopName", Query.Direction.ASCENDING).get().await()
        } else if (shopCategory == "기업"){
            collectionReference.whereEqualTo("shopComPosition", true)
                .orderBy("shopName", Query.Direction.ASCENDING).get().await()
        } else{
            collectionReference.whereEqualTo("shopComPosition", false)
                .orderBy("shopName", Query.Direction.ASCENDING).get().await()
        }
        // 반환할 리스트
        val resultList = mutableListOf<Map<String, *>>()
        result.forEach {
            val map = mapOf(
                // 문서의 Id
                "shopDocumentId" to it.id,
                // 데이터를 가지고 있는 객체
                "shopVO" to it.toObject(ShopVO::class.java)
            )
            resultList.add(map)
        }
        return resultList
    }
}