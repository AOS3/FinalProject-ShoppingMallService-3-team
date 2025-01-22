package com.lion.finalprojectshoppingmallservice3team.customer.data.model

import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO

class ProductModel {
    var productDocumentId: String = ""
    var name: String = ""
    var price: Long = 0L
    var imageUrl: String = ""
    var imageUrls: List<String> = emptyList()
    var description: String = ""
    var creator: String = ""
    var category: String = ""
    var subCategory: String = ""
    var isFavorite: Boolean = false
    var isLimited: Boolean = false
    var stockQuantity: Int = 0
    var rating: Float = 0.0f
    var reviewCount: Int = 0

    fun toProductVO(): ProductVO {
        val productVO = ProductVO()
        productVO.name = name
        productVO.price = price
        productVO.imageUrl = imageUrl
        productVO.imageUrls = imageUrls
        productVO.description = description
        productVO.creator = creator
        productVO.category = category
        productVO.subCategory = subCategory
        productVO.isFavorite = isFavorite
        productVO.isLimited = isLimited
        productVO.stockQuantity = stockQuantity
        productVO.rating = rating
        productVO.reviewCount = reviewCount
        return productVO
    }
}