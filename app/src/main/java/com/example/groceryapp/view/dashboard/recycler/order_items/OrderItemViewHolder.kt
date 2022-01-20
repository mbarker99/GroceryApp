package com.example.groceryapp.view.dashboard.recycler.order_items

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Item
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewOrderItemBinding
import com.squareup.picasso.Picasso

class OrderItemViewHolder(val binding: ItemViewOrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.apply {
            tvItemName.text = item.product_name
            tvAmount.text = "Total: \$${item.amount}"
            tvProductDescription.text = item.description
            tvQuantity.text = "Qty: ${item.quantity}"
            tvUnitPrice.text = "Unit Price: \$${item.unit_price}"
            val imageUrl = "${APIConstants.BASE_IMAGE_URL}${item.product_image_url}"
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder).into(ivProductImage)
        }
    }
}