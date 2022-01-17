package com.example.groceryapp.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.ActivityLoginBinding
import com.example.groceryapp.presenter.login.LoginContract
import com.example.groceryapp.presenter.login.LoginPresenter
import com.example.groceryapp.view.dashboard.DashboardActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var binding: ActivityLoginBinding
    lateinit var presenter: LoginPresenter
    lateinit var volleyRequestHandler: VolleyRequestHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyRequestHandler = VolleyRequestHandler(this)
        presenter = LoginPresenter(volleyRequestHandler, this)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        val sharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE)
        if (sharedPreferences.contains("user_id")) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

    }

    /*
        Email: michael@gmail.com
        Password: password
     */
    private fun login() {
        binding.apply {
            presenter.login(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressCircular.visibility = View.VISIBLE
        else
            binding.progressCircular.visibility = View.GONE
    }

    override fun setResult() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}