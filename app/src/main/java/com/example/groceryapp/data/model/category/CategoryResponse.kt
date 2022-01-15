package com.example.groceryapp.data.model.category

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
)