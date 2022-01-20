package com.example.groceryapp.view.dashboard.recycler.orders

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.databinding.ItemViewOrderBinding

class OrderViewHolder(val binding: ItemViewOrderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(order: Order) {
        binding.apply {
            tvOrderId.text = "Order Number: ${order.order_id}"
            tvOrderAddress.text = order.address
            tvOrderDate.text = order.order_date
            tvOrderPayment.text = order.payment_method
            tvOrderStatus.text = "Status: ${order.order_status}"
            tvOrderTotal.text = "Total: \$${order.bill_amount}"
        }
    }
}