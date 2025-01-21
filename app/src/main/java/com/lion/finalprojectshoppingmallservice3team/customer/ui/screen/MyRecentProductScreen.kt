package com.lion.finalprojectshoppingmallservice3team.customer.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lion.finalprojectshoppingmallservice3team.Component.LikeLionProductList
import com.lion.finalprojectshoppingmallservice3team.Component.Product
import com.lion.finalprojectshoppingmallservice3team.customer.ui.viewmodel.MyRecentProductViewModel
import com.lion.finalprojectshoppingmallservice3team.data.Storage

@Composable
fun MyRecentProductScreen(myRecentProductViewModel: MyRecentProductViewModel = hiltViewModel()) {
    val dummyProducts = listOf(
        Product(
            productDocumentId = "1",
            name = "크리에이터 상품명",
            price = 15000,
            imageUrl = "", // 이미지 URL이 없는 경우 기본 이미지 사용
            imageUrls = listOf(), // 이미지 리스트 비워둠
            description = "상품 설명입니다.",
            creator = "크리에이터1",
            category = "굿즈",
            subCategory = "키링",
            isFavorite = false,
            isLimited = true,
            stockQuantity = 0, // 재고 없음
            rating = 4.5f,
            reviewCount = 10
        ),
        Product(
            productDocumentId = "2",
            name = "한정판 상품",
            price = 20000,
            imageUrl = "", // 이미지 URL이 없는 경우 기본 이미지 사용
            imageUrls = listOf(),
            description = "한정판 상품 설명입니다.",
            creator = "크리에이터2",
            category = "의류",
            subCategory = "티셔츠",
            isFavorite = true,
            isLimited = false,
            stockQuantity = 5, // 재고 있음
            rating = 5.0f,
            reviewCount = 20
        ),
        Product(
            productDocumentId = "3",
            name = "일반 상품",
            price = 5000,
            imageUrl = "",
            imageUrls = listOf(),
            description = "일반 상품 설명입니다.",
            creator = "크리에이터3",
            category = "굿즈",
            subCategory = "스티커",
            isFavorite = false,
            isLimited = false,
            stockQuantity = 15, // 재고 있음
            rating = 3.5f,
            reviewCount = 5
        ),
        Product(
            productDocumentId = "4",
            name = "Soldout 상품",
            price = 10000,
            imageUrl = "",
            imageUrls = listOf(),
            description = "Soldout 상품 설명입니다.",
            creator = "크리에이터4",
            category = "악세사리",
            subCategory = "팔찌",
            isFavorite = true,
            isLimited = true,
            stockQuantity = 0, // 재고 없음
            rating = 4.0f,
            reviewCount = 8
        )
    )


    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 10.dp)
        ) {
            // 상품 리스트를 2열 그리드로 표시
            LikeLionProductList(
                productList = dummyProducts,

//        /크리에이터 화면으로 이동/
                onCreatorNameClick = {  },
                onItemClick = {myRecentProductViewModel.listItemImageOnClick(it.productDocumentId)},

                onLikeClick = { myRecentProductViewModel.onLikeClick(it) },
                columns = 2
            )
        }
    }
}