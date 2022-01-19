package com.example.groceryapp.view.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.CartItem
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.databinding.ItemViewCartBinding
import com.example.groceryapp.databinding.ItemViewCategoryBinding

class CartAdapter(var cartList: ArrayList<CartItem>) : RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartList[position]
        holder.bind(cartItem)

    }

    override fun getItemCount(): Int = cartList.size

}