package com.example.onlineshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.onlineshop.R
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.ProductResponse
import com.example.onlineshop.data.Subcategory.Companion.KEY_SUB_CATEGORY
import com.example.onlineshop.databinding.ActivityProductBinding
import com.google.gson.Gson

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductBinding
    lateinit var queue: RequestQueue
    lateinit var adapter: ProductAdapter
    lateinit var products: List<Product>
    var subcategoryId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subcategoryId = intent.getStringExtra(KEY_SUB_CATEGORY).toString()
        binding.rvProducts.layoutManager = LinearLayoutManager(baseContext)
        loadProducts()
    }

    private fun loadProducts() {

        val url = "${Constants.BASE_URL}SubCategory/products/$subcategoryId"
        Log.i("TAG", url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductResponse::class.java)
                if (response.status == 0){
                    products = response.products
                    adapter = ProductAdapter(products)


                    binding.rvProducts.adapter = adapter
                }
            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is: $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
}