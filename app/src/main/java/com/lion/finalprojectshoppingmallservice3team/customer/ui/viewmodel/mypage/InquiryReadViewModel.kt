package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.InquiryModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.InquiryService
import com.lion.finalprojectshoppingmallservice3team.customer.data.util.InquiryState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InquiryReadViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val inquiryService: InquiryService,
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    var contentCustomerMapState = mutableStateMapOf<String, Any>()  // 타입 변경

    var contentAnswerMapState = mutableStateMapOf<String, Any>()  // 타입 변경

    fun navigationOnClick(){
        shoppingApplication.navHostController.popBackStack("inquiryRead", inclusive = true)
        shoppingApplication.navHostController.navigate("inquiryList")
    }

    // 데이터를 가져와 상태 관리 변수에 담아준다.
    fun gettingInquiryData(inquiryDocumentId:String){
        // 서버에서 데이터를 가져온다.
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO) {
                inquiryService.selectInquiryDataOneById(inquiryDocumentId)
            }
            val inquiryData = work1.await() ?: return@launch

            val inquiryCustomerMap = mapOf(
                "author" to inquiryData.inquiryCustomerUserNickname,
                "title" to inquiryData.inquiryTitle,
                "content" to inquiryData.inquiryContent,
                "attachment" to inquiryData.inquiriesImage,
                "date" to formatDateFromMillis(inquiryData.inquiryCreatedAt),
                "isAnswer" to false,
            )

            contentCustomerMapState.clear()
            contentCustomerMapState.putAll(inquiryCustomerMap)

            if (inquiryData.inquiryState == InquiryState.INQUIRY_STATE_SUCCESS) {
                val inquiryAnswerMap = mapOf(
                    "author" to inquiryData.inquiryCreatorUserNickname,
                    "content" to inquiryData.inquiriesResponse,
                    "date" to formatDateFromMillis(inquiryData.inquiriesResponseCreatedAt),
                    "attachment" to inquiryData.inquiriesResponseImage,
                    "isAnswer" to true,
                )

                contentAnswerMapState.clear()
                contentAnswerMapState.putAll(inquiryAnswerMap)
            }
        }
    }

    // Long 타입의 시간을 yyyy-MM-dd HH:mm:ss 형식으로 변환하는 함수
    fun formatDateFromMillis(timeInMillis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date(timeInMillis))
    }
}