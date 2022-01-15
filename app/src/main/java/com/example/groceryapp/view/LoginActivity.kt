package com.example.groceryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.ActivityLoginBinding
import com.example.groceryapp.presenter.login.LoginContract
import com.example.groceryapp.presenter.login.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginContract.View {
    lateinit var binding: ActivityLoginBinding
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val volleyRequestHandler = VolleyRequestHandler(this)
        presenter = LoginPresenter(volleyRequestHandler, this)
        
        binding.btnLogin.setOnClickListener {
            login()
        }

    }

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