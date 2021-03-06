package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.data.model.response.Category
import com.example.groceryapp.view.dashboard.recycler.category.CategoryAdapter
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentHomeBinding
import com.example.groceryapp.presenter.dashboard.home_fragment.HomeContract
import com.example.groceryapp.presenter.dashboard.home_fragment.HomePresenter
import com.example.groceryapp.view.dashboard.OnItemClickListener

class HomeFragment : Fragment(), HomeContract.View {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: CategoryAdapter
    lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val volleyRequestHandler = VolleyRequestHandler(requireActivity().applicationContext)
        presenter = HomePresenter(volleyRequestHandler, this)
        adapter = CategoryAdapter(presenter.getCategories(), activity as OnItemClickListener)

        binding.rvCategories.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.rvCategories.adapter = adapter

        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressHorizontal.visibility = View.VISIBLE
        else
            binding.progressHorizontal.visibility = View.GONE
    }

    override fun setResult(categories: List<Category>) {
        adapter.update(categories)
    }
}