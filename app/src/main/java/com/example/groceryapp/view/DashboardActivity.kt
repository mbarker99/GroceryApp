package com.example.groceryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceryapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}