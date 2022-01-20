package com.example.groceryapp.view.dashboard.recycler.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.databinding.ItemViewCategoryBinding
import com.example.groceryapp.view.dashboard.OnItemClickListener

class CategoryAdapter(var categories: List<Category>, var listener: OnItemClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun getItemCount(): Int = categories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)

        holder.itemView.setOnClickListener {
            listener.onCategoryClicked(category.category_id)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }


}