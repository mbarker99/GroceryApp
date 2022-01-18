package com.example.groceryapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.groceryapp.data.model.response.Product

class DatabaseAccess(val context: Context) {
    val dbHelper = DatabaseHelper(context)
    val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addCartItem(product: Product): Boolean {
        val values = ContentValues()
        values.put(DatabaseHelper.COL_PRODUCT_ID, product.product_id)
        values.put(DatabaseHelper.COL_PRICE, product.price)
        values.put(DatabaseHelper.COL_QUANTITY, 1)
        val id = db.insert(DatabaseHelper.TABLE_CART, null, values)
        return id > 0
    }

}