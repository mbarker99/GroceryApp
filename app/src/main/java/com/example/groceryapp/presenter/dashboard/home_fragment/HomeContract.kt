package com.example.groceryapp.presenter.dashboard.home_fragment

import com.example.groceryapp.data.model.response.Category

class HomeContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(categories: List<Category>)
    }

    interface Presenter {
        fun getCategories(): List<Category>
    }
}