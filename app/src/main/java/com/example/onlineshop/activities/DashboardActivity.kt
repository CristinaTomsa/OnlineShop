package com.example.onlineshop.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.adapter.CategoryAdapter
import com.example.onlineshop.data.Category
import com.example.onlineshop.data.CategoryResponse
import com.example.onlineshop.data.SearchProductResponse
import com.example.onlineshop.databinding.ActivityDashbordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_dashbord.*
import java.lang.Exception
import java.net.URLEncoder

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbordBinding
    lateinit var queue: RequestQueue
    lateinit var adapter: CategoryAdapter
    lateinit var categories: List<Category>
    lateinit var headerView: View
    lateinit var username: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)


        binding.rvCategories.layoutManager = GridLayoutManager(baseContext,2)
        loadCategories()

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        headerView = binding.navView.getHeaderView(0)

        username = headerView.findViewById(R.id.nav_header_name)

        binding.navView.setNavigationItemSelectedListener {
            handleNavigationOperation(it)
            true
        }
        binding.ivSearchProduct.setOnClickListener {
            startActivity(Intent(baseContext, SearchProductActivity::class.java))
        }



    }

    private fun handleNavigationOperation(menuItem: MenuItem) {

        var intent = Intent(this, LoginActivity::class.java)

        when(menuItem.itemId){
            R.id.nav_logout -> {
                var snackbar = Snackbar.make(drawer_layout, "Leaving Already?", Snackbar.LENGTH_LONG)
                    .setAction("Yes") {
                        val pref = getSharedPreferences("login_details", MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = pref.edit()
                        editor.clear()
                        editor.commit()
                        startActivity(intent)
                    }
                snackbar.show()
            }
            R.id.nav_my_cart ->{
                startActivity(Intent(baseContext, CartActivity::class.java))
            }

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }else{
                binding.drawerLayout.openDrawer(GravityCompat.START)
             }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadCategories() {
        val url = "${Constants.BASE_URL}Category"
        val request = StringRequest(
            Request.Method.GET,
            url, { apiResponse: String ->
                val typeToken = object : TypeToken<CategoryResponse>() {

                }
                val gson: Gson = Gson()
                try {

                    val response: CategoryResponse = gson.fromJson(apiResponse, typeToken.type)
                    if (response.status == 0) {
                        categories = response.categories
                        adapter = CategoryAdapter(categories)

                        adapter.setOnOptionSelectedListener { category, i ->
                            val intent = Intent(baseContext, SubcategoryActivity::class.java)
                            intent.putExtra(Category.KEY_CATEGORY, category.category_id)
                            startActivity(intent)
                            finish()

                        }
                        binding.rvCategories.adapter = adapter
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Failed to load categories. Please retry.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(baseContext, "Error is : ${e.toString()}", Toast.LENGTH_LONG)
                        .show()
                }
            }, { error ->
                error.printStackTrace()
                Toast.makeText(baseContext, "Error is : $error", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }

}