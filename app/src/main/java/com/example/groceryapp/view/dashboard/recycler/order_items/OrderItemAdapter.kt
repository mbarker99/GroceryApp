package com.example.groceryapp.view.dashboard.recycler.order_items

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Item
import com.example.groceryapp.databinding.ItemViewOrderItemBinding

class OrderItemAdapter(var items: List<Item>) : RecyclerView.Adapter<OrderItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewOrderItemBinding.inflate(layoutInflater, parent, false)
        return OrderItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}