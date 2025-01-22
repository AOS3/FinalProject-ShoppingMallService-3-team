package com.lion.finalprojectshoppingmallservice3team.customer.data.service

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.CustomerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.LoginResult
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.CustomerVO

class CustomerService(val customerRepository: CustomerRepository) {
    // 사용자 정보를 추가하는 메서드
    fun addCustomerData(customerModel: CustomerModel){
        // 데이터를 VO에 담아준다.
        val customerVO = customerModel.toCustomerVO()
        // 저장하는 메서드를 호출한다.
        customerRepository.addCustomerData(customerVO)
    }

    // 사용하려는 닉네임이 존재하는지 확인하는 메서드
    suspend fun checkJoinUserNickName(customerUserNickName:String) : Boolean{
        // 닉네임을 통해 사용자 정보를 가져온다.
        val customerVoList = customerRepository.selectUserDataByUserNickName(customerUserNickName)
        // 가져온 데이터가 있다면
        if(customerVoList.isNotEmpty()){
            return false
        }
        // 가져온 데이터가 없다면
        else {
            return true
        }
    }

    // 로그인 처리 메서드
    suspend fun checkLogin(loginUserId:String, loginUserPw:String) : LoginResult {
        // 로그인 결과
        var result = LoginResult.LOGIN_RESULT_SUCCESS

        // 입력한 아이디로 사용자 정보를 가져온다.
        val CustomerVoList = customerRepository.selectUserDataByUserId(loginUserId)

        // 가져온 사용자 정보가 없다면
        if(CustomerVoList.isEmpty()){
            result = LoginResult.LOGIN_RESULT_ID_NOT_EXIST
        } else {
            if(loginUserPw != CustomerVoList[0].customerUserPw){
                // 비밀번호가 다르다면
                result = LoginResult.LOGIN_RESULT_PASSWORD_INCORRECT
            }
            // 탈퇴한 회원이라면
            if(CustomerVoList[0].customerUserState == UserState.USER_STATE_SIGNOUT.number){
                result = LoginResult.LOGIN_RESULT_SIGNOUT_MEMBER
            }
        }
        return result
    }

    // 사용자 아이디를 통해 문서 id와 사용자 정보를 가져온다.
    // 사용자 아이디와 동일한 사용자의 정보 하나를 반환하는 메서드
    suspend fun selectUserDataByUserIdOne(customerUserId:String) : CustomerModel{
        val tempMap = customerRepository.selectUserDataByUserIdOne(customerUserId)

        val loginCustomerVo = tempMap["customer_vo"] as CustomerVO
        val loginCustomerDocumentId = tempMap["customer_document_id"] as String

        val loginCustomerModel = loginCustomerVo.toCustomerModel(loginCustomerDocumentId)

        return loginCustomerModel
    }

    // 사용하려는 닉네임이 존재하는지 확인하는 메서드
    suspend fun checkJoinUserId(customerUserId:String) : Boolean{
        // 닉네임을 통해 사용자 정보를 가져온다.
        val customerVoList = customerRepository.selectUserDataByUserId(customerUserId)
        // 가져온 데이터가 있다면
        if(customerVoList.isNotEmpty()){
            return false
        }
        // 가져온 데이터가 없다면
        else {
            return true
        }
    }

    // 사용자 데이터를 수정한다.
    suspend fun updateUserData(customerModel: CustomerModel){
        val customerVO = customerModel.toCustomerVO()
        customerRepository.updateUserData(customerVO, customerModel.customerDocumentId)
    }

    // 사용자 비밀번호 데이터를 수정한다.
    suspend fun updateUserPwData(customerModel: CustomerModel){
        val customerVO = customerModel.toCustomerVO()
        customerRepository.updateUserPwData(customerVO, customerModel.customerDocumentId)
    }

    // 사용자의 상태를 변경하는 메서드
    suspend fun updateUserState(customerDocumentId:String, newState:UserState){
        customerRepository.updateUserState(customerDocumentId, newState)
    }
}