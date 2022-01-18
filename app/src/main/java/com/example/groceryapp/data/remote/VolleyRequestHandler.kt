package com.example.groceryapp.data.remote

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.groceryapp.data.model.User
import com.example.groceryapp.data.model.response.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.Exception
import java.net.URLEncoder

class VolleyRequestHandler(val context: Context) {
    var queue: RequestQueue = Volley.newRequestQueue(context)
    lateinit var categories: List<Category>
    lateinit var subcategories: List<Subcategory>
    lateinit var products: List<Product>
    lateinit var productDetails: Product
    lateinit var searchResults: List<Product>

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
                val user = it.getJSONObject("user")
                val emailId = user.getString("email_id")
                val password = user.getString("password")

                if (it.getInt("status") != 0) {
                    Toast.makeText(context, message, Toast.LENGTH_LONG)
                        .show()  // I want this to be a Snackbar, probably
                    callback.onFailure()
                } else {
                    callback.onSuccess()
                }
                Log.d(TAG_USER_INFO, "UN: $emailId, PW: $password")
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

    fun login(emailId: String, password: String, callback: ResponseCallback) {
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

    fun setCategories(callback: ResponseCallback) {
        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_CATEGORY}"
        Log.d(TAG_REQUEST_URL, url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<CategoryResponse>() {}
                val gson = Gson()
                try {
                    val response: CategoryResponse = gson.fromJson(it, typeToken.type)
                    if (response.status != 0) {
                        Log.d(TAG_REQUEST_MESSAGE, response.message)
                        callback.onFailure()
                    } else {
                        this.categories = response.categories
                        callback.onSuccess()
                    }
                } catch (e: Exception) {
                    Log.e(TAG_GSON_ERROR, e.toString())
                    e.printStackTrace()
                    callback.onFailure()
                }
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }

    fun logout(callback: ResponseCallback) {
        val sharedPreferences = context.getSharedPreferences(
            "login_details",
            AppCompatActivity.MODE_PRIVATE
        )

        val emailId = sharedPreferences.getString("email_id", null)

        val data = JSONObject()
        data.put(APIConstants.KEY_EMAIL_ID, emailId)

        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_LOGOUT}"
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
                    sharedPreferences.edit().clear().apply()
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

    fun setSubcategories(categoryId: String?, callback: ResponseCallback) {
        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_SUBCATEGORIES}?${APIConstants.KEY_CATEGORY_ID}=$categoryId"
        Log.d(TAG_REQUEST_URL, url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<SubcategoryResponse>() {}
                val gson = Gson()
                try {
                    val response: SubcategoryResponse = gson.fromJson(it, typeToken.type)
                    if (response.status != 0) {
                        Log.d(TAG_REQUEST_MESSAGE, response.message)
                        callback.onFailure()
                    } else {
                        this.subcategories = response.subcategories
                        callback.onSuccess()
                    }
                } catch (e: Exception) {
                    Log.e(TAG_GSON_ERROR, e.toString())
                    e.printStackTrace()
                    callback.onFailure()
                }
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }

    fun setProducts(subcategoryId: String?, callback: ResponseCallback) {
        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_PRODUCTS}$subcategoryId"
        Log.d(TAG_REQUEST_URL, url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<ProductResponse>() {}
                val gson = Gson()
                try {
                    val response: ProductResponse = gson.fromJson(it, typeToken.type)
                    if (response.status != 0) {
                        Log.d(TAG_REQUEST_MESSAGE, response.message)
                        callback.onFailure()
                    } else {
                        this.products = response.products
                        callback.onSuccess()
                    }
                } catch (e: Exception) {
                    Log.e(TAG_GSON_ERROR, e.toString())
                    e.printStackTrace()
                    callback.onFailure()
                }
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }

    fun setProductDetails(productId: String?, callback: ResponseCallback) {
        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_PRODUCT_DETAILS}$productId"
        Log.d(TAG_REQUEST_URL, url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<ProductDetailsResponse>() {}
                val gson = Gson()
                try {
                    val response: ProductDetailsResponse = gson.fromJson(it, typeToken.type)
                    if (response.status != 0) {
                        Log.d(TAG_REQUEST_MESSAGE, response.message)
                        callback.onFailure()
                    } else {
                        this.productDetails = response.product
                        callback.onSuccess()
                    }
                } catch (e: Exception) {
                    Log.e(TAG_GSON_ERROR, e.toString())
                    e.printStackTrace()
                    callback.onFailure()
                }
            },
            {
                Log.e(TAG_VOLLEY_ERROR, it.toString())
                it.printStackTrace()
                callback.onFailure()
            }
        )
        queue.add(request)
    }

    fun setSearchedProductDetails(search: String?, callback: ResponseCallback) {
        val encodedSearch = URLEncoder.encode(search, "UTF-8")
        val url = "${APIConstants.BASE_URL}${APIConstants.ENDPOINT_PRODUCT_SEARCH}?${APIConstants.KEY_QUERY}=$encodedSearch"
        Log.d(TAG_REQUEST_URL, url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val typeToken = object : TypeToken<SearchResponse>() {}
                val gson = Gson()
                try {
                    val response: SearchResponse = gson.fromJson(it, typeToken.type)
                    if (response.status != 0) {
                        Log.d(TAG_REQUEST_MESSAGE, response.message)
                        callback.onFailure()
                    } else {
                        this.searchResults = response.products
                        callback.onSuccess()
                    }
                } catch (e: Exception) {
                    Log.e(TAG_GSON_ERROR, e.toString())
                    e.printStackTrace()
                    callback.onFailure()
                }
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
        const val TAG_USER_INFO = "UserInfo"
        const val TAG_GSON_ERROR = "GsonError"
    }
}