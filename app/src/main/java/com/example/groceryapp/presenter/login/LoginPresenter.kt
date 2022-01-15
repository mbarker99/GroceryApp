package com.example.groceryapp.presenter.login

import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class LoginPresenter(val volleyRequestHandler: VolleyRequestHandler, val view: LoginContract.View) :
    LoginContract.Presenter {

    override fun login(emailId: String, password: String, rememberMe: Boolean) {
        view.onLoad(true)
        volleyRequestHandler.login(emailId, password, rememberMe, object : ResponseCallback {
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