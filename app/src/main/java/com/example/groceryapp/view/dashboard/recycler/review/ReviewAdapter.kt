package com.example.groceryapp.view.dashboard.recycler.review

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.model.response.Review
import com.example.groceryapp.databinding.ItemViewReviewBinding

class ReviewAdapter(var reviews: List<Review>) : RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewReviewBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount(): Int = reviews.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }

}