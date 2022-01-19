package com.example.groceryapp.view.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.CartItem
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewCartBinding
import com.squareup.picasso.Picasso

class CartViewHolder(val binding: ItemViewCartBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cartItem: CartItem) {
        binding.tvName.text = cartItem.productName
        binding.tvQuantity.text = "Qty: ${cartItem.quantity}"
        binding.tvPrice.text = "\$ ${cartItem.price}"
    }

}