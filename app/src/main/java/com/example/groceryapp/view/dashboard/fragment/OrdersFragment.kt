package com.example.groceryapp.view.dashboard.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentOrdersBinding
import com.example.groceryapp.presenter.dashboard.order_fragment.OrderContract
import com.example.groceryapp.presenter.dashboard.order_fragment.OrderPresenter
import com.example.groceryapp.view.dashboard.OnItemClickListener
import com.example.groceryapp.view.dashboard.recycler.orders.OrderAdapter


class OrdersFragment : Fragment(), OrderContract.View {
    lateinit var binding: FragmentOrdersBinding
    lateinit var volleyRequestHandler: VolleyRequestHandler
    lateinit var presenter: OrderPresenter
    lateinit var adapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)
        volleyRequestHandler = VolleyRequestHandler(requireContext())
        presenter = OrderPresenter(volleyRequestHandler, this)

        val sharedPreferences =
            requireActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("user_id", "")

        adapter = OrderAdapter(presenter.getOrders(userId), activity as OnItemClickListener)

        binding.rvOrders.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrders.adapter = adapter

        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressHorizontal.visibility = View.VISIBLE
        else
            binding.progressHorizontal.visibility = View.GONE
    }

    override fun setResult(orders: List<Order>) {
        adapter.update(orders)
    }


}