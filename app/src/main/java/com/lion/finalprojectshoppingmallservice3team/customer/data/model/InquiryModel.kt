package com.lion.finalprojectshoppingmallservice3team.customer.data.model

import com.lion.finalprojectshoppingmallservice3team.customer.data.util.InquiryState
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.InquiryVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO

class InquiryModel {
    var inquiryDocumentId: String = ""
    var inquiryCustomerUserId: String = ""
    var inquiryCustomerUserNickname: String = ""
    var inquiryCreatorUserId: String = ""
    var inquiryCreatorUserNickname: String = ""
    var inquiryProductId: String = ""
    var inquiryTitle : String = ""
    var inquiryContent : String = ""
    var inquiryState = InquiryState.INQUIRY_STATE_SUBMIT
    var inquiryCreatedAt: Long = 0L
    var inquiriesResponseCreatedAt: Long = 0L
    var inquiriesResponse: String = ""
    var inquiriesImage: String = ""
    var inquiriesResponseImage: String = ""
    var inquiriesisCreator: Boolean = false

    // VO로 변환
    fun toInquiryVO(): InquiryVO {
        val inquiryVO = InquiryVO()
        inquiryVO.inquiryCustomerUserId = inquiryCustomerUserId
        inquiryVO.inquiryCustomerUserNickname = inquiryCustomerUserNickname
        inquiryVO.inquiryCreatorUserId = inquiryCreatorUserId
        inquiryVO.inquiryCreatorUserNickname = inquiryCreatorUserNickname
        inquiryVO.inquiryProductId = inquiryProductId
        inquiryVO.inquiryTitle = inquiryTitle
        inquiryVO.inquiryContent = inquiryContent
        inquiryVO.inquiryState = inquiryState.number
        inquiryVO.inquiryCreatedAt = inquiryCreatedAt
        inquiryVO.inquiriesResponseCreatedAt = inquiriesResponseCreatedAt
        inquiryVO.inquiriesResponse = inquiriesResponse
        inquiryVO.inquiriesImage = inquiriesImage
        inquiryVO.inquiriesResponseImage = inquiriesResponseImage
        inquiryVO.inquiriesisCreator = inquiriesisCreator
        return inquiryVO
    }
}