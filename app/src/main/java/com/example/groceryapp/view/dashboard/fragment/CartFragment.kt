package com.example.groceryapp.view.dashboard.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.data.local.DatabaseAccess
import com.example.groceryapp.data.model.CartItem
import com.example.groceryapp.databinding.FragmentCartBinding
import com.example.groceryapp.presenter.dashboard.cart_fragment.CartContract
import com.example.groceryapp.presenter.dashboard.cart_fragment.CartPresenter
import com.example.groceryapp.view.dashboard.recycler.cart.CartAdapter

class CartFragment : Fragment(), CartContract.View {
    lateinit var binding: FragmentCartBinding
    lateinit var presenter: CartContract.Presenter
    lateinit var adapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        presenter = CartPresenter(DatabaseAccess(requireContext()), this)
        val cartList = presenter.getCartList()
        Log.d("CartList", cartList.toString())
        adapter = CartAdapter(cartList)

        binding.rvCart.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.rvCart.adapter = adapter

        binding.tvTotal.text = "Total: \$${presenter.getCartTotal()}"
        binding.btnCheckout.setOnClickListener {

        }


        return binding.root
    }

    override fun setCartList(list: ArrayList<CartItem>) {

    }

}