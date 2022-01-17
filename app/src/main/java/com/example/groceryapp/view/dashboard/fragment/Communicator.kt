package com.example.groceryapp.view.dashboard.fragment

import androidx.fragment.app.Fragment

interface Communicator {
    fun sendData(fragment: Fragment, message: String)
}