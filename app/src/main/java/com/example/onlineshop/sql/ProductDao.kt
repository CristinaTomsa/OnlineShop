package com.example.onlineshop.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.ProductInfo

class ProductDao(context: Context){
    private val db = DBHelper(context).writableDatabase

    fun addProduct(product: Product): Long {
        val values = ContentValues()
        values.put("product_name", product.product_name)
        values.put("price", product.price)
        values.put("qty", product.qty)

        val productId = db.insert("product", null, values)
        return productId
    }

    @SuppressLint("Range")
    public fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()
        val cursor = db.query("product", null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("product_id"))
            val name = cursor.getString(cursor.getColumnIndex("product_name"))
            val price = cursor.getFloat(cursor.getColumnIndex("price"))
            val qty = cursor.getInt(cursor.getColumnIndex("qty"))

            val product = Product(null, 0,null, null, price,0,null, name,0,null,qty)

            products.add(product)
        }
        return products
    }

    fun deleteTable(){
       db.delete("product", null, null)
    }

    fun deleteProduct(id: Long){
        val whereClause = "product_id =?"
        val whereArgs = arrayOf("id")
        db.delete("product", whereClause, whereArgs)

    }

    fun updateProduct(product: Product, qty: Int){
        val values = ContentValues()
        values.put("product_name", product.product_name)
        values.put("price", product.price)
        values.put("qty", product.qty)

        /* product_id INTEGER PRIMARY KEY AUTOINCREMENT,
            product_name TEXT,
            product_price FLOAT,
            qty INTEGER*/

        var whereClause = "product_id =?"
        val whereArgs = arrayOf(product.product_id.toString())
        db.update("product", values, whereClause, whereArgs)

    }

}