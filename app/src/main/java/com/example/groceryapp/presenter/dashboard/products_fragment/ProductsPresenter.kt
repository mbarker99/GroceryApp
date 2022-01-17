package com.example.groceryapp.presenter.dashboard.products_fragment

import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class ProductsPresenter(val volleyRequestHandler: VolleyRequestHandler, val view: ProductsContract.View) : ProductsContract.Presenter {
    override fun getProducts(subcategoryId: String?): List<Product> {
        view.onLoad(true)
        var products = emptyList<Product>()
        volleyRequestHandler.setProducts(subcategoryId, object: ResponseCallback {
            override fun onSuccess() {
                products = volleyRequestHandler.products
                view.setResult(products)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
        return products
    }
}