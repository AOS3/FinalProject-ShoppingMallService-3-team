package com.lion.finalprojectshoppingmallservice3team.customer.data.model

import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CustomerModel {
    var customerDocumentId: String = ""
    var customerUserId: String = ""
    var customerUserPw: String = ""
    var customerUserName: String = ""
    var customerUserNickName: String = ""
    var customerUserPhoneNumber: String = ""
    var customerUserProfileImage: String = ""
    var customerUserAddress: String = ""
    var customerUserDetailAddress: String = ""
    var customerUserState = UserState.USER_STATE_NORMAL
    var customerUserBirthDate: String = ""
    var customerUserGender: String = ""
    var customerUserAdvAgree : Boolean = false
    var customerUserSmsAgree: String = ""
    var customerUserAppPushAgree: String = ""
    var fcmToken: String = ""
    var customerUserCreatedAt: Long = 0L
    var customerUserUpdatedAt: Long = 0L
    var isCreator: Boolean = false

    // VO로 변환
    fun toCustomerVO(): CustomerVO {
        val customerVO = CustomerVO()
        customerVO.customerUserId = customerUserId
        customerVO.customerUserPw = customerUserPw
        customerVO.customerUserName = customerUserName
        customerVO.customerUserNickName = customerUserNickName
        customerVO.customerUserPhoneNumber = customerUserPhoneNumber
        customerVO.customerUserProfileImage = customerUserProfileImage
        customerVO.customerUserAddress = customerUserAddress
        customerVO.customerUserDetailAddress = customerUserDetailAddress
        customerVO.customerUserState = customerUserState.number
        customerVO.customerUserBirthDate = customerUserBirthDate
        customerVO.customerUserGender = customerUserGender
        customerVO.customerUserAdvAgree = customerUserAdvAgree
        customerVO.customerUserSmsAgree = customerUserSmsAgree
        customerVO.customerUserAppPushAgree = customerUserAppPushAgree
        customerVO.fcmToken = fcmToken
        customerVO.customerUserCreatedAt = customerUserCreatedAt
        customerVO.customerUserUpdatedAt = customerUserUpdatedAt
        customerVO.isCreator = isCreator
        return customerVO
    }
}