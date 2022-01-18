package com.example.groceryapp.data.model.response

data class ProductDetailsResponse(
    val message: String,
    val product: Product,
    val status: Int
)

data class Product(
    val average_rating: String,
    val category_id: String,
    val description: String,
    val images: List<Image>,
    val is_active: String,
    val price: String,
    val product_id: String,
    val product_image_url: String,
    val product_name: String,
    val reviews: List<Review>,
    val specifications: List<Specification>,
    val sub_category_id: String
)

data class Image(
    val display_order: String,
    val image: String
)

data class Review(
    val full_name: String,
    val rating: String,
    val review: String,
    val review_date: String,
    val review_id: String,
    val review_title: String,
    val user_id: String
)

data class Specification(
    val display_order: String,
    val specification: String,
    val specification_id: String,
    val title: String
)