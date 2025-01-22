package com.lion.finalprojectshoppingmallservice3team.customer.data.vo

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState

class CustomerVO {
    var customerUserId: String = ""
    var customerUserPw: String = ""
    var customerUserName: String = ""
    var customerUserNickName: String = ""
    var customerUserPhoneNumber: String = ""
    var customerUserProfileImage: String = ""
    var customerUserAddress: String = ""
    var customerUserDetailAddress: String = ""
    var customerUserState: Int = 0
    var customerUserBirthDate: String = ""
    var customerUserGender: String = ""
    var customerUserAdvAgree: Boolean = false
    var customerUserSmsAgree: String = ""
    var customerUserAppPushAgree: String = ""
    var fcmToken: String = ""
    var customerUserCreatedAt: Long = 0L
    var customerUserUpdatedAt: Long = 0L
    var isCreator: Boolean = false

    fun toCustomerModel(customerDocumentId: String): CustomerModel {
        val customerModel = CustomerModel()

        customerModel.customerDocumentId = customerDocumentId
        customerModel.customerUserId = customerUserId
        customerModel.customerUserPw = customerUserPw
        customerModel.customerUserName = customerUserName
        customerModel.customerUserNickName = customerUserNickName
        customerModel.customerUserPhoneNumber = customerUserPhoneNumber
        customerModel.customerUserProfileImage = customerUserProfileImage
        customerModel.customerUserAddress = customerUserAddress
        customerModel.customerUserDetailAddress = customerUserDetailAddress
        customerModel.customerUserBirthDate = customerUserBirthDate
        customerModel.customerUserGender = customerUserGender
        customerModel.customerUserAdvAgree = customerUserAdvAgree
        customerModel.customerUserSmsAgree = customerUserSmsAgree
        customerModel.customerUserAppPushAgree = customerUserAppPushAgree
        customerModel.fcmToken = fcmToken
        customerModel.customerUserCreatedAt = customerUserCreatedAt
        customerModel.customerUserUpdatedAt = customerUserUpdatedAt
        customerModel.isCreator = isCreator

        when(customerUserState){
            UserState.USER_STATE_NORMAL.number -> customerModel.customerUserState = UserState.USER_STATE_NORMAL
            UserState.USER_STATE_SIGNOUT.number -> customerModel.customerUserState = UserState.USER_STATE_SIGNOUT
        }

        return customerModel
    }
}