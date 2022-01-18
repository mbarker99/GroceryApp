package com.example.groceryapp.presenter.dashboard.product_details_fragment

import com.example.groceryapp.data.model.response.Product

class ProductDetailsContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(product: Product?)
    }

    interface Presenter {
        fun getProductDetails(productId: String) : Product?
    }
}