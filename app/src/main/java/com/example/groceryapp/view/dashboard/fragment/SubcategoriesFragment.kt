package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.data.model.response.Subcategory
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentSubcategoriesBinding
import com.example.groceryapp.presenter.dashboard.subcategories_fragment.SubcategoriesContract
import com.example.groceryapp.presenter.dashboard.subcategories_fragment.SubcategoriesPresenter
import com.example.groceryapp.view.dashboard.OnItemClickListener
import com.example.groceryapp.view.dashboard.SubcategoryAdapter

class SubcategoriesFragment : Fragment(), SubcategoriesContract.View {
    lateinit var binding: FragmentSubcategoriesBinding
    lateinit var adapter: SubcategoryAdapter
    lateinit var presenter: SubcategoriesPresenter
    lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubcategoriesBinding.inflate(inflater, container, false)
        val volleyRequestHandler = VolleyRequestHandler(requireActivity().applicationContext)
        presenter = SubcategoriesPresenter(volleyRequestHandler, this)

        val categoryId = arguments?.getString("id")
        adapter = SubcategoryAdapter(presenter.getSubcategories(categoryId), activity as OnItemClickListener)

        communicator = activity as Communicator

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

    override fun setResult(subcategories: List<Subcategory>) {
        adapter.update(subcategories)
    }

}