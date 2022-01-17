package com.example.groceryapp.view.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.databinding.ItemViewCategoryBinding
import com.example.groceryapp.databinding.ItemViewSubcategoryBinding

class SubcategoryAdapter(var subcategories: List<Subcategory>, var listener: OnItemClickListener) : RecyclerView.Adapter<SubcategoryViewHolder>() {

    override fun getItemCount(): Int = subcategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewSubcategoryBinding.inflate(layoutInflater, parent, false)
        return SubcategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val subcategory = subcategories[position]
        holder.bind(subcategory)

        holder.itemView.setOnClickListener {
            listener.onClick(subcategory.category_id)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(subcategories: List<Subcategory>) {
        this.subcategories = subcategories
        notifyDataSetChanged()
    }


}