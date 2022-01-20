package com.example.groceryapp.view.dashboard.recycler.address

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.CartItem
import com.example.groceryapp.data.model.response.Address
import com.example.groceryapp.databinding.ItemViewAddressesBinding
import com.example.groceryapp.databinding.ItemViewCartBinding

class AddressViewHolder(val binding: ItemViewAddressesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(address: Address) {
        binding.tvAddressTitle.text = address.title
        binding.tvAddress.text = address.address
    }

}