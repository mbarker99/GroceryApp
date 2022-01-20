package com.example.groceryapp.view.dashboard

import com.example.groceryapp.data.model.response.Product

interface OnItemClickListener {
    fun onCategoryClicked(categoryId: String)
    fun onSubcategoryClicked(subCategoryId: String)
    fun onProductClicked(productId: String)
    fun onAddToCartClicked(position: Int, product: Product)
    fun onOrderClicked(orderId: String)
}