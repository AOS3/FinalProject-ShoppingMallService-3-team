package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.creator

import android.R.attr.enabled
import android.content.Context
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.annotation.HalfFloat
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide.init
import com.lion.finalprojectshoppingmallservice3team.Component.ChipState
import com.lion.finalprojectshoppingmallservice3team.Component.ChipStyle
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import com.lion.finalprojectshoppingmallservice3team.creator.data.model.ShopModel
import com.lion.finalprojectshoppingmallservice3team.creator.data.repository.CreatorRepository
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.CreatorService
import com.lion.finalprojectshoppingmallservice3team.creator.data.service.ShopService
import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.service.ProductService
import com.lion.finalprojectshoppingmallservice3team.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatorMainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    val shopService: ShopService,
    val productService: ProductService
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication

    val listUpdate = mutableStateOf(false)
    var creatorList = mutableStateOf<List<ShopModel>>(listOf())


    val chipElements: SnapshotStateList<ChipState> = mutableStateListOf(
        ChipState("전체", mutableStateOf(true)),
        ChipState("개인", mutableStateOf(false)),
        ChipState("기업", mutableStateOf(false)),
    )
    // 실시간 갱신해주는 옵저버용 데이터
    val tab = MutableLiveData<String>(chipElements[0].text)

    // 현재 탭
    val current = mutableStateOf("")
    // 검색 값을 받는 값
    val searchText = mutableStateOf("")
    // 최종 검색 값
    val companySearch = mutableStateOf("")

    // 옵저버용 데이터에 넣을 함수
    val observer = Observer<String>{
        loadCreatorData(it)
        if (it != "기업"){
            companySearch.value = ""
            searchFilterList.value = listOf()
        }
        current.value = it
    }
    // 기업 검색시 나올 리스트 값
    var searchFilterList = mutableStateOf<List<ShopModel>>(listOf())

    // 옵저버 실행
    init {
        tab.observeForever(observer)
    }
    override fun onCleared() {
        creatorList.value = emptyList()
        tab.removeObserver(observer)
        super.onCleared()
    }

    val chipState = ChipStyle(
        selectedColor = Color(0xFFA16DEB),
        selectedTextColor = Color.White,
        unselectedColor = Color.White,
        unselectedTextColor = Color.Black,
        chipTextStyle = Typography.bodySmall,
        chipModifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 10.dp)
    )

    var companyList = mutableStateListOf<String>("미츄","샌드박스","패러블")

    fun setEvent(idx:Int){
        chipElements.forEach {
            if (it == chipElements[idx])
                it.apply {
                    isSelected.value = true
                    tab.value = it.text
                }
            else
                it.apply {
                    isSelected.value = false
                }

        }

    }

    fun loadCreatorData(current: String){
        viewModelScope.launch{
            listUpdate.value = false
            creatorList.value = shopService.selectAllProductData(current)
            listUpdate.value = true
        }
    }


    fun searchSystem(){
        clickSystem(searchText.value)
    }

    fun clickSystem(value: String){
        companySearch.value = value
        searchFilterList.value = creatorList.value.filter{
            value == it.shopCompanyName
        }
    }
}