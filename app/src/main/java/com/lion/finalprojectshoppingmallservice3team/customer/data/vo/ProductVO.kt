package com.lion.finalprojectshoppingmallservice3team.customer.data.vo

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel

class ProductVO {
    var productSellerId : String = ""
    var productSellerName : String = ""
    var productName : String = ""
    var productPrice : Long = 0L
    var productImages : List<String> = emptyList()
    var productOpenStatus : String = ""
    var productSellingStatus : String = ""
    var productDiscount : Long = 0L
    var productCategory : String = ""
    var productSubCategory : String = ""
    var productLimitedSalesPeriod : String = ""
    var productLimitedSalesPerPerson : String = ""
    var productManagementAllQuantity : Long = 0L
    var productOption : HashMap<String, List<String>> = hashMapOf()
    var productInfoTitle : String = ""
    var productInfoContent : String = ""
    var productInfoImages : List<String> = emptyList()
    var productInformationNotice : String =""
    var productCertification : String = ""
    var productReviewCount : Long = 0L
    var productLikeCount : Long = 0L
    var productRating : Double = 0.0
    var productSalesCount : Long = 0L
    var productCreatedAt : Long = 0L
    var productUpdatedAt : Long = 0L

    fun toProductModel(productDocumentId: String): ProductModel {
        val productModel = ProductModel()
        productModel.productSellerId = productSellerId
        productModel.productDocumentId = productDocumentId
        productModel.productSellerName = productSellerName
        productModel.productName = productName
        productModel.productPrice = productPrice
        productModel.productImages = productImages
        productModel.productOpenStatus = productOpenStatus
        productModel.productSellingStatus = productSellingStatus
        productModel.productDiscount = productDiscount
        productModel.productCategory = productCategory
        productModel.productSubCategory = productSubCategory
        productModel.productLimitedSalesPeriod = productLimitedSalesPeriod
        productModel.productLimitedSalesPerPerson = productLimitedSalesPerPerson
        productModel.productManagementAllQuantity = productManagementAllQuantity
        productModel.productOption = productOption
        productModel.productInfoTitle = productInfoTitle
        productModel.productInfoContent = productInfoContent
        productModel.productInfoImages = productInfoImages
        productModel.productInformationNotice = productInformationNotice
        productModel.productCertification = productCertification
        productModel.productReviewCount = productReviewCount
        productModel.productLikeCount = productLikeCount
        productModel.productRating = productRating
        productModel.productSalesCount = productSalesCount
        productModel.productCreatedAt = productCreatedAt
        productModel.productUpdatedAt = productUpdatedAt

        return productModel
    }
}