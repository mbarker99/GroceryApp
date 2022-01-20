package com.example.groceryapp.view.dashboard.recycler.product_images

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Image
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewProductImagesBinding
import com.squareup.picasso.Picasso

class ImageViewHolder(val binding: ItemViewProductImagesBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        val imageUrl = "${APIConstants.BASE_IMAGE_URL}${image.image}"
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
            .into(binding.ivProductImage)
    }
}