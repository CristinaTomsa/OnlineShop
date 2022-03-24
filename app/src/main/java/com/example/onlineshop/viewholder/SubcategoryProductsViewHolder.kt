package com.example.onlineshop.viewholder

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.activities.ProductDetailActivity
import com.example.onlineshop.activities.SubcategoryActivity
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.data.Category
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.ProductResponse
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.databinding.ViewHolderSubcategoryProductsBinding
import com.google.gson.Gson

class SubcategoryProductsViewHolder(val binding: ViewHolderSubcategoryProductsBinding): RecyclerView.ViewHolder(binding.root){

    val queue = Volley.newRequestQueue(binding.root.context)
    fun bind(subcategory: Subcategory){

        val id = subcategory.subcategory_id

        val url = "${Constants.BASE_URL}SubCategory/products/$id"
        Log.i("TAG", url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductResponse::class.java)
                if (response.status == 0){

                    val adapter = ProductAdapter(response.products)
                    binding.rvProducts.layoutManager = LinearLayoutManager(binding.root.context)
//
                    adapter.setOnOptionSelectedListener { product, i ->
                        val intent = Intent(binding.root.context, ProductDetailActivity::class.java)
                        intent.putExtra(Product.KEY_PRODUCT, product.product_id)
                        binding.root.context.startActivity(intent)
 //
                    }

                    binding.rvProducts.adapter = adapter
                }
            },
            {
                it.printStackTrace()
                Toast.makeText(binding.root.context, "Error is: $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }

}