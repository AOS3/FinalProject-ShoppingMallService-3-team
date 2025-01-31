package com.lion.finalprojectshoppingmallservice3team.customer.data.service

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.InquiryModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.InquiryRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.InquiryVO

class InquiryService(val inquiryRepository: InquiryRepository) {
    // 사용자 정보를 추가하는 메서드
    suspend fun addInquiryData(inquiryModel: InquiryModel){
        // 데이터를 VO에 담아준다.
        val customerVO = inquiryModel.toInquiryVO()
        // 저장하는 메서드를 호출한다.
        inquiryRepository.addInquiryData(customerVO)
    }

    // 글 목록을 가져오는 메서드
    suspend fun gettingInquiryList() : MutableList<InquiryModel>{
        // 글정보를 가져온다.
        val inquiryList = mutableListOf<InquiryModel>()
        val resultList = inquiryRepository.gettingInquiryList()

        resultList.forEach {
            val inquiryVO = it["inquiryVO"] as InquiryVO
            val documentId = it["documentId"] as String
            val inquiryModel = inquiryVO.toInquiryModel(documentId)
            inquiryList.add(inquiryModel)
        }

        return inquiryList
    }

    // 글의 문서 id를 통해 글 데이터를 가져온다.
    suspend fun selectInquiryDataOneById(documentId:String) : InquiryModel{
        // 글 데이터를 가져온다.
        val inquiryVO = inquiryRepository.selectInquiryDataOneById(documentId)
        // BoardModel객체를 생성한다.
        val inquiryModel = inquiryVO.toInquiryModel(documentId)

        return inquiryModel
    }

    // 이미지 데이터를 서버로 업로드 하는 메서드
    suspend fun uploadImage(sourceFilePath:String, serverFilePath:String){
        inquiryRepository.uploadImage(sourceFilePath, serverFilePath)
    }
}