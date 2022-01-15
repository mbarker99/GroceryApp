package com.example.groceryapp.presenter.home_fragment

import com.example.groceryapp.data.model.User

class HomeContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult()
    }

    interface Presenter {
        fun getCategories()
    }
}