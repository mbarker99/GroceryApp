package com.example.groceryapp.view.dashboard

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.groceryapp.R
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.ActivityDashboardBinding
import com.example.groceryapp.presenter.dashboard.DashboardContract
import com.example.groceryapp.presenter.dashboard.DashboardPresenter
import com.example.groceryapp.view.dashboard.fragment.*


class DashboardActivity : AppCompatActivity(), DashboardContract.View, Communicator,
    OnItemClickListener {
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

        val sharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE)
        val header = binding.navigationView.getHeaderView(0)
        val headerName = header.findViewById<TextView>(R.id.tv_header_name)
        val headerEmail = header.findViewById<TextView>(R.id.tv_header_email)
        headerName.text = sharedPreferences.getString("full_name", "Name")
        headerEmail.text = sharedPreferences.getString("email_id", "Email")

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
                R.id.nav_item_account -> {
                    transaction.replace(R.id.container, AccountFragment()).commit()
                }
                R.id.nav_item_logout -> {
                    // TODO : make this a custom DialogFragment and add a ProgressBar
                    val dialog = AlertDialog.Builder(this).apply {
                        setTitle("Logout")
                        setMessage("Are you sure you want to logout?")
                        setPositiveButton("Yes") { dialog, which ->
                            logout()
                            dialog.dismiss()
                        }
                        setNegativeButton("No") { dialog, which ->
                            dialog.dismiss()
                        }
                    }.create()
                    dialog.show()
                }
            }

            true
        }

    }

    private fun logout() {
        presenter.logout()
    }

    override fun setResult() {
        finish()
    }

    override fun sendData(fragment: Fragment, message: String) {
        val bundle = Bundle()
        bundle.putString("category_id", message)

        val transaction = this.supportFragmentManager.beginTransaction()

        fragment.arguments = bundle
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("nav")
        transaction.commit()
    }

    override fun onClick(categoryId: String) {
        sendData(SubcategoriesFragment(), categoryId)
    }
}