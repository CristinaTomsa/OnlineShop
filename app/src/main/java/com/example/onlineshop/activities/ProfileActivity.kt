package com.example.onlineshop.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityProfileBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_dashbord.*

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.buttonGoShopping.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.buttonLogoutProfile.setOnClickListener {
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
    }
}