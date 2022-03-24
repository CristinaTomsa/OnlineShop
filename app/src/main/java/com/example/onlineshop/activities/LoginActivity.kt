package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.activities.Constants.PREF_NAME
import com.example.onlineshop.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        setupEvents()

        binding.textViewNotRegistered.setOnClickListener {
            startActivity(Intent(baseContext, RegisterActivity::class.java))
        }

        val pref = getSharedPreferences("login_details", AppCompatActivity.MODE_PRIVATE)
        if (pref.contains("email")){
            startActivity(Intent(baseContext, DashboardActivity::class.java))
        }
    }


    private fun setupEvents() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val url = "${Constants.BASE_URL}User/auth"
        val data = JSONObject()

        val email_id = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        data.put("email_id", email_id)
        data.put("password", password)

        val requestMethod = Request.Method.POST
        val request = JsonObjectRequest(requestMethod,
            url,
            data,
            { response ->
                val status = response.getInt("status")
                val message = response.getString("message")
                if (status == 0) {
                    val user: JSONObject = response.getJSONObject("user")
                    val full_name = user.getString("full_name")
                    val mobile_no= user.getString("mobile_no")
                    val email_id = user.getString("email_id")
                    val userId = user.getString("user_id")


                    val pref = getSharedPreferences(PREF_NAME, AppCompatActivity.MODE_PRIVATE)
                    val editor = pref.edit()

                    editor.putString("name", full_name)
                    editor.putString("mobile", mobile_no)
                    editor.putString("email", email_id)
                    editor.putString("user_id", userId)
                    editor.apply()


                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, DashboardActivity::class.java))
                    finish()

                } else {
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()

                }
            },
            { error ->
                Toast.makeText(
                    baseContext,
                    "Failed to login. Please check your email id or password.",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
        queue.add(request)
    }
}

