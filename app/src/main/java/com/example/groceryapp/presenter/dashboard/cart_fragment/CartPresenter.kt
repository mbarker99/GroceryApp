package com.example.groceryapp.presenter.dashboard.cart_fragment

import com.example.groceryapp.data.local.DatabaseAccess
import com.example.groceryapp.data.model.CartItem

class CartPresenter(val dao: DatabaseAccess, view: CartContract.View) : CartContract.Presenter {
    override fun getCartList(): ArrayList<CartItem> {
        return dao.getCartItems()
    }
    override fun getCartTotal(): Int {
        return dao.getCartTotal()
    }
}