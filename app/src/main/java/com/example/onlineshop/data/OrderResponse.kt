package com.example.onlineshop.data

data class OrderResponse(
    val message: String,
    val order: Order,
    val status: Int
)

data class Order(
    val address: String,
    val address_title: String,
    val bill_amount: String,
    val items: List<Item>,
    val order_date: String,
    val order_id: String,
    val order_status: String,
    val payment_method: String
)

data class Item(
    val amount: String,
    val description: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val quantity: String,
    val unit_price: String
)