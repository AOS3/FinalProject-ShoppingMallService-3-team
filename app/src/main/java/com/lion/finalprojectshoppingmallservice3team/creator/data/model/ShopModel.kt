package com.lion.finalprojectshoppingmallservice3team.creator.data.model

import com.lion.finalprojectshoppingmallservice3team.creator.data.util.ShopState

import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.CreatorVO
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.ShopVO

class ShopModel {
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
    // 샵 상태
    var shopState = ShopState.Shop_STATE_NORMAL

    // VO로 변환
    fun toShopVO(): ShopVO {
        val shopVO = ShopVO()
        shopVO.shopCreatorName = shopCreatorName
        shopVO.shopName = shopName
        shopVO.shopCreatorId = shopCreatorId
        shopVO.shopDomainName = shopDomainName
        shopVO.shopBestSns = shopBestSns
        shopVO.shopBrandDescription = shopBrandDescription
        shopVO.shopBannerImage = shopBannerImage
        shopVO.shopProfileImage = shopProfileImage
        shopVO.shopCreatedAt = shopCreatedAt
        shopVO.shopUpdatedAt = shopUpdatedAt
        shopVO.shopLikeCount = shopLikeCount
        shopVO.shopState = shopState.number
        return shopVO
    }
}