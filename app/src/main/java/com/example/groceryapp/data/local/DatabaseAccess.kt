package com.example.groceryapp.data.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.groceryapp.data.model.CartItem
import com.example.groceryapp.data.model.response.Product
import java.lang.Exception

class DatabaseAccess(val context: Context) {
    val dbHelper = DatabaseHelper(context)
    val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addCartItem(product: Product): Boolean {
        val values = ContentValues()
        values.put(DatabaseHelper.COL_PRODUCT_ID, product.product_id)
        values.put(DatabaseHelper.COL_PRICE, product.price)
        values.put(DatabaseHelper.COL_QUANTITY, 1)
        values.put(DatabaseHelper.COL_NAME, product.product_name)
        val id = db.insert(DatabaseHelper.TABLE_CART, null, values)
        return id > 0
    }

    @SuppressLint("Range")
    fun getCartItems(): ArrayList<CartItem> {
        val cartItems = ArrayList<CartItem>()
        try {
            val cursor = db.query(DatabaseHelper.TABLE_CART, null, null, null, null, null, null)
            cursor?.let {
                while (it.moveToNext()) {
                    val productName = it.getString(it.getColumnIndex("name"))
                    val productId = it.getString(it.getColumnIndex("product_id"))
                    val price = it.getString(it.getColumnIndex("price"))
                    val quantity = it.getInt(it.getColumnIndex("quantity"))
                    val cartItem = CartItem(productId, productName, price, quantity)
                    cartItems.add(cartItem)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("DAO", "Added items $cartItems")
        return cartItems
    }

    @SuppressLint("Range")
    fun getCartTotal(): Int {
        var total = 0
        try {
            val cursor = db.query(DatabaseHelper.TABLE_CART, null, null, null, null, null, null)
            cursor?.let {
                while (it.moveToNext()) {
                    val price = Integer.parseInt(it.getString(it.getColumnIndex("price")))
                    total += price
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return total
    }

}