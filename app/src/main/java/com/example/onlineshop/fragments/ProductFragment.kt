package com.example.onlineshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.adapter.ProductAdapter
import com.example.onlineshop.data.Category
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.ProductResponse
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.databinding.FragmentProductBinding
import com.google.gson.Gson

class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    lateinit var queue: RequestQueue
    lateinit var adapter: ProductAdapter
    lateinit var products: List<Product>
    var subcategoryId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root

        arguments?.let{
            subcategoryId = it.getString(Subcategory.KEY_SUB_CATEGORY).toString()
        }

        binding.rvProducts.layoutManager = LinearLayoutManager(context)



        //SubCategory/products/{sub_category_id}

       // subcategoryId = intent.getStringExtra(Category.KEY_CATEGORY).toString()

        //subcategoryId = intent

        loadProducts()
    }

    private fun loadProducts() {

        val url = "${Constants.BASE_URL}SubCategory/products/$subcategoryId"
        Log.i("url", url)

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
                Toast.makeText(context, "Error is: $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }
    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categoryId: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(Subcategory.KEY_SUB_CATEGORY, categoryId)

                }
            }
    }

}