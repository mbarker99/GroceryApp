package com.example.groceryapp.presenter.dashboard

import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class DashboardPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: DashboardContract.View
) : DashboardContract.Presenter {

    override fun logout() {

        view.onLoad(true)
        volleyRequestHandler.logout(object: ResponseCallback {
            override fun onSuccess() {
               view.onLoad(false)
                view.setResult()
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
    }

}