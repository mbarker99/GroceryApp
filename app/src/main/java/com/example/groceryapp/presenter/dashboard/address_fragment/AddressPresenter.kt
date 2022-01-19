package com.example.groceryapp.presenter.dashboard.address_fragment

import com.example.groceryapp.data.model.response.Address
import com.example.groceryapp.data.remote.ResponseCallback
import com.example.groceryapp.data.remote.VolleyRequestHandler

class AddressPresenter(
    val volleyRequestHandler: VolleyRequestHandler,
    val view: AddressContract.View
) : AddressContract.Presenter {
    override fun getAddresses(userId: String?): List<Address> {
        view.onLoad(true)
        var addresses = emptyList<Address>()
        volleyRequestHandler.setAddresses(userId, object : ResponseCallback {
            override fun onSuccess() {
                addresses = volleyRequestHandler.addresses
                view.setResult(addresses)
                view.onLoad(false)
            }

            override fun onFailure() {
                view.onLoad(false)
            }

        })
        return addresses
    }

}