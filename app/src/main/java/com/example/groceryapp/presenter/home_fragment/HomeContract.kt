package com.example.groceryapp.presenter.home_fragment

import com.example.groceryapp.data.model.category.Category

class HomeContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(categories: List<Category>)
    }

    interface Presenter {
        fun getCategories(): List<Category>
    }
}