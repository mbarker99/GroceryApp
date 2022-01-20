package com.example.groceryapp.presenter.dashboard.products_fragment

import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class ProductsPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: ProductsContract.View
) : ProductsContract.Presenter {
    override fun getProducts(subcategoryId: String?): List<Product> {
        view.onLoad(true)
        var products = emptyList<Product>()
        volleyRequestHandler.setProducts(subcategoryId, object : ResponseCallback {
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

    override fun getSearchedProductDetails(search: String): List<Product> {
        view.onLoad(true)
        var searchResults: List<Product> = emptyList<Product>()
        volleyRequestHandler.setSearchedProductDetails(search, object : ResponseCallback {
            override fun onSuccess() {
                searchResults = volleyRequestHandler.searchResults
                view.setResult(searchResults)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
        return searchResults
    }
}