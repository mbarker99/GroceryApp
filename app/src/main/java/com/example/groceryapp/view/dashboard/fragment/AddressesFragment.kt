package com.example.groceryapp.view.dashboard.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.data.model.response.Address
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.FragmentAddressesBinding
import com.example.groceryapp.presenter.dashboard.address_fragment.AddressContract
import com.example.groceryapp.presenter.dashboard.address_fragment.AddressPresenter
import com.example.groceryapp.view.dashboard.recycler.address.AddressAdapter

class AddressesFragment : Fragment(), AddressContract.View {
    lateinit var binding: FragmentAddressesBinding
    lateinit var presenter: AddressPresenter
    lateinit var volleyRequestHandler: VolleyRequestHandler
    lateinit var adapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressesBinding.inflate(inflater, container, false)
        volleyRequestHandler = VolleyRequestHandler(requireContext())
        presenter = AddressPresenter(volleyRequestHandler, this)

        val sharedPreferences = requireActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("user_id", "")

        adapter = AddressAdapter(presenter.getAddresses(userId))

        binding.rvAddresses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAddresses.adapter = adapter

        return binding.root
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setResult(addresses: List<Address>) {
        adapter.update(addresses)
    }

}