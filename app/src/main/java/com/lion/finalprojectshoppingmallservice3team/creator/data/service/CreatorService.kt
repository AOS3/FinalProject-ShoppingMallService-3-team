package com.lion.finalprojectshoppingmallservice3team.creator.data.service

import com.lion.finalprojectshoppingmallservice3team.creator.data.model.CreatorModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel

class CreatorService(val creatorRepository: CreatorRepository) {
    // 사용자 정보를 추가하는 메서드
    fun addCreatorData(creatorModel: CreatorModel){
        // 데이터를 VO에 담아준다.
        val creatorVO = creatorModel.toCreatorVO()
        // 저장하는 메서드를 호출한다.
        creatorRepository.addCreatorData(creatorVO)
    }
}