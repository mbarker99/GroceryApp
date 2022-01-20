package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.data.model.response.Order
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentOrderDetailsBinding
import com.example.groceryapp.presenter.dashboard.order_details_fragment.OrderDetailsContract
import com.example.groceryapp.presenter.dashboard.order_details_fragment.OrderDetailsPresenter
import com.example.groceryapp.view.dashboard.recycler.order_items.OrderItemAdapter

class OrderDetailsFragment : Fragment(), OrderDetailsContract.View {
    lateinit var binding: FragmentOrderDetailsBinding
    lateinit var volleyRequestHandler: VolleyRequestHandler
    lateinit var presenter: OrderDetailsPresenter
    lateinit var itemsAdapter: OrderItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        volleyRequestHandler = VolleyRequestHandler(requireContext())
        presenter = OrderDetailsPresenter(volleyRequestHandler, this)

        itemsAdapter = OrderItemAdapter(emptyList())
        binding.rvOrderItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvOrderItems.adapter = itemsAdapter

        val orderId = arguments?.getString("id")
        presenter.getOrderDetails(orderId)

        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {
    }

    override fun setResult(order: Order?) {
        binding.apply {
            if (order != null) {
                tvOrderId.text = "Order Number: ${order.order_id}"
                tvOrderAddress.text = order.address
                tvOrderDate.text = order.order_date
                tvOrderPayment.text = order.payment_method
                tvOrderStatus.text = "Status: ${order.order_status}"
                tvOrderTotal.text = "Total: \$${order.bill_amount}"
                itemsAdapter.update(order.items)
            }
        }
    }


}