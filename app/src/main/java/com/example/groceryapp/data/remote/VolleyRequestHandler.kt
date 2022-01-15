package com.example.groceryapp.data.remote

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.groceryapp.data.model.User
import com.example.groceryapp.presenter.login.LoginContract
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class VolleyRequestHandler(val context: Context) {
    var queue: RequestQueue = Volley.newRequestQueue(context)

    fun register(user: User, callback: ResponseCallback) {
        val data = JSONObject()
        data.put(APIConstants.KEY_FULL_NAME, user.fullName)
        data.put(APIConstants.KEY_MOBILE_NO, user.mobileNo)
        data.put(APIConstants.KEY_EMAIL_ID, user.emailId)
        data.put(APIConstants.KEY_PASSWORD, user.password)

        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_REGISTER}"
        Log.d(TAG_REQUEST_URL, url)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            {
                val message = it.getString("message")
                if (it.getInt("status") != 0) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG)
                        .show()  // I want this to be a Snackbar, probably
                    callback.onFailure()
                } else {
                    callback.onSuccess()
                }
                Log.d(TAG_REQUEST_MESSAGE, message)
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }


    fun login(emailId: String, password: String, rememberMe: Boolean, callback: ResponseCallback) {
        val data = JSONObject()
        data.put(APIConstants.KEY_EMAIL_ID, emailId)
        data.put(APIConstants.KEY_PASSWORD, password)

        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_LOGIN}"
        Log.d(TAG_REQUEST_URL, url)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            data,
            {
                val message = it.getString("message")
                if (it.getInt("status") != 0) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG)
                        .show()  // I want this to be a Snackbar, probably
                    callback.onFailure()
                } else {
                    val user = it.getJSONObject("user")
                    val userId = user.getString("user_id")
                    val fullName = user.getString("full_name")
                    val mobileNo = user.getString("mobile_no")
                    val emailId = user.getString("email_id")

                    if (rememberMe) {
                        val sharedPreferences = context.getSharedPreferences(
                            "login_details",
                            AppCompatActivity.MODE_PRIVATE
                        )
                        val editor = sharedPreferences.edit()
                        editor.putString("user_id", userId)
                        editor.putString("full_name", fullName)
                        editor.putString("mobile_no", mobileNo)
                        editor.putString("email_id", emailId)
                        editor.apply()
                    }

                    callback.onSuccess()
                }
                Log.d(TAG_REQUEST_MESSAGE, message)
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }

    companion object {
        const val TAG_REQUEST_URL = "RequestURL"
        const val TAG_REQUEST_MESSAGE = "RequestMessage"
        const val TAG_VOLLEY_ERROR = "VolleyError"
    }
}