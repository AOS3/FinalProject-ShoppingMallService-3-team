package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.CreatorModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.CreatorService
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.UserState
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorApplyViewmodel @Inject constructor(
    @ApplicationContext context: Context,
    val creatorService: CreatorService,
    val customerService: CustomerService
): ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 샵 이름
    val shopName = mutableStateOf("")
    // 도메인 명
    val domainName = mutableStateOf("")
    // 본인 또는 브랜드 소개
    val brandDescription = mutableStateOf("")
    // 회사명
    val companyName = mutableStateOf("")
    // 회사 서류 제출
    val fileUploaded = mutableStateOf(false)
    // 연락처
    val creatorPhoneNumber = mutableStateOf("")
    // 대표SNS
    val bestSns = mutableStateOf("")
    // 포트폴리오 사이트
    val portfolioSite = mutableStateOf("")
    // 전체 동의
    val triCheckboxAllValue = mutableStateOf(ToggleableState.Off)
    // 개인정보 동의
    val checkboxPersonalInfoAgree = mutableStateOf(false)

    // 약관 체크박스를 눌렀을 때 호출되는 메서드
    fun triCheckboxAllValueOnClick(){
        if(triCheckboxAllValue.value == ToggleableState.On){
//            checkBoxUserJoinInfo1Value.value = true
//            checkBoxUserJoinInfo2Value.value = true
            checkboxPersonalInfoAgree.value = true
            updateApplySubmitButtonState()
        } else if(triCheckboxAllValue.value == ToggleableState.Off){
//            checkBoxUserJoinInfo1Value.value = false
//            checkBoxUserJoinInfo2Value.value = false
            checkboxPersonalInfoAgree.value = false
            updateApplySubmitButtonState()
        }
    }

    // 약관 요소들의 상태가 변경되었을 때
    fun checkBoxOnChanged(){
        // 체크된 체박스의 개수
        var checkedCount = 0

//        if(checkBoxUserJoinInfo1Value.value == true){
//            checkedCount++
//        }
//        if(checkBoxUserJoinInfo2Value.value == true){
//            checkedCount++
//        }
//        if(checkBoxUserJoinInfo3Value.value == true){
//            checkedCount++
//        }
        if(checkboxPersonalInfoAgree.value == true){
            checkedCount++
        }

        triCheckboxAllValue.value = if(checkedCount == 1){
            ToggleableState.On
        } else if(checkedCount == 0){
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }

        // 상태 업데이트 호출
        updateApplySubmitButtonState()
    }

    val isButtonSecondEnabled = mutableStateOf(false)
    val isButtonThirdEnabled = mutableStateOf(false)
    val isButtonSubmitEnabled = mutableStateOf(false)

    fun updateApplySecondButtonState() {
        // 조건: 아이디 중복 확인, 닉네임 중복 확인, 비밀번호 조건 만족, 약관 필수 체크
        isButtonSecondEnabled.value =
            shopName.value.isNotBlank() &&
                    domainName.value.isNotBlank() &&
                    brandDescription.value.isNotBlank() &&
                    companyName.value.isNotBlank()
    }

    fun updateApplyThirdButtonState() {
        // 조건: 아이디 중복 확인, 닉네임 중복 확인, 비밀번호 조건 만족, 약관 필수 체크
        isButtonThirdEnabled.value =
            creatorPhoneNumber.value.isNotBlank() &&
                    bestSns.value.isNotBlank() ||
                    portfolioSite.value.isNotBlank() ||
                    companyName.value.isNotBlank()
    }

    fun updateApplySubmitButtonState() {
        // 조건: 아이디 중복 확인, 닉네임 중복 확인, 비밀번호 조건 만족, 약관 필수 체크
        isButtonSubmitEnabled.value =
            shopName.value.isNotBlank() &&
                    domainName.value.isNotBlank() &&
                    brandDescription.value.isNotBlank() &&
                    companyName.value.isNotBlank() &&
                    creatorPhoneNumber.value.isNotBlank() &&
                    bestSns.value.isNotBlank() ||
                    portfolioSite.value.isNotBlank() ||
                    companyName.value.isNotBlank() ||
                    checkboxPersonalInfoAgree.value
    }

    // 첫번쨰 화면
    fun navigationFirstIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApply", inclusive = true)
    }

    fun buttonFirstNextOnClick(){
        shoppingApplication.navHostController.navigate("creatorApplySecond")
    }

    // 두번쨰 화면
    fun navigationSecondIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApplySecond", inclusive = true)
        shoppingApplication.navHostController.navigate("creatorApply"){
            launchSingleTop = true // 중복 생성 방지

        }
    }
    fun buttonSecondNextOnClick(){
        shoppingApplication.navHostController.navigate("creatorApplyThird")
    }

    // 세번쨰 화면
    fun navigationThirdIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApplyThird", inclusive = true)
        shoppingApplication.navHostController.navigate("creatorApplySecond"){
            launchSingleTop = true // 중복 생성 방지

        }
    }

    fun buttonThirdNextOnClick(){
        shoppingApplication.navHostController.navigate("")
    }



    fun onFileUpload() {
        fileUploaded.value = true
    }

    fun modifyIsCreator() {
        shoppingApplication.loginCustomerModel.isCreator = true

        // 수정한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO) {
                customerService.updateIsCreatorData(shoppingApplication.loginCustomerModel)
            }
            work1.join()
        }
    }

    // 가입 완료 버튼을 눌렀을 때
    fun buttonSubmitOnClick(){

        modifyIsCreator()

        // 저장할 데이터를 추출한다.
        val creatorModel = CreatorModel()

        creatorModel.creatorBestSns = bestSns.value
        creatorModel.creatorUserBirthDate = shoppingApplication.loginCustomerModel.customerUserBirthDate
        creatorModel.creatorUserPhoneNumber = shoppingApplication.loginCustomerModel.customerUserPhoneNumber
        creatorModel.creatorBrandDescription = brandDescription.value
        creatorModel.creatorCompanyName = companyName.value
        creatorModel.creatorComNumber = 0L
        creatorModel.creatorComPosition = ""
        creatorModel.creatorInquery = ""
        creatorModel.creatorPortfolioFile = ""
        creatorModel.creatorShopName = shopName.value
        creatorModel.creatorDomainName = domainName.value
        creatorModel.creatorUserNickName = shoppingApplication.loginCustomerModel.customerUserNickName
        creatorModel.creatorReturnNumber = ""
        creatorModel.creatorfcmToken = ""
        creatorModel.creatorCompanyFile = ""
        creatorModel.creatorUserName = shoppingApplication.loginCustomerModel.customerUserName
        creatorModel.creatorPortfolioSite = portfolioSite.value
        creatorModel.creatorUserAdvAgree = true
//        customerModel.isAdult = checkBoxUserJoinInfo1Value.value
//        customerModel.useAgree = checkBoxUserJoinInfo2Value.value
        if (checkboxPersonalInfoAgree.value == true){
            creatorModel.creatorPersonInfoAgree = "동의"
        } else {
            creatorModel.creatorPersonInfoAgree = "미동의"
        }
        creatorModel.creatorUserCreatedAt = System.nanoTime()
        creatorModel.creatorUserState = UserState.USER_STATE_NORMAL

        // 저장한다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                creatorService.addCreatorData(creatorModel)
            }
            work1.join()

            Toast.makeText(shoppingApplication, "크리에이터 신청이 완료되었습니다", Toast.LENGTH_SHORT).show()
            shoppingApplication.navHostController.popBackStack()
            shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
            shoppingApplication.navHostController.popBackStack("creatorApply", inclusive = true)
            shoppingApplication.navHostController.popBackStack("creatorApplySecond", inclusive = true)
            shoppingApplication.navHostController.popBackStack("creatorApplyThird", inclusive = true)
            shoppingApplication.navHostController.navigate("loginMyPage")
        }
    }
}