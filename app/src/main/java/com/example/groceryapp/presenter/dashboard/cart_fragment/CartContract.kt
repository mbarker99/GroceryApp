package com.example.groceryapp.presenter.dashboard.cart_fragment

import com.example.groceryapp.data.model.CartItem

class CartContract {
    interface View {
        fun setCartList(list: ArrayList<CartItem>)
    }
    interface Presenter {
        fun getCartList() : ArrayList<CartItem>
        fun getCartTotal() : Int
    }
}