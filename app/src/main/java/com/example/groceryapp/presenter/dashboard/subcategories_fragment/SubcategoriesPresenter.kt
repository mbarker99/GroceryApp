package com.example.groceryapp.presenter.dashboard.subcategories_fragment

import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class SubcategoriesPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: SubcategoriesContract.View
) : SubcategoriesContract.Presenter {

    override fun getSubcategories(categoryId: String?): List<Subcategory> {
        view.onLoad(true)
        var subcategories = emptyList<Subcategory>()
        volleyRequestHandler.setSubcategories(categoryId, object: ResponseCallback {
            override fun onSuccess() {
                subcategories = volleyRequestHandler.subcategories
                view.setResult(subcategories)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }
        })
        return subcategories
    }

}