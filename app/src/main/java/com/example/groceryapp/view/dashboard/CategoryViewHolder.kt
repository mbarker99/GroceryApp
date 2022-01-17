package com.example.groceryapp.view.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewCategoryBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(val binding: ItemViewCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        val imageUrl = "${APIConstants.BASE_IMAGE_URL}${category.category_image_url}"
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
            .into(binding.ivCategoryImage)
        binding.tvCategoriesName.text = category.category_name

    }

}