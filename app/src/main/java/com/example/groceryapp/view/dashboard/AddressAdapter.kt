package com.example.groceryapp.view.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Address
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.databinding.ItemViewAddressesBinding
import com.example.groceryapp.databinding.ItemViewCartBinding

class AddressAdapter(var addresses: List<Address>) : RecyclerView.Adapter<AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewAddressesBinding.inflate(layoutInflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address)

    }

    override fun getItemCount(): Int = addresses.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(addresses: List<Address>) {
        this.addresses = addresses
        notifyDataSetChanged()
    }

}