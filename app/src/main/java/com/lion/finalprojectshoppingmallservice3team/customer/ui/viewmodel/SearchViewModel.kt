package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val shoppingApplication = context as ShoppingApplication

    // 탭 목록 정의
    val tabs: List<Pair<String, Int>> = listOf(
        "전체" to 0,
        "크리에이터" to 0,
        "상품" to 0,
    )

    // 현재 선택된 탭 인덱스 상태
    var selectedTabIndex = mutableStateOf(0)

    // 검색어 상태 관리
    val searchValue = mutableStateOf("")

    // 검색 결과
    val searchResults = mutableStateOf<List<Any>>(emptyList())

    // 최근 검색어 목록 관리
    val recentSearches = mutableStateListOf<String>()

    fun performSearch(query: String) {
        if (query.isBlank()) {
            searchResults.value = emptyList()
        } else {
            // 예: 크리에이터나 상품 리스트에서 검색
            val mockData = listOf("Creator1", "Product1")
            searchResults.value = mockData.filter { it.contains(query, ignoreCase = true) }
        }
    }

        // 탭 선택 시 호출되는 함수
        fun onTabSelected(index: Int) {
            selectedTabIndex.value = index
        }

        // 검색어 추가 함수
        fun addSearchQuery(query: String) {
            if (query.isNotBlank() && !recentSearches.contains(query)) {
                recentSearches.add(0, query)
            }
            searchValue.value = "" // 검색창 초기화
        }

        // 최근 검색어 삭제 함수
        fun removeSearchQuery(index: Int) {
            if (index in recentSearches.indices) {
                recentSearches.removeAt(index)
            }
        }
    }
