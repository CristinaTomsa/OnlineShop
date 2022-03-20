package com.example.onlineshop.sql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.onlineshop.data.Product

class ProductDao(context: Context){
    private val db = DBHelper(context).writableDatabase

    public fun addProduct(product: Product): Long {
        val values = ContentValues()
        values.put("product_name", product.product_name)
        values.put("price", product.price)

        return db.insert("product", null, values)
    }

    @SuppressLint("Range")
    public fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()
        val cursor = db.query("product", null, null, null, null, null, null)
        while(cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndex("product_id"))
            val name = cursor.getString(cursor.getColumnIndex("product_name"))
            val price = cursor.getFloat(cursor.getColumnIndex("price"))

            val product = Product(null, null, null, null, price, 0,null, name,null, null )

            products.add(product)
        }
        return products
    }

    fun deleteTable(){
       db.delete("product", null, null)
    }

    fun deleteProduct(id: String?){
        val whereClause = "product_id"
        val whereArgs = arrayOf(id)
        db.delete("product", whereClause, whereArgs)

    }

}