package com.example.groceryapp.presenter.dashboard.order_details_fragment

import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.presenter.dashboard.order_fragment.OrderContract

class OrderDetailsPresenter(val volleyRequestHandler: VolleyRequestHandler, val view: OrderDetailsContract.View) : OrderDetailsContract.Presenter {
    override fun getOrderDetails(orderId: String?): Order? {
        view.onLoad(true)
        var order: Order? = null
        volleyRequestHandler.setOrderDetails(orderId, object: ResponseCallback {
            override fun onSuccess() {
                order = volleyRequestHandler.orderDetails
                view.setResult(order)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
        return order
    }

}