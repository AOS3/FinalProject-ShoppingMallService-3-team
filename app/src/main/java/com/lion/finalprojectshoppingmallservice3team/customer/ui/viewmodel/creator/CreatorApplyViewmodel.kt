package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.CreatorModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.CreatorService
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.ShopService
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.CreatorState
import com.lion.finalprojectshoppingmallservice3team.creator.data.util.ShopState
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CreatorApplyViewmodel @Inject constructor(
    @ApplicationContext context: Context,
    val creatorService: CreatorService,
    val customerService: CustomerService,
    val shopService: ShopService
): ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 샵 이름
    var creatorShopName by mutableStateOf("")
    // 도메인 명
    var domainName by mutableStateOf("")
    // 본인 또는 브랜드 소개
    var brandDescription by mutableStateOf("")
    // 회사명
    var companyName by mutableStateOf("")
    // 회사 서류 제출
    var fileUploaded by mutableStateOf(false)
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
    // 여러 개의 이미지를 저장하는 상태 변수
    val imageBitmapCompanyList = mutableStateListOf<Bitmap>()
    val imageCompanyUriList = mutableStateListOf<String>()

    // 여러 개의 이미지를 저장하는 상태 변수
    val imageBitmapPortfolioList = mutableStateListOf<Bitmap>()
    val imagePortfolioUriList = mutableStateListOf<String>()

    // 첫번쨰 화면
    fun navigationFirstIconOnClick(){
        shoppingApplication.navHostController.popBackStack("creatorApply", inclusive = true)
        shoppingApplication.navHostController.navigate("home"){
            launchSingleTop = true // 중복 생성 방지

        }
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

        if (companyName == "샌드박스"
            || companyName == "미츄"
            || companyName == "패러블") {
            creatorModel.creatorComPosition = "사업자"
        } else {
            creatorModel.creatorComPosition = "개인"
        }

        creatorModel.creatorInquery = ""
        creatorModel.creatorReturnNumber = ""
        creatorModel.creatorfcmToken = ""
        creatorModel.creatorId = shoppingApplication.loginCustomerModel.customerUserId
        creatorModel.creatorUserName = shoppingApplication.loginCustomerModel.customerUserName
        creatorModel.creatorPortfolioSite = portfolioSite.value
        creatorModel.creatorUserAdvAgree = false
//        customerModel.isAdult = checkBoxUserJoinInfo1Value.value
//        customerModel.useAgree = checkBoxUserJoinInfo2Value.value
        if (checkboxPersonalInfoAgree.value == true){
            creatorModel.creatorPersonInfoAgree = "동의"
        } else {
            creatorModel.creatorPersonInfoAgree = "미동의"
        }
        creatorModel.creatorUserCreatedAt = System.currentTimeMillis()
        creatorModel.creatorUserState = CreatorState.Creator_STATE_NORMAL

        // 저장한다.
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val imageCompanyPaths = imageBitmapCompanyList.map { bitmap -> saveBitmapToFile(bitmap) } // Bitmap → 파일 변환
                val uploadedCompanyUrls = customerService.uploadImages(imageCompanyPaths) // Firebase Storage 업로드
                creatorModel.creatorCompanyFile = uploadedCompanyUrls.joinToString(",") // Firestore에 저장할 URL 리스트

                val imagePortfolioPaths = imageBitmapPortfolioList.map { bitmap -> saveBitmapToFile(bitmap) } // Bitmap → 파일 변환
                val uploadedPortfolioUrls = customerService.uploadImages(imagePortfolioPaths) // Firebase Storage 업로드
                creatorModel.creatorPortfolioFile = uploadedPortfolioUrls.joinToString(",") // Firestore에 저장할 URL 리스트

                val work1 = async(Dispatchers.IO) {
                    creatorService.addCreatorData(creatorModel)
                }
                work1.await()

                val shopModel = ShopModel().apply {
                    shopName = creatorShopName
                    shopDomainName = domainName
                    shopCreatorName = shoppingApplication.loginCustomerModel.customerUserName
                    shopBrandDescription = brandDescription
                    shopComposition = creatorModel.creatorComPosition == "사업자"
                    shopCompanyName = companyName
                    shopBestSns = bestSns.value
                    shopCreatedAt = System.currentTimeMillis()
                    shopCreatorId = shoppingApplication.loginCustomerModel.customerUserId
                    shopState = ShopState.Shop_STATE_NORMAL
                }

                val work2 = async(Dispatchers.IO) {
                    shopService.addShopData(shopModel)
                }
                work2.await()

                Toast.makeText(shoppingApplication, "크리에이터 신청이 완료되었습니다", Toast.LENGTH_SHORT).show()
                shoppingApplication.navHostController.popBackStack("loginMyPage", inclusive = true)
                shoppingApplication.navHostController.navigate("loginMyPage")
            } catch (e: Exception) {
                Toast.makeText(shoppingApplication, "이미지 업로드 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveBitmapToFile(bitmap: Bitmap): String {
        val file = File(shoppingApplication.filesDir, "image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.flush()
        outputStream.close()
        return file.absolutePath
    }

    fun onFileUpload() {
        fileUploaded = true
    }
}