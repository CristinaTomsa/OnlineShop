package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityRegisterBinding
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var queue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        setupEvents()


        binding.textViewAlreadyRegistered.setOnClickListener{
            startActivity(Intent(baseContext, LoginActivity::class.java))
            finish()
        }
    }

    private fun setupEvents() {
        binding.btnRegister.setOnClickListener{
            addUser()
        }
    }

    private fun addUser() {
        val url = "${Constants.BASE_URL}User/register"
        val data = JSONObject()

        val full_name = binding.etFullName.text.toString()
        val mobile_no = binding.etPhoneNo.text.toString().toInt()
        val email_id = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        data.put("full_name", full_name)
        data.put("mobile_no", mobile_no)
        data.put("email_id", email_id)
        data.put("password", password)

        val requestMethod = Request.Method.POST
        val request = JsonObjectRequest(requestMethod,
            url,
            data,
            {
                    response ->
                val status = response.getInt("status")
                val message = response.getString("message")

                if(status == 0){
                    Toast.makeText(baseContext,message, Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                }
            },
            {
                    error ->
                Toast.makeText(baseContext, "User with email id $email_id already registered", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)



    }


}