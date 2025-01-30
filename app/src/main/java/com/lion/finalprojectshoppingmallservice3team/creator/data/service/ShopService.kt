package com.lion.finalprojectshoppingmallservice3team.creator.data.service

import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.ShopRepository

class ShopService(val shopRepository: ShopRepository) {

    // 사용자 정보를 추가하는 메서드
    fun addShopData(shopModel: ShopModel){
        // 데이터를 VO에 담아준다.
        val shopVO = shopModel.toShopVO()
        // 저장하는 메서드를 호출한다.
        shopRepository.addShopData(shopVO)
    }
}