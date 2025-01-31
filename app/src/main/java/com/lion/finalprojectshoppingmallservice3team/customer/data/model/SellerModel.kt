package com.lion.finalprojectshoppingmallservice3team.customer.data.model

import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.SellerVO

class SellerModel {
    var sellerDocumentId: String = ""
    var sellerName: String = ""
    var sellerPhoneNumber: String = ""
    var sellerBirthDay: String = ""
    var sellerEmail: String = ""
    var sellerDomainName: String = ""
    var sellerShopName: String = ""
    var sellerInformation: String = ""
    var sellerSubmitDocument: String = ""
    var sellerSnsAddress: String = ""
    var sellerPortfolioAddress: String = ""
    var sellerCompany: String = ""
    var sellerComPosition : String = ""
    var sellerComNumber : String = ""
    var sellerReturnNumber: String = ""
    var sellerInquiry: String = ""
    var sellerState: String = ""
    var sellerCreatedAt: Long = 0L

    fun toSellerVO(): SellerVO {
        val sellerVO = SellerVO()
        sellerVO.sellerName = sellerName
        sellerVO.sellerPhoneNumber = sellerPhoneNumber
        sellerVO.sellerBirthDay = sellerBirthDay
        sellerVO.sellerEmail = sellerEmail
        sellerVO.sellerDomainName = sellerDomainName
        sellerVO.sellerShopName = sellerShopName
        sellerVO.sellerInformation = sellerInformation
        sellerVO.sellerSubmitDocument = sellerSubmitDocument
        sellerVO.sellerSnsAddress = sellerSnsAddress
        sellerVO.sellerPortfolioAddress = sellerPortfolioAddress
        sellerVO.sellerCompany = sellerCompany
        sellerVO.sellerComPosition = sellerComPosition
        sellerVO.sellerComNumber = sellerComNumber
        sellerVO.sellerReturnNumber = sellerReturnNumber
        sellerVO.sellerInquiry = sellerInquiry
        sellerVO.sellerState = sellerState
        sellerVO.sellerCreatedAt = sellerCreatedAt

        return sellerVO
    }
}

