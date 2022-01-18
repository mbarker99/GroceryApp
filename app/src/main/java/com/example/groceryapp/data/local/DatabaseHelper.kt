package com.example.groceryapp.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){



    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(queryPayment)
        Log.d("SQL", "Table $TABLE_PAYMENTS created")
        db?.execSQL(queryAddress)
        Log.d("SQL", "Table $TABLE_ADDRESSES created")
        db?.execSQL(queryCart)
        Log.d("SQL", "Table $TABLE_CART created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val DATABASE_NAME = "UserDetails"
        const val DATABASE_VERSION = 1

        const val TABLE_PAYMENTS = "Payments"
        const val TABLE_ADDRESSES = "Addresses"
        const val TABLE_CART = "Cart"

        const val COL_NAME = "name"
        const val COL_CARD_NUMBER = "card_number"
        const val COL_EXPIRATION = "expiration"
        const val COL_SECURITY_CODE = "security_code"

        const val COL_ADDRESS = "address"
        const val COL_CITY = "city"
        const val COL_STATE = "state"
        const val COL_ZIP_CODE = "zip_code"
        const val COL_COUNTRY = "country"

        const val COL_PRODUCT_ID = "product_id"
        const val COL_PRICE = "price"
        const val COL_QUANTITY = "quantity"

        const val queryPayment = "CREATE TABLE $TABLE_PAYMENTS(id INTEGER PRIMARY KEY," +
                "$COL_NAME TEXT," +
                "$COL_CARD_NUMBER TEXT," +
                "$COL_EXPIRATION TEXT," +
                "$COL_SECURITY_CODE TEXT)"

        const val queryAddress = "CREATE TABLE $TABLE_ADDRESSES(id INTEGER PRIMARY KEY," +
                "$COL_NAME TEXT," +
                "$COL_ADDRESS TEXT," +
                "$COL_CITY TEXT," +
                "$COL_STATE TEXT," +
                "$COL_ZIP_CODE TEXT," +
                "$COL_COUNTRY TEXT)"

        const val queryCart = "CREATE TABLE $TABLE_CART(id INTEGER PRIMARY KEY," +
                "$COL_PRODUCT_ID TEXT," +
                "$COL_PRICE TEXT," +
                "$COL_QUANTITY TEXT)"

        const val KEY_SQL = "SQL"

    }

}