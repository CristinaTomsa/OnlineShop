package com.example.onlineshop.data

data class ProductInfo(
    val product_id: Long,
    val product_name: String,
    var qty: Int,
    var price: Float
)
