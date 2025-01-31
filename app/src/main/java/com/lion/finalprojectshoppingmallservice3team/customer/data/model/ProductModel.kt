package com.lion.finalprojectshoppingmallservice3team.customer.data.model

import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO

class ProductModel {
    var productDocumentId : String = ""
    var productSellerId : String = ""
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
    var productRating : Long = 0L
    var productSalesCount : Long = 0L
    var productCreatedAt : Long = 0L
    var productUpdatedAt : Long = 0L


    fun toProductVO(): ProductVO {
        val productVO = ProductVO()
        productVO.productSellerId = productSellerId
        productVO.productName = productName
        productVO.productPrice = productPrice
        productVO.productImages = productImages
        productVO.productOpenStatus = productOpenStatus
        productVO.productSellingStatus = productSellingStatus
        productVO.productDiscount = productDiscount
        productVO.productCategory = productCategory
        productVO.productSubCategory = productSubCategory
        productVO.productLimitedSalesPeriod = productLimitedSalesPeriod
        productVO.productLimitedSalesPerPerson = productLimitedSalesPerPerson
        productVO.productManagementAllQuantity = productManagementAllQuantity
        productVO.productOption = productOption
        productVO.productInfoTitle = productInfoTitle
        productVO.productInfoContent = productInfoContent
        productVO.productInfoImages = productInfoImages
        productVO.productInformationNotice = productInformationNotice
        productVO.productCertification = productCertification
        productVO.productReviewCount = productReviewCount
        productVO.productLikeCount = productLikeCount
        productVO.productRating = productRating
        productVO.productSalesCount = productSalesCount
        productVO.productCreatedAt = productCreatedAt
        productVO.productUpdatedAt = productUpdatedAt


        return productVO
    }
}