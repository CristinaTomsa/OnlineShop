package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.adapter.ProductsViewPagerAdapter
import com.example.onlineshop.data.Category.Companion.KEY_CATEGORY
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.data.SubcategoryResponse
import com.example.onlineshop.databinding.ActivitySubcategoryBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class SubcategoryActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    lateinit var binding: ActivitySubcategoryBinding
    //lateinit var adapter: SubcategoryAdapter
    lateinit var adapter: ProductsViewPagerAdapter
    //lateinit var adapter: FragmentAdapter
    lateinit var subcategories: List<Subcategory>
    var categoryId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)

        //binding.rvSubcategory.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
//        adapter = FragmentAdapter(supportFragmentManager)
//        adapter.notifyDataSetChanged()

        categoryId = intent.getStringExtra(KEY_CATEGORY).toString()
        //categoryId = intent.extras?.getString(KEY_CATEGORY)?.toInt()!!

        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, DashboardActivity::class.java))
        }

        loadSubcategories()

    }

    private fun loadSubcategories() {

        val url = "${Constants.BASE_URL}SubCategory?category_id=$categoryId"
        Log.i("url", url)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, SubcategoryResponse::class.java)
                if (response.status == 0){

                    subcategories = response.subcategories
                    adapter = ProductsViewPagerAdapter(subcategories)


                    binding.viewPager.adapter = adapter


                    val mediator = TabLayoutMediator(binding.tabs, binding.viewPager){
                        tab, position ->
                        tab.text = subcategories[position].subcategory_name
                    }
                    mediator.attach()
                    //adapter = SubcategoryAdapter(subcategories)

//                    adapter.setOnOptionSelectedListener { category, i ->
//                        val intent = Intent(baseContext, ProductActivity::class.java)
//                        intent.putExtra(Subcategory.KEY_SUB_CATEGORY, category.subcategory_id)
//                        startActivity(intent)
//                        finish()
//
//                    }
                    //binding.rvSubcategory.adapter = adapter
                }else{
                    Toast.makeText(baseContext, response.message, Toast.LENGTH_LONG).show()
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