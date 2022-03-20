package com.example.onlineshop.data

import java.io.Serializable

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)

data class Category(
    val category_id: String,
    val category_image_url: String,
    val category_name: String,
    val is_active: String
): Serializable{
    companion object{
        const val KEY_CATEGORY = "category"
    }
}