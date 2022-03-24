package com.example.onlineshop.data

import java.io.Serializable


data class ProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)

data class Product(
    val average_rating: String?,
    val category_id: Int,
    val category_name: String?,
    val description: String?,
    val price: Float,
    val product_id: Long,
    val product_image_url: String?,
    val product_name: String?,
    val sub_category_id: Int,
    val subcategory_name: String?,
    var qty: Int
): Serializable {
    companion object {
        const val KEY_PRODUCT = "product"
    }
}