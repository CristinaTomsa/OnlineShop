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
import com.example.onlineshop.data.SearchProductResponse
import com.example.onlineshop.databinding.ActivitySearchProductBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashbord.*
import java.net.URLEncoder

class SearchProductActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchProductBinding
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchProductBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.ivSearch.setOnClickListener {
            searchProduct()
        }
    }

    private fun searchProduct() {
        val searchText =URLEncoder.encode(binding.edtSearchProduct.text.toString())
//Product/search?query={search_text}
        val url = "${Constants.BASE_URL}Product/search?query=$searchText"
        Log.i("search url", url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val searchResponse = gson.fromJson(it, SearchProductResponse::class.java)

                if (searchResponse.status == 0){

                    binding.tvProductName.text = searchResponse.product.product_name.toString()
                    binding.tvProductPrice.text = searchResponse.product.price.toString()
                    binding.tvDescription.text = searchResponse.product.description.toString()

                }else{
                    Toast.makeText(baseContext, "Something went wrong. Please retry.", Toast.LENGTH_LONG).show()
                }
            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
}