package com.example.groceryapp.data.model.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.databinding.ItemViewCategoryBinding

class CategoryAdapter(var categories: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun getItemCount(): Int = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

}