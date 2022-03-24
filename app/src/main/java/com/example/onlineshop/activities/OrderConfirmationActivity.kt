package com.example.onlineshop.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.data.Addresse
import com.example.onlineshop.data.Product
import com.example.onlineshop.databinding.ActivityOrderConfirmationBinding
import com.example.onlineshop.sql.ProductDao
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_dashbord.*
import org.json.JSONObject

class OrderConfirmationActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderConfirmationBinding
    lateinit var dao: ProductDao
    lateinit var address: Addresse
    lateinit var list: List<Product>
    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)

        binding.buttonGoHome.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            dao.deleteTable()

        }

        binding.buttonLogout.setOnClickListener {
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

       // placeOrder()
    }

//    private fun placeOrder() {
//        var userId = 42
//
//        list = dao.getProducts()
//
//        val url = "${Constants.BASE_URL} /Order"
//
//        var product:ArrayList<Any> = ArrayList()
//        var oneProduct= HashMap<String, Any>()
//
//
//        //map prouctList into jsonObject
//        for (item in list) {
//            oneProduct["price"] = item.price.toString()
//            oneProduct["quantity"] = item.qty
//            oneProduct["productName"] = item.product_name.toString()
//            val jsonObjectProduct = JSONObject(oneProduct as Map<*, *>)
//            product.add(jsonObjectProduct)
//        }
//
//        //map address into jsonObject
//        var shipAddress = HashMap<String, Any?>()
//        shipAddress["title"] = address.title
//        shipAddress["address"] = address.address
//        val jsonAddress = JSONObject(shipAddress as Map<*, *>)
//
//        //map all the info into jsonObject
//        var params = HashMap<String, Any>()
//        params["userId"] = userId
//        params["products"] = product
//        params["shippingAddress"] = jsonAddress
//
//        val jsonObject = JSONObject(params as Map<*, *>)
//        var requestQueue = Volley.newRequestQueue(this)
//        var request = JsonObjectRequest(
//            Request.Method.POST, url,
//            jsonObject,
//            {
//                    response ->
//                val status = response.getInt("status")
//                val message = response.getString("message")
//
//                if(status == 0){
//                    Toast.makeText(baseContext,message, Toast.LENGTH_LONG).show()
//
//                }else{
//                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
//                }
//
//            },
//            {
//                it.printStackTrace()
//                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
//            }
//        )
//
//        requestQueue.add(request)
//
//
//    }
}