package com.lion.finalprojectshoppingmallservice3team.creator.data.vo

import com.lion.finalprojectshoppingmallservice3team.creator.data.model.CreatorModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.CreatorState
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.ShopState

class ShopVO {
    // 샵 문서
    var shopDocumentId: String = ""
    // 샵 크리에이터 이름
    var shopCreatorName: String = ""
    // 샵 이름
    var shopName: String = ""
    // 샵 크리에이터 아이디
    var shopCreatorId: String = ""
    // 크리에이터 도메인
    var shopDomainName: String = ""
    // 대표 sns
    var shopBestSns: String = ""
    // 크리에이터 소개
    var shopBrandDescription: String = ""
    // MO 배너 이미지
    var shopBannerImage: String = ""
    // 프로필 이미지
    var shopProfileImage: String = ""
    // 샵 생성시간
    var shopCreatedAt: Long = 0L
    // 샵 업데이트 시간
    var shopUpdatedAt: Long = 0L
    // 샵 좋아요
    var shopLikeCount: Int = 0
    var shopState: Int = 0

    fun toShopModel(shopDocumentId: String): ShopModel {
        val shopModel = ShopModel()

        shopModel.shopDocumentId = shopDocumentId
        shopModel.shopName = shopName
        shopModel.shopCreatorId = shopCreatorId
        shopModel.shopDomainName = shopDomainName
        shopModel.shopBestSns = shopBestSns
        shopModel.shopBrandDescription = shopBrandDescription
        shopModel.shopBannerImage = shopBannerImage
        shopModel.shopProfileImage = shopProfileImage
        shopModel.shopCreatedAt = shopCreatedAt
        shopModel.shopUpdatedAt = shopUpdatedAt
        shopModel.shopLikeCount = shopLikeCount

        when(shopState){
            ShopState.Shop_STATE_NORMAL.number -> shopModel.shopState = ShopState.Shop_STATE_NORMAL
            ShopState.Shop_STATE_SIGNOUT.number -> shopModel.shopState = ShopState.Shop_STATE_SIGNOUT
        }

        return shopModel
    }
}