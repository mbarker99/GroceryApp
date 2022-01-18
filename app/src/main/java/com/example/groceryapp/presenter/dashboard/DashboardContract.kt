package com.example.groceryapp.presenter.dashboard

import com.example.groceryapp.data.model.response.Product

class DashboardContract {
    interface View {
        fun setResult()
    }

    interface Presenter {
        fun logout()
        fun getSearchedProductDetails(productId: String) : Product?
    }
}