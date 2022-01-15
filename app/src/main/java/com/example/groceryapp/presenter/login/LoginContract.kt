package com.example.groceryapp.presenter.login

class LoginContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult()
    }

    interface Presenter {
        fun login(emailId: String, password: String)
    }
}