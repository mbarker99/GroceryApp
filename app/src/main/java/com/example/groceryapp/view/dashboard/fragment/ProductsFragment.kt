package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.data.model.response.Product
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentProductsBinding
import com.example.groceryapp.presenter.dashboard.products_fragment.ProductsContract
import com.example.groceryapp.presenter.dashboard.products_fragment.ProductsPresenter
import com.example.groceryapp.view.dashboard.OnItemClickListener
import com.example.groceryapp.view.dashboard.recycler.product.ProductAdapter

class ProductsFragment : Fragment(), ProductsContract.View {
    lateinit var binding: FragmentProductsBinding
    lateinit var adapter: ProductAdapter
    lateinit var presenter: ProductsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        val volleyRequestHandler = VolleyRequestHandler(requireActivity().applicationContext)
        presenter = ProductsPresenter(volleyRequestHandler, this)

        val subCategoryId = arguments?.getString("id")
        val isSearch =  arguments?.getBoolean("isSearch")

        adapter = if (isSearch == false)
            ProductAdapter(presenter.getProducts(subCategoryId), activity as OnItemClickListener)
        else
            ProductAdapter(presenter.getSearchedProductDetails(subCategoryId.toString()), activity as OnItemClickListener)

        binding.rvSubcategories.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.rvSubcategories.adapter = adapter

        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressHorizontal.visibility = View.VISIBLE
        else
            binding.progressHorizontal.visibility = View.GONE
    }

    override fun setResult(products: List<Product>) {
        adapter.update(products)
    }

}