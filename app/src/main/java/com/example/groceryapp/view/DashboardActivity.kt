package com.example.groceryapp.view

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.groceryapp.R
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.ActivityDashboardBinding
import com.example.groceryapp.presenter.dashboard.DashboardContract
import com.example.groceryapp.presenter.dashboard.DashboardPresenter
import com.example.groceryapp.presenter.home_fragment.HomePresenter
import com.example.groceryapp.view.fragment.CartFragment
import com.example.groceryapp.view.fragment.HomeFragment

class DashboardActivity : AppCompatActivity(), DashboardContract.View {
    lateinit var binding: ActivityDashboardBinding
    lateinit var presenter: DashboardPresenter
    lateinit var volleyRequestHandler: VolleyRequestHandler
    var fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpEvents()
    }

    private fun setUpEvents() {

        volleyRequestHandler = VolleyRequestHandler(this)
        presenter = DashboardPresenter(volleyRequestHandler, this)

        binding.btnNavDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.navigationView.setNavigationItemSelectedListener {
            it.isChecked = true
            binding.drawerLayout.closeDrawers()

            val transaction = fragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.nav_item_home -> {
                   transaction.replace(R.id.container, HomeFragment()).commit()
                }
                R.id.nav_item_cart -> {
                    transaction.replace(R.id.container, CartFragment()).commit()
                }
                R.id.nav_item_logout -> {
                    logout()
                }
            }

            true
        }

    }

    private fun logout() {
        presenter.logout()
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setResult() {
        finish()
    }
}