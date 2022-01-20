package com.example.groceryapp.presenter.dashboard.order_fragment

import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class OrderPresenter(val volleyRequestHandler: VolleyRequestHandler, val view: OrderContract.View) : OrderContract.Presenter {
    override fun getOrders(userId: String?): List<Order> {
        view.onLoad(true)
        var orders = emptyList<Order>()
        volleyRequestHandler.setOrders(userId, object : ResponseCallback {
            override fun onSuccess() {
                orders = volleyRequestHandler.orders
                view.setResult(orders)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }
        })
        return orders
    }

}