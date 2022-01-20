package com.example.groceryapp.presenter.dashboard.order_fragment

import com.example.groceryapp.data.model.response.Order

class OrderContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(orders: List<Order>)
    }
    interface Presenter {
        fun getOrders(userId: String?) : List<Order>
    }
}