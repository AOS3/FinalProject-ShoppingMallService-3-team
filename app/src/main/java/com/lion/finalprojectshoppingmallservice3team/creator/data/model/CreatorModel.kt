package com.lion.finalprojectshoppingmallservice3team.creator.data.model

import com.lion.finalprojectshoppingmallservice3team.creator.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.CreatorVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CreatorModel {
    var creatorDocumentId: String = ""
    var creatorUserPhoneNumber: String = ""
    var creatorUserBirthDate: String = ""
//    var creatorEmail: String = ""
    var creatorUserName: String = ""
    var creatorUserNickName: String = ""
    var creatorDomainName: String = ""
    var creatorShopName: String = ""

    var creatorBrandDescription: String = ""
    var creatorCompanyFile: String = ""

    var creatorBestSns: String = ""
    var creatorPortfolioFile: String = ""
    var creatorPortfolioSite: String = ""

    var creatorCompanyName: String = ""
    var creatorComPosition: String = ""
    var creatorComNumber: Long = 0L

    var creatorUserState = UserState.USER_STATE_NORMAL
    var creatorReturnNumber: String = ""
    var creatorInquery: String = ""

    var creatorUserAdvAgree : Boolean = false
    var creatorPersonInfoAgree: String = ""
    var creatorUserSmsAgree: String = ""
    var creatorUserAppPushAgree: String = ""
    var creatorfcmToken: String = ""
    var creatorUserCreatedAt: Long = 0L

    // VO로 변환
    fun toCreatorVO(): CreatorVO {
        val creatorVO = CreatorVO()
        creatorVO.creatorUserPhoneNumber = creatorUserPhoneNumber
        creatorVO.creatorUserBirthDate = creatorUserBirthDate
        creatorVO.creatorUserName = creatorUserName
        creatorVO.creatorUserNickName = creatorUserNickName
        creatorVO.creatorDomainName = creatorDomainName
        creatorVO.creatorShopName = creatorShopName
        creatorVO.creatorBrandDescription = creatorBrandDescription
        creatorVO.creatorCompanyFile = creatorCompanyFile
        creatorVO.creatorUserState = creatorUserState.number
        creatorVO.creatorBestSns = creatorBestSns
        creatorVO.creatorPortfolioFile = creatorPortfolioFile
        creatorVO.creatorPortfolioSite = creatorPortfolioSite
        creatorVO.creatorCompanyName = creatorCompanyName
        creatorVO.creatorComPosition = creatorComPosition
        creatorVO.creatorComNumber = creatorComNumber
        creatorVO.creatorReturnNumber = creatorReturnNumber
        creatorVO.creatorInquery = creatorInquery
        creatorVO.creatorUserAdvAgree = creatorUserAdvAgree
        creatorVO.creatorPersonInfoAgree = creatorPersonInfoAgree
        creatorVO.creatorUserSmsAgree = creatorUserSmsAgree
        creatorVO.creatorUserAppPushAgree = creatorUserAppPushAgree
        creatorVO.creatorfcmToken = creatorfcmToken
        creatorVO.creatorUserCreatedAt = creatorUserCreatedAt
        return creatorVO
    }
}