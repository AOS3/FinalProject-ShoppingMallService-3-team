package com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lion.finalprojectshoppingmallservice3team.ShoppingApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    val shoppingApplication = context as ShoppingApplication


    init {
        loadBannerImages()
//        loadCircularBoxItems()
    }


    /*****************************배너 화면*********************************/
    private val _bannerImages = mutableStateOf<List<String>>(emptyList())
    val bannerImages: List<String> get() = _bannerImages.value

//    private val _weeklyCreatorImages = mutableStateOf<List<String>>(emptyList())
//    val weeklyCreatorImages: List<String> get() = _weeklyCreatorImages.value

    private fun loadBannerImages() {
        viewModelScope.launch {
            // 데이터베이스에서 배너 이미지와 크리에이터 이미지 가져오기
//            val banners = repository.getBannerImages()
//            val creators = repository.getweeklyCreatorImages()

//            _bannerImages.value = banners
//            _weeklyCreatorImages.value = creators
        }
    }


    /*****************************배너 아래 이동 화면*********************************/
//    private val _circularBoxItems = mutableStateOf<List<CircularBoxItem>>(emptyList())
//    val circularBoxItems: List<CircularBoxItem> get() = _circularBoxItems.value

    private val _circularBoxItems = mutableStateOf(
        listOf(
            CircularBoxItem("https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg", "크리에이터", "action1"),
            CircularBoxItem("https://example.com/image2.jpg", "Item 2", "action2"),
            CircularBoxItem("https://example.com/image3.jpg", "Item 3", "action3"),
            CircularBoxItem("https://example.com/image4.jpg", "Item 4", "action4"),
            CircularBoxItem("https://example.com/image5.jpg", "Item 5", "action5")
        )
    )
    val circularBoxItems: List<CircularBoxItem> get() = _circularBoxItems.value


//    private fun loadCircularBoxItems() {
//        viewModelScope.launch {
//            val items = repository.getCircularBoxItems()
//            _circularBoxItems.value = items
//        }
//    }


    /*****************************WEEKLY RANKING*********************************/
    // Weekly Ranking 데이터
    private val _weeklyRankingData = mutableStateOf(
        listOf(
            RankItem(2, "LzPP", "", "https://example.com/image1.png", 0),
            RankItem(3, "싸이코드 연이", "크리에이터", "https://example.com/image2.png", 1),
            RankItem(4, "싸이코드 루이짠", "버츄얼", "https://example.com/image3.png", 6),
            RankItem(5, "기타 사용자", "", "https://example.com/image4.png", 8),
            RankItem(6, "핑크토끼", "", "https://example.com/image5.png", 13),
            RankItem(7, "파이팅맨", "", "https://example.com/image6.png", 4)
        )
    )
    val weeklyRankingData: List<RankItem> get() = _weeklyRankingData.value
}



// CircularBoxItem 데이터 클래스 정의
data class CircularBoxItem(
    val imgUrl: String,
    val text: String,
    val targetAction: String
)

data class RankItem(
    val rank: Int,
    val name: String,
    val subtitle: String,
    val imageUrl: String,
    val change: Int // 순위 변화 (양수: 상승, 음수: 하락)
)


