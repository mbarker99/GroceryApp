package com.example.groceryapp.presenter.dashboard.subcategories_fragment

import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.data.model.response.Subcategory

class SubcategoriesContract {
    interface View {
        fun onLoad(isLoading: Boolean)
        fun setResult(subcategories: List<Subcategory>)
    }

    interface Presenter {
        fun getSubcategories(categoryId: String?): List<Subcategory>
    }
}