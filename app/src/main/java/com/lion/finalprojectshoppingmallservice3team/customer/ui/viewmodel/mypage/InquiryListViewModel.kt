package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.mypage

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.InquiryModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.InquiryService
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
class InquiryListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val inquiryService: InquiryService
) : ViewModel(){
    val shoppingApplication = context as ShoppingApplication

    val checkBoxMyinquiryValue = mutableStateOf(false)
    val contentListState = mutableStateListOf<Map<String, *>>()  // 타입 변경

    fun inquiryWriteFabOnClick(){
        shoppingApplication.navHostController.navigate("inquiryWrite")
    }

    fun inquiryListOnClick(documentId:String){
        shoppingApplication.navHostController.popBackStack("inquiryList", inclusive = true)
        shoppingApplication.navHostController.navigate("inquiryRead/$documentId")
    }

    fun navigationOnClick(){
        shoppingApplication.navHostController.popBackStack("inquiryList", inclusive = true)
        shoppingApplication.navHostController.navigate("loginMyPage")
    }

    fun refreshContentList(){
        CoroutineScope(Dispatchers.Main).launch {
            val work1 = async(Dispatchers.IO){
                inquiryService.gettingInquiryList()
            }
            val recyclerViewList = work1.await()

            // 상태 관리 변수에 담아준다.
            contentListState.clear()
            contentListState.addAll(
                recyclerViewList.map { inquiry ->
                    mapOf(
                        "documentId" to inquiry.inquiryDocumentId,
                        "nickname" to inquiry.inquiryCustomerUserNickname,
                        "title" to inquiry.inquiryTitle,
                        "state" to inquiry.inquiryState.str,
                        "createdAt" to formatDateFromMillis(inquiry.inquiryCreatedAt)
                    )
                }
            )
        }
    }

    // Long 타입의 시간을 yyyy-MM-dd HH:mm:ss 형식으로 변환하는 함수
    fun formatDateFromMillis(timeInMillis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date(timeInMillis))
    }
}