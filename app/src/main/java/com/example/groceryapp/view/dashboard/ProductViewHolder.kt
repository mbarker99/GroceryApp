package com.example.groceryapp.view.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(val binding: ItemViewProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        val imageUrl = "${APIConstants.BASE_IMAGE_URL}${product.product_image_url}"
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
            .into(binding.ivProductImage)
        binding.tvProductName.text = product.product_name

    }
}
