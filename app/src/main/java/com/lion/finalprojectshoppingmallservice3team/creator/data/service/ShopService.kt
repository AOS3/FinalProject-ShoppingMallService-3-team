package com.lion.finalprojectshoppingmallservice3team.creator.data.service

import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.ShopRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.ShopVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
import org.checkerframework.checker.units.qual.s

class ShopService(val shopRepository: ShopRepository) {

    // 사용자 정보를 추가하는 메서드
    fun addShopData(shopModel: ShopModel){
        // 데이터를 VO에 담아준다.
        val shopVO = shopModel.toShopVO()
        // 저장하는 메서드를 호출한다.
        shopRepository.addShopData(shopVO)
    }

    // 모든 크리에이터 데이터를 가져오는 메서드
    suspend fun selectAllProductData(shopCategory: String): MutableList<ShopModel> {
        // 크리에이터 정보를 가져온다.
        val shopList = mutableListOf<ShopModel>()
        val resultList = shopRepository.selectAllShopData(shopCategory)

        resultList.forEach {
            val shopVO = it["shopVO"] as ShopVO
            val shopDocumentId = it["shopDocumentId"] as String
            val shopModel = shopVO.toShopModel(shopDocumentId)
            shopList.add(shopModel)
        }
        return shopList
    }
}