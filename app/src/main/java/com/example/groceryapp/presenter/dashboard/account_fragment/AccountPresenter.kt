package com.example.groceryapp.presenter.dashboard.account_fragment

import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class AccountPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: AccountContract.View
) : AccountContract.Presenter {
    override fun addAddress(userId: Int, title: String, address: String) {
        view.onLoad(true)
        volleyRequestHandler.addAddress(userId, title, address, object: ResponseCallback {
            override fun onSuccess() {
                view.setResult()
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
    }

}