package com.example.onlineshop.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.onlineshop.data.Product

//
//class DBHelper(var mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {
//
//    var database = writableDatabase
//
//    companion object{
//        const val DATABASE_NAME = "ProductDB"
//        const val TABLE_NAME = "product"
//        const val DATABASE_VERSION = 1
//        const val COL_ID = "product_id"
//        const val COL_NAME = "product_name"
//        const val COL_PRICE = "price"
//        const val COL_IMAGE = "product_image_url"
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        var createTable = "create table $TABLE_NAME (${COL_ID} INTEGER, $COL_NAME char(50), $COL_PRICE Decimal," +
//                "$COL_IMAGE char(50))"
//        db?.execSQL(createTable)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        var dropTable = "DROP table $TABLE_NAME"
//        db?.execSQL(dropTable)
//        onCreate(db)
//    }
//
//    //add the product to the database
//    fun addProduct(product: Product){
//        if(!isProductInCart(product)) {
//            var contentValue = ContentValues()
//            contentValue.put(COL_ID, product.product_id)
//            contentValue.put(COL_NAME, product.product_name)
//            contentValue.put(COL_PRICE, product.price)
//            contentValue.put(COL_IMAGE, product.product_image_url)
//            database.insert(TABLE_NAME, null, contentValue)
//        }else {
//            //duplicate query
//        }
//    }
//
//    //update the product
////    fun updateProduct(updateProduct: Product){
////        var whereClause = "$COL_ID = ?"
////        val whereArgs = arrayOf(updateProduct.product_id)
////        var contentValues = ContentValues()
////        contentValues.put(COL_QUANTITY, updateProduct)
////        database.update(TABLE_NAME, contentValues, whereClause, whereArgs)
////    }
//
//    fun isProductInCart(product: Product):Boolean{
//        var query = "select * from $TABLE_NAME where $COL_ID = ?"
//        var cursor = database.rawQuery(query, arrayOf(product.product_id))
//        var count = cursor.count
//        return count != 0
//    }
//
//
//    //delete the product
//    fun deleteProduct(id: String?){
//        val whereClause = "$COL_ID = ?"
//        val whereArgs = arrayOf(id)
//        database.delete(TABLE_NAME, whereClause, whereArgs)
//
//    }
//
//    //delete cart
//    fun deleteTable(){
//        database.delete(TABLE_NAME, null, null)
//    }
//
//    //read the product from the database
//    fun readProduct(): ArrayList<Product>{
//        var productList: ArrayList<Product> = ArrayList()
//        var columns = arrayOf(
//            COL_ID,
//            COL_NAME,
//            COL_PRICE,
//            COL_IMAGE
//        )
//        var cursor = database.query(TABLE_NAME, columns, null, null, null, null, null)
//        if(cursor != null && cursor.moveToFirst()){
//            do{
//                var id = cursor.getString(cursor.getColumnIndex(COL_ID))
//                var name  = cursor.getString(cursor.getColumnIndex(COL_NAME))
//                var price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE))
//                var image = cursor.getString(cursor.getColumnIndex(COL_IMAGE))
//                var product = Product(null, id, name, null, price, image,
//                    image, price, name)
//                productList.add(product)
//            }while(cursor.moveToNext())
//        }
//        cursor.close()
//        return productList
//    }
//}