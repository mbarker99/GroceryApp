package com.example.groceryapp.presenter.home_fragment

import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class HomePresenter(val volleyRequestHandler: VolleyRequestHandler, val view: HomeContract.View) :
    HomeContract.Presenter {
    override fun getCategories() {
        view.onLoad(true)
        val categories = volleyRequestHandler.getCategories(object: ResponseCallback {
            override fun onSuccess() {
                TODO("Not yet implemented")
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
    }

}