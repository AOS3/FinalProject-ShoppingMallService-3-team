package com.lion.finalprojectshoppingmallservice3team

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.kakao.sdk.user.UserApiClient
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.CustomerService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val customerService: CustomerService
) : ViewModel() {

    private val _navigationTarget = MutableStateFlow<String>("splash")
    val navigationTarget: StateFlow<String> get() = _navigationTarget

    private val shoppingApplication = context as ShoppingApplication

    fun getTokenFromPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("accessToken", null) // 저장된 토큰 반환
    }

    fun checkAutoLogin(context: Context) {
        val token = getTokenFromPreferences(context)

        if (token.isNullOrEmpty()) {
            // 토큰이 없으면 로그인 화면으로 이동
            shoppingApplication.isLoggedIn.value = false
            _navigationTarget.value = "home"
        } else {
            // Firebase에서 해당 토큰으로 사용자 정보 가져오기
            viewModelScope.launch {
                try {
                    val db = FirebaseFirestore.getInstance()
                    val querySnapshot = db.collection("CustomerData")
                        .whereEqualTo("autoLoginToken", token)
                        .get()
                        .await()

                    if (!querySnapshot.isEmpty) {
                        val document = querySnapshot.documents[0]
                        val customerModel = customerService.fetchCustomerData(document.id)

                        if (customerModel != null) {
                            shoppingApplication.loginCustomerModel = customerModel
                            shoppingApplication.isLoggedIn.value = true
                            _navigationTarget.value = "home" // 로그인 성공 후 홈 화면으로 이동
                        } else {
                            println("CustomerModel 생성 실패")
                            shoppingApplication.loginCustomerModel = CustomerModel()
                            shoppingApplication.isLoggedIn.value = false
                            _navigationTarget.value = "home" // 사용자 정보 로드 실패 시 로그인 화면으로 이동
                        }
                    } else {
                        shoppingApplication.loginCustomerModel = CustomerModel()
                        shoppingApplication.isLoggedIn.value = false
                        _navigationTarget.value = "home" // 사용자 정보가 없으면 로그인 화면으로 이동
                    }
                } catch (e: Exception) {
                    println("Firebase에서 사용자 정보 로드 실패: ${e.localizedMessage}")
                    _navigationTarget.value = "home"
                }
            }
        }
    }
}
