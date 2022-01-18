package com.example.groceryapp.data.model.response

data class SearchResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)