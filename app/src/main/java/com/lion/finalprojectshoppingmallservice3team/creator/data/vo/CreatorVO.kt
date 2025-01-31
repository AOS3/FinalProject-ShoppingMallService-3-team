package com.lion.finalprojectshoppingmallservice3team.creator.data.vo

import com.lion.finalprojectshoppingmallservice3team.creator.data.model.CreatorModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.CreatorState

class CreatorVO {
    var creatorUserPhoneNumber: String = ""
    var creatorUserBirthDate: String = ""
    var creatorId: String = ""
    var creatorUserName: String = ""

    var creatorCompanyFile: String = ""
    var creatorPortfolioFile: String = ""
    var creatorPortfolioSite: String = ""

    var creatorCompanyName: String = ""
    var creatorComPosition: String = ""
    var creatorComNumber: Long = 0L

    var creatorUserState: Int = 0
    var creatorReturnNumber: String = ""
    var creatorInquery: String = ""

    var creatorUserAdvAgree : Boolean = false
    var creatorPersonInfoAgree: String = ""
    var creatorUserSmsAgree: String = ""
    var creatorUserAppPushAgree: String = ""
    var creatorfcmToken: String = ""
    var creatorUserCreatedAt: Long = 0L

    fun toCreatorModel(creatorDocumentId: String): CreatorModel {
        val creatorModel = CreatorModel()

        creatorModel.creatorDocumentId = creatorDocumentId
        creatorModel.creatorUserPhoneNumber = creatorUserPhoneNumber
        creatorModel.creatorUserBirthDate = creatorUserBirthDate
        creatorModel.creatorId = creatorId
        creatorModel.creatorUserName = creatorUserName
        creatorModel.creatorCompanyFile = creatorCompanyFile
        creatorModel.creatorPortfolioFile = creatorPortfolioFile
        creatorModel.creatorPortfolioSite = creatorPortfolioSite
        creatorModel.creatorCompanyName = creatorCompanyName
        creatorModel.creatorComPosition = creatorComPosition
        creatorModel.creatorComNumber = creatorComNumber
        creatorModel.creatorReturnNumber = creatorReturnNumber
        creatorModel.creatorInquery = creatorInquery
        creatorModel.creatorUserAdvAgree = creatorUserAdvAgree
        creatorModel.creatorPersonInfoAgree = creatorPersonInfoAgree
        creatorModel.creatorUserSmsAgree = creatorUserSmsAgree
        creatorModel.creatorUserAppPushAgree = creatorUserAppPushAgree
        creatorModel.creatorfcmToken = creatorfcmToken
        creatorModel.creatorUserCreatedAt = creatorUserCreatedAt

        when(creatorUserState){
            CreatorState.Creator_STATE_NORMAL.number -> creatorModel.creatorUserState = CreatorState.Creator_STATE_NORMAL
            CreatorState.Creator_STATE_SIGNOUT.number -> creatorModel.creatorUserState = CreatorState.Creator_STATE_SIGNOUT
        }

        return creatorModel
    }
}