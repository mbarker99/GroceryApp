package com.example.groceryapp.data.model.response

data class ProductResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)

