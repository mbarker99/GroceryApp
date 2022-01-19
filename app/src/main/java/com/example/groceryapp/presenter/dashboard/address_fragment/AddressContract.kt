package com.example.groceryapp.presenter.dashboard.address_fragment

import com.example.groceryapp.data.model.response.Address

class AddressContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(addresses: List<Address>)
    }
    interface Presenter {
        fun getAddresses(userId: String?) : List<Address>
    }
}