package com.example.groceryapp.presenter.dashboard

import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class DashboardPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: DashboardContract.View
) : DashboardContract.Presenter {

    override fun logout() {
        volleyRequestHandler.logout(object: ResponseCallback {
            override fun onSuccess() {
                view.setResult()
            }

            override fun onFailure() {

            }

        })
    }

    override fun getSearchedProductDetails(productId: String): Product? {
        return null
    }

}