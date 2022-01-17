package com.example.groceryapp.presenter.dashboard.home_fragment

import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class HomePresenter(val volleyRequestHandler: VolleyRequestHandler, val view: HomeContract.View) :
    HomeContract.Presenter {

    override fun getCategories(): List<Category> {
        view.onLoad(true)
        var categories = emptyList<Category>()
        volleyRequestHandler.setCategories(object : ResponseCallback {
            override fun onSuccess() {
                categories = volleyRequestHandler.categories
                view.setResult(categories)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }
        })
        return categories
    }

}