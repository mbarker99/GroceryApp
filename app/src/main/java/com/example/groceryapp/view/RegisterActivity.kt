package com.example.groceryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.groceryapp.data.model.User
import com.example.groceryapp.data.remote.VolleyRequestHandler
import com.example.groceryapp.databinding.ActivityRegisterBinding
import com.example.groceryapp.presenter.register.RegisterContract
import com.example.groceryapp.presenter.register.RegisterPresenter

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    lateinit var binding: ActivityRegisterBinding
    lateinit var presenter: RegisterPresenter
    lateinit var volleyRequestHandler: VolleyRequestHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyRequestHandler = VolleyRequestHandler(this)
        presenter = RegisterPresenter(volleyRequestHandler, this)

        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        binding.apply {
            presenter.register(
                User(
                    etName.text.toString(),
                    etMobileNo.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            )
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading)
            binding.progressCircular.visibility = View.VISIBLE
        else
            binding.progressCircular.visibility = View.GONE
    }

    override fun setResult() {
        onBackPressed()
    }
}