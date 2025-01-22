package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.CustomerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecentCreatorViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel(){

    val shoppingApplication = context as ShoppingApplication

    // 최근 본 크리에이터 리스트를 StateFlow로 관리
    private val _recentCreators = MutableStateFlow<List<CustomerModel>>(emptyList())
    val recentCreators: StateFlow<List<CustomerModel>> = _recentCreators

    init {
        // 더미 데이터를 로드
        loadRecentCreators()
    }

    private fun loadRecentCreators() {
        // 예제용 더미 데이터
        val dummyCreators = listOf(
            CustomerModel().apply {
                customerUserNickName = "크리에이터1"
                customerUserProfileImage = "https://example.com/default-profile.png" // 기본 이미지 URL
                customerUserPhoneNumber = "010-1234-5678"
                isCreator = true
            },
            CustomerModel().apply {
                customerUserNickName = "크리에이터2"
                customerUserProfileImage = "https://example.com/default-profile.png"
                customerUserPhoneNumber = "010-2345-6789"
                isCreator = true
            },
            CustomerModel().apply {
                customerUserNickName = "크리에이터3"
                customerUserProfileImage = "https://example.com/default-profile.png"
                customerUserPhoneNumber = "010-3456-7890"
                isCreator = true
            }
        )

        // Flow에 데이터를 업데이트
        viewModelScope.launch {
            _recentCreators.emit(dummyCreators)
        }
    }
}