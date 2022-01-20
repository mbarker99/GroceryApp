package com.example.groceryapp.view.dashboard.recycler.review

import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.data.model.response.Review
import com.example.groceryapp.databinding.ItemViewReviewBinding

class ReviewViewHolder(val binding: ItemViewReviewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(review: Review) {
        binding.apply {
            tvReviewTitle.text = review.review_title
            tvReviewAuthor.text = review.full_name
            tvReview.text = review.review
            tvReviewDate.text = review.review_date
        }
    }
}