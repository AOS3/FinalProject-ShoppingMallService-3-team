package com.lion.finalprojectshoppingmallservice3team.customer.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.ProductCategory
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
import kotlinx.coroutines.tasks.await

class ProductRepository {

    // 상품 데이터 추가 메서드
    suspend fun addProduct(productVO: ProductVO):String{
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("ProductData")
        val documentReference = collectionReference.add(productVO).await()
        return documentReference.id
    }

    // 상품 ID로 상품 데이터를 가져오는 메서드
    suspend fun selectProductDataOneById(productDocumentID: String): ProductVO {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("ProductData")
        val documentReference = collectionReference.document(productDocumentID)
        val documentSnapshot = documentReference.get().await()
        val productVO = documentSnapshot.toObject(ProductVO::class.java)!!
        return productVO
    }

    // 모든 상품 데이터를 가져오는 메서드
    suspend fun selectAllProductData(productCategory: String): MutableList<Map<String, *>> {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("ProductData")
        val result = if(productCategory == ProductCategory.PRODUCT_CATEGORY_ALL.str){
            collectionReference.orderBy("productSalesCount", Query.Direction.DESCENDING).get().await()
        } else{
            collectionReference.whereEqualTo("productCategory", productCategory)
                .orderBy("productSalesCount", Query.Direction.DESCENDING).get().await()
        }
        // 반환할 리스트
        val resultList = mutableListOf<Map<String, *>>()
        result.forEach {
            val map = mapOf(
                // 문서의 Id
                "productDocumentId" to it.id,
                // 데이터를 가지고 있는 객체
                "productVO" to it.toObject(ProductVO::class.java)
            )
            resultList.add(map)
        }
        return resultList
    }


}