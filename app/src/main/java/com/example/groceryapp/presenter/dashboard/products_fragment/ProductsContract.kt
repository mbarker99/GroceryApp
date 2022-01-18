package com.example.groceryapp.presenter.dashboard.products_fragment

import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.model.response.Subcategory

class ProductsContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(products: List<Product>)
    }

    interface Presenter {
        fun getProducts(subcategoryId: String?): List<Product>
        fun getSearchedProductDetails(search: String) : List<Product>
    }
}