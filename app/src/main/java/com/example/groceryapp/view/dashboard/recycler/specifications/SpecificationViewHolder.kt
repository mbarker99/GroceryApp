package com.example.groceryapp.view.dashboard.recycler.specifications

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Specification
import com.example.groceryapp.databinding.ItemViewSpecificationsBinding

class SpecificationViewHolder(val binding: ItemViewSpecificationsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(spec: Specification) {
        binding.apply {
            tvSpecTitle.text = spec.title
            tvSpec.text = spec.specification
        }
    }
}