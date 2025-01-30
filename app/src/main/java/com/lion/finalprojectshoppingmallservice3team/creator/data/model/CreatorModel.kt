package com.lion.finalprojectshoppingmallservice3team.creator.data.model

import com.lion.finalprojectshoppingmallservice3team.creator.data.util.CreatorState

import com.lion.finalprojectshoppingmallservice3team.creator.data.vo.CreatorVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CreatorModel {
    // 크리에이터 문서
    var creatorDocumentId: String = ""
    // 크리에이터 연락처
    var creatorUserPhoneNumber: String = ""
    // 크리에이터 생년월일
    var creatorUserBirthDate: String = ""
    // 크리에이터 아이디
    var creatorId: String = ""
    // 크리에이터 실명
    var creatorUserName: String = ""
    // 회사 증빙 파일
    var creatorCompanyFile: String = ""
    // 포트폴리오 파일
    var creatorPortfolioFile: String = ""
    // 포트폴리오 사이트
    var creatorPortfolioSite: String = ""
    // 크리에이터 회사 이름
    var creatorCompanyName: String = ""
    // 크리에이터 소속
    var creatorComPosition: String = ""
    // 크리에이터 사업자번호
    var creatorComNumber: Long = 0L
    // 크리에이터 상태
    var creatorUserState = CreatorState.Creator_STATE_NORMAL
    // 크리에이터 반품문의 연락처
    var creatorReturnNumber: String = ""
    // 크리에이터 문의 방법 선택
    var creatorInquery: String = ""
    // 광고정보동의
    var creatorUserAdvAgree : Boolean = false
    // 개인정보동의
    var creatorPersonInfoAgree: String = ""
    // 문자수신동의
    var creatorUserSmsAgree: String = ""
    // 앱푸쉬동의
    var creatorUserAppPushAgree: String = ""
    // 앱푸쉬토큰
    var creatorfcmToken: String = ""
    // 크리에이터 가입시간
    var creatorUserCreatedAt: Long = 0L

    // VO로 변환
    fun toCreatorVO(): CreatorVO {
        val creatorVO = CreatorVO()
        creatorVO.creatorUserPhoneNumber = creatorUserPhoneNumber
        creatorVO.creatorUserBirthDate = creatorUserBirthDate
        creatorVO.creatorUserName = creatorUserName
        creatorVO.creatorCompanyFile = creatorCompanyFile
        creatorVO.creatorUserState = creatorUserState.number
        creatorVO.creatorPortfolioFile = creatorPortfolioFile
        creatorVO.creatorId = creatorId
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