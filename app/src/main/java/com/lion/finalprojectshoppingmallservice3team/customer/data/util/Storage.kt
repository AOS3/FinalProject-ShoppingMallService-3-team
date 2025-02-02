package com.lion.finalprojectshoppingmallservice3team.data

import java.util.UUID

data class Product(
    val productDocumentId: String,
    val name: String,     // 상품 이름
    val price: Long,    // 상품 가격
    val imageUrl: String,// 상품 이미지 URL (일단 기본값)
    val imageUrls: List<String> = emptyList(),
    val description: String, // 상품 설명
    val creator: String,  // 크리에이터 이름
    val category: String, // 대카테고리 (예: 의류, 굿즈 등)
    val subCategory: String, // 소카테고리 (예: 티셔츠, 키링 등)
    var isFavorite: Boolean, // 좋아요
    var isLimited: Boolean, // 한정판 여부
    val stockQuantity: Int, // 재고 수
    val rating: Float, // 상품 평점 (예: 4.5)
    val reviewCount: Int // 리뷰 수
)

object Storage {
    val products = mutableListOf(
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "티셔츠 Example Product",
            price = 15000,
            imageUrl = "https://s3.ap-northeast-2.amazonaws.com/univ-careet/FileData/Picture/202304/f50c81b9-3d33-49fd-b003-c530b53b0359_770x426.png",
            creator = "Creator A",
            category = "의류",
            subCategory = "티셔츠",
            isFavorite = false,
            imageUrls = listOf("https://s3.ap-northeast-2.amazonaws.com/univ-careet/FileData/Picture/202304/f50c81b9-3d33-49fd-b003-c530b53b0359_770x426.png",
                "https://m.mondayfactory.co.kr/web/product/big/202202/cb4b8b4863da262f0671c705eb014bc2.jpg",
                "https://blog.jandi.com/ko/wp-content/uploads/sites/4/2022/01/%EC%9E%94%EB%94%94%EA%B5%BF%EC%A6%88_%EC%A2%85%ED%95%A9%EB%AA%A9%EC%97%85-1.jpg"),
            description = "티셔츠입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 4.5f,
            reviewCount = 10
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "키링 Example Product",
            price = 10000,
            imageUrl = "",
            creator = "Creator B",
            category = "굿즈",
            subCategory = "키링",
            isFavorite = false,
            description = "키링입니다.",
            stockQuantity = 10,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "맨투맨 Example Product",
            price = 20000,
            imageUrl = "",
            creator = "Creator C",
            category = "의류",
            subCategory = "맨투맨",
            isFavorite = false,
            description = "맨투맨입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "아크릴굿즈 Example Product",
            price = 12000,
            imageUrl = "",
            creator = "Creator D",
            category = "굿즈",
            subCategory = "아크릴굿즈",
            isFavorite = false,
            description = "아크릴굿즈입니다.",
            stockQuantity = 12,
            isLimited = false,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "가방 Example Product",
            price = 30000,
            imageUrl = "",
            creator = "Creator E",
            category = "패션잡화",
            subCategory = "가방",
            isFavorite = false,
            description = "가방입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "쿠션 Example Product",
            price = 25000,
            imageUrl = "",
            creator = "Creator F",
            category = "쿠션/패브릭",
            subCategory = "쿠션/방석",
            isFavorite = false,
            description = "쿠션입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "마우스패드 Example Product",
            price = 18000,
            imageUrl = "",
            creator = "Creator G",
            category = "문구/오피스",
            subCategory = "마우스패드",
            isFavorite = false,
            description = "마우스패드입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "스마트톡 Example Product",
            price = 8000,
            imageUrl = "",
            creator = "Creator H",
            category = "폰액세서리",
            subCategory = "스마트톡",
            isFavorite = false,
            description = "스마트톡입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
        Product(
            productDocumentId = UUID.randomUUID().toString(),
            name = "카드 Example Product",
            price = 5000,
            imageUrl = "",
            creator = "Creator I",
            category = "스티커/지류",
            subCategory = "카드",
            isFavorite = false,
            description = "카드입니다.",
            stockQuantity = 0,
            isLimited = true,
            rating = 0f,
            reviewCount = 0
        ),
    )
}