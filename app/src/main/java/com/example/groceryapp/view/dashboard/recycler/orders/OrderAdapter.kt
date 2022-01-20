package com.example.groceryapp.view.dashboard.recycler.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.databinding.ItemViewOrderBinding
import com.example.groceryapp.view.dashboard.OnItemClickListener

class OrderAdapter(var orders: List<Order>, val listener: OnItemClickListener) : RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])

        holder.itemView.setOnClickListener {
            listener.onOrderClicked(orders[position].order_id)
        }
    }

    override fun getItemCount(): Int = orders.size


    @SuppressLint("NotifyDataSetChanged")
    fun update(orders: List<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }
}