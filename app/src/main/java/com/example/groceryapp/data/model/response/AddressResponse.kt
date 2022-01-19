package com.example.groceryapp.data.model.response

data class AddressResponse(
    val addresses: List<Address>,
    val message: String,
    val status: Int
)

data class Address(
    val address: String,
    val address_id: String,
    val title: String
)