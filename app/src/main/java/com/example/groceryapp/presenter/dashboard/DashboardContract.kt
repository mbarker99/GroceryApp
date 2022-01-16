package com.example.groceryapp.presenter.dashboard

import com.example.groceryapp.data.model.category.Category

class DashboardContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult()
    }

    interface Presenter {
        fun logout()
    }
}