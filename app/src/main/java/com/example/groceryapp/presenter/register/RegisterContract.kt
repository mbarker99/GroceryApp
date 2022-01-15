package com.example.groceryapp.presenter.register

import com.example.groceryapp.data.model.User

class RegisterContract {

    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult()
    }

    interface Presenter {
        fun register(user: User)
    }
}