package com.example.groceryapp.presenter.dashboard.order_details_fragment

import com.example.groceryapp.data.model.response.Order

class OrderDetailsContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(order: Order?)
    }

    interface Presenter {
        fun getOrderDetails(orderId: String?) : Order?
    }
}