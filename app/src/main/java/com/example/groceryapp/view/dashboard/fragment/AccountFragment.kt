package com.example.groceryapp.view.dashboard.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.groceryapp.R
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.DialogAddAddressBinding
import com.example.groceryapp.databinding.FragmentAccountBinding
import com.example.groceryapp.presenter.dashboard.account_fragment.AccountContract
import com.example.groceryapp.presenter.dashboard.account_fragment.AccountPresenter

class AccountFragment : Fragment(), AccountContract.View {
    lateinit var binding: FragmentAccountBinding
    lateinit var volleyRequestHandler: VolleyRequestHandler
    lateinit var presenter: AccountPresenter
    var userId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        setUpEvents()
        return binding.root
    }

    private fun setUpEvents() {

        volleyRequestHandler = VolleyRequestHandler(requireContext())
        presenter = AccountPresenter(volleyRequestHandler, this)
        val sharedPreferences = requireActivity().getSharedPreferences("login_details", Context.MODE_PRIVATE)
        userId = Integer.parseInt(sharedPreferences.getString("user_id", null))

        binding.btnViewAddresses.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.replace(R.id.container, AddressesFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }

        binding.btnAddAddress.setOnClickListener {
            val dialog = Dialog(requireContext())
            val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)


            dialogBinding.apply {
                dialog.show()

                btnAdd.setOnClickListener {
                    if (userId != null) {
                        val title = etTitle.text.toString()
                        val address = etAddress.text.toString()
                        presenter.addAddress(userId, title, address)
                    } else {
                        Toast.makeText(requireContext(), "Something went wrong.", Toast.LENGTH_LONG).show()
                    }
                    dialog.dismiss()
                }
            }

        }
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setResult() {
        Toast.makeText(requireContext(), "Address added successfully.", Toast.LENGTH_LONG).show()
    }

}