package com.example.groceryapp.presenter.dashboard

class DashboardContract {
    interface View {
        fun setResult()
    }

    interface Presenter {
        fun logout()
    }
}