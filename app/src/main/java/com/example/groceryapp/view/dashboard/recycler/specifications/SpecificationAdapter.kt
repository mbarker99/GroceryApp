package com.example.groceryapp.view.dashboard.recycler.specifications

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Specification
import com.example.groceryapp.databinding.ItemViewSpecificationsBinding

class SpecificationAdapter(var specs: List<Specification>) : RecyclerView.Adapter<SpecificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewSpecificationsBinding.inflate(layoutInflater, parent, false)
        return SpecificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(specs[position])
    }

    override fun getItemCount(): Int = specs.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(specs: List<Specification>) {
        this.specs = specs
        notifyDataSetChanged()
    }
}