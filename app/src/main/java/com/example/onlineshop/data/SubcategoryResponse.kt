package com.example.onlineshop.data

import java.io.Serializable


data class SubcategoryResponse(
    val message: String,
    val status: Int,
    val subcategories: List<Subcategory>
)

data class Subcategory(
    val category_id: String,
    val is_active: String,
    val subcategory_id: String,
    val subcategory_image_url: String,
    val subcategory_name: String
): Serializable {
    companion object{
        const val KEY_SUB_CATEGORY = "subCategory"
    }
}