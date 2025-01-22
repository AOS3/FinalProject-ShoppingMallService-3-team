package com.lion.finalprojectshoppingmallservice3team.customer.data.vo

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel

class ProductVO {
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

    fun toProductModel(productDocumentId: String): ProductModel {
        val productModel = ProductModel()
        productModel.productDocumentId = productDocumentId
        productModel.name = name
        productModel.price = price
        productModel.imageUrl = imageUrl
        productModel.imageUrls = imageUrls
        productModel.description = description
        productModel.creator = creator
        productModel.category = category
        productModel.subCategory = subCategory
        productModel.isFavorite = isFavorite
        productModel.isLimited = isLimited
        productModel.stockQuantity = stockQuantity
        productModel.rating = rating
        productModel.reviewCount = reviewCount
        return productModel
    }
}