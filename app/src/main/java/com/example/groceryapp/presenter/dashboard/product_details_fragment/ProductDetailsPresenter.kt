package com.example.groceryapp.presenter.dashboard.product_details_fragment

import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class ProductDetailsPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: ProductDetailsContract.View
) : ProductDetailsContract.Presenter {

    override fun getProductDetails(productId: String): Product? {
        view.onLoad(true)
        var product: Product? = null
        volleyRequestHandler.setProductDetails(productId, object: ResponseCallback {
            override fun onSuccess() {
                product = volleyRequestHandler.productDetails
                view.setResult(product)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
        return product
    }


}