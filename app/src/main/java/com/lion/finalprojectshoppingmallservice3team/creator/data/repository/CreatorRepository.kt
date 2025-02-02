package com.lion.finalprojectshoppingmallservice3team.creator.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.CreatorVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CreatorRepository {
    // 사용자 정보를 추가하는 메서드
    fun addCreatorData(creatorVO: CreatorVO) {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("CreatorData")
        collectionReference.add(creatorVO)
    }
}