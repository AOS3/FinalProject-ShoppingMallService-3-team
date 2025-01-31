package com.lion.finalprojectshoppingmallservice3team.customer.data.vo

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.InquiryModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.InquiryState
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState

class InquiryVO {
    var inquiryCustomerUserId: String = ""
    var inquiryCustomerUserNickname: String = ""
    var inquiryCreatorUserId: String = ""
    var inquiryCreatorUserNickname: String = ""
    var inquiryProductId: String = ""
    var inquiryTitle : String = ""
    var inquiryContent : String = ""
    var inquiryState: Int = 0
    var inquiryCreatedAt: Long = 0L
    var inquiriesResponseCreatedAt: Long = 0L
    var inquiriesResponse: String = ""
    var inquiriesImage: String = ""
    var inquiriesResponseImage: String = ""
    var inquiriesisCreator: Boolean = false

    fun toInquiryModel(inquiryDocumentId: String): InquiryModel {
        val inquiryModel = InquiryModel()
        inquiryModel.inquiryDocumentId = inquiryDocumentId
        inquiryModel.inquiryCustomerUserId = inquiryCustomerUserId
        inquiryModel.inquiryCustomerUserNickname = inquiryCustomerUserNickname
        inquiryModel.inquiryCreatorUserId = inquiryCreatorUserId
        inquiryModel.inquiryCreatorUserNickname = inquiryCreatorUserNickname
        inquiryModel.inquiryProductId = inquiryProductId
        inquiryModel.inquiryTitle = inquiryTitle
        inquiryModel.inquiryContent = inquiryContent
        inquiryModel.inquiryCreatedAt = inquiryCreatedAt
        inquiryModel.inquiriesResponseCreatedAt = inquiriesResponseCreatedAt
        inquiryModel.inquiriesResponse = inquiriesResponse
        inquiryModel.inquiriesImage = inquiriesImage
        inquiryModel.inquiriesResponseImage = inquiriesResponseImage
        inquiryModel.inquiriesisCreator = inquiriesisCreator

        when(inquiryState){
            InquiryState.INQUIRY_STATE_SUBMIT.number -> inquiryModel.inquiryState = InquiryState.INQUIRY_STATE_SUBMIT
            InquiryState.INQUIRY_STATE_SUCCESS.number -> inquiryModel.inquiryState = InquiryState.INQUIRY_STATE_SUCCESS
            InquiryState.INQUIRY_STATE_DELETE.number -> inquiryModel.inquiryState = InquiryState.INQUIRY_STATE_DELETE
        }

        return inquiryModel
    }
}