package com.example.groceryapp.view.dashboard.recycler.subcategory

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.data.remote.APIConstants
import com.example.groceryapp.databinding.ItemViewCategoryBinding
import com.example.groceryapp.databinding.ItemViewSubcategoryBinding
import com.squareup.picasso.Picasso

class SubcategoryViewHolder(val binding: ItemViewSubcategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(subcategory: Subcategory) {
        val imageUrl = "${APIConstants.BASE_IMAGE_URL}${subcategory.subcategory_image_url}"
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_placeholder)
            .into(binding.ivSubcategoryImage)
        binding.tvSubcategoriesName.text = subcategory.subcategory_name

    }

}