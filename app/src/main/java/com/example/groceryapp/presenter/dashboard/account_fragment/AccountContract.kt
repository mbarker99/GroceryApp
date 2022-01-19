package com.example.groceryapp.presenter.dashboard.account_fragment

class AccountContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult()
    }
    interface Presenter {
        fun addAddress(userId: Int, title: String, address: String)
    }
}