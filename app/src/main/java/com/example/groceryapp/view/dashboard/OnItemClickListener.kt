package com.example.groceryapp.view.dashboard

interface OnItemClickListener {
    fun onCategoryClicked(categoryId: String)
    fun onSubcategoryClicked(subCategoryId: String)
}