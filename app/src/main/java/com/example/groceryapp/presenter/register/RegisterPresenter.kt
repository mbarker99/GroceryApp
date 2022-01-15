package com.example.groceryapp.presenter.register

import com.example.groceryapp.data.model.User
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class RegisterPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: RegisterContract.View
) : RegisterContract.Presenter {

    override fun register(user: User) {
        view.onLoad(true)
        volleyRequestHandler.register(user, object: ResponseCallback {
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