package com.example.groceryapp.data.model.response

data class OrderDetailsResponse(
    val message: String,
    val order: Order,
    val status: Int
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