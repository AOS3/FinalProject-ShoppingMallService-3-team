package com.lion.finalprojectshoppingmallservice3team.customer.data.service

import com.lion.finalprojectshoppingmallservice3team.customer.data.model.ProductModel
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.CustomerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.ProductRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.repository.SellerRepository
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.ProductVO
import com.lion.finalprojectshoppingmallservice3team.customer.data.vo.SellerVO

class ProductService(val productRepository: ProductRepository, /*val sellerRepository: SellerRepository*/) {

    suspend fun registerProduct(productVO: ProductVO): String {

        return productRepository.addProduct(productVO)
    }

    // 상품 ID로 상품 데이터를 가져오는 메서드
    suspend fun selectProductDataOneById(productDocumentID: String): ProductModel {
        // 상품 데이터를 가져온다.
        val productVO = productRepository.selectProductDataOneById(productDocumentID)
        // productModel 객체를 생성한다.
        val productModel = productVO.toProductModel(productDocumentID)

        return productModel
    }

    // 모든 상품 데이터를 가져오는 메서드
    suspend fun selectAllProductData(productCategory: String): MutableList<ProductModel> {
        // 상품 정보를 가져온다.
        val productList = mutableListOf<ProductModel>()
        val resultList = productRepository.selectAllProductData(productCategory)
        // 판매자 정보를 가져온다.
        // val sellerList = sellerRepository.selectSellerDataAll()
        // 판매자 정보를 맵에 담는다.
//        val sellerMap = mutableMapOf<String, String>()
//        sellerList.forEach {
//            val sellerDocumentId = it["seller_document_Id"] as String
//            val sellerVO = it["seller_vo"] as SellerVO
//            sellerMap[sellerDocumentId] = sellerVO.sellerShopName
//        }

        resultList.forEach {
            val productVO = it["productVO"] as ProductVO
            val productDocumentId = it["productDocumentId"] as String
            val productModel = productVO.toProductModel(productDocumentId)
            //productModel.productSellerId = sellerMap[productModel.productSellerId]!!
            productList.add(productModel)
        }
        return productList
    }
}