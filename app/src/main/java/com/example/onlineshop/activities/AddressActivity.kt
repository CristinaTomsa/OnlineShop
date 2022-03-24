package com.example.onlineshop.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants.PREF_NAME
import com.example.onlineshop.adapter.AddressAdapter
import com.example.onlineshop.data.AddressResponse
import com.example.onlineshop.data.Addresse
import com.example.onlineshop.databinding.ActivityAddressBinding
import com.google.gson.Gson
import java.util.stream.Stream

class AddressActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddressBinding
    private var addressList: List <Addresse> = ArrayList()
    lateinit var adapter: AddressAdapter
    var user_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        adapter = AddressAdapter(addressList)
        binding.rvAddress.layoutManager = LinearLayoutManager(baseContext)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, CartActivity::class.java))
        }


//        binding.rvAddress.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )

        binding.btnAddNewAddress.setOnClickListener{
            startActivity(Intent(this, AddAddressActivity::class.java))
        }
        adapter.notifyDataSetChanged()

        val pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        user_id = pref.getString("user_id", null).toString()



    }

    private fun getData() {

        val url = "${Constants.BASE_URL}User/addresses/42"
        Log.i("address url", url)

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                var gson = Gson()
                var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressList = addressResponse.addresses
                adapter = AddressAdapter(addressList)

                binding.rvAddress.adapter = adapter
            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        Volley.newRequestQueue(this).add(request)
    }
}