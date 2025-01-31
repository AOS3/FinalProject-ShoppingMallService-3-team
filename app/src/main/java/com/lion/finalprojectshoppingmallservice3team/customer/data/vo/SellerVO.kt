package com.lion.finalprojectshoppingmallservice3team.customer.data.vo

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.SellerModel

class SellerVO {
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

    fun toSellerModel(): SellerModel {
        val sellerModel = SellerModel()
        sellerModel.sellerName = sellerName
        sellerModel.sellerPhoneNumber = sellerPhoneNumber
        sellerModel.sellerBirthDay = sellerBirthDay
        sellerModel.sellerEmail = sellerEmail
        sellerModel.sellerDomainName = sellerDomainName
        sellerModel.sellerShopName = sellerShopName
        sellerModel.sellerInformation = sellerInformation
        sellerModel.sellerSubmitDocument = sellerSubmitDocument
        sellerModel.sellerSnsAddress = sellerSnsAddress
        sellerModel.sellerPortfolioAddress = sellerPortfolioAddress
        sellerModel.sellerCompany = sellerCompany
        sellerModel.sellerComPosition = sellerComPosition
        sellerModel.sellerComNumber = sellerComNumber
        sellerModel.sellerReturnNumber = sellerReturnNumber
        sellerModel.sellerInquiry = sellerInquiry
        sellerModel.sellerState = sellerState
        sellerModel.sellerCreatedAt = sellerCreatedAt

        return sellerModel
    }
}