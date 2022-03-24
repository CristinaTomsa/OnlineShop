package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.onlineshop.R
import com.example.onlineshop.data.Addresse
import com.example.onlineshop.databinding.ActivityPaymentBinding


class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    lateinit var address: Addresse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }

        init()

        var i = intent
        address = i.getSerializableExtra(Addresse.KEY_ADDRESS) as Addresse


        binding.buttonPayment.setOnClickListener {
            var intent = Intent(this, OrderConfirmationActivity::class.java)
            intent.putExtra(Addresse.KEY_ADDRESS, address)
            startActivity(intent)
        }

    }

    private fun init(){




        binding.checkboxCash.setOnClickListener {
            if (binding.checkboxCash.isChecked){
                binding.checkboxInternalBacking.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
                binding.checkboxPaypal.isChecked = false
            }
        }

        binding.checkboxInternalBacking.setOnClickListener {
            if (binding.checkboxInternalBacking.isChecked){
                binding.checkboxCash.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
                binding.checkboxPaypal.isChecked = false
            }
        }

        binding.checkboxDebitCredit.setOnClickListener {
            if (binding.checkboxDebitCredit.isChecked){
                binding.checkboxCash.isChecked = false
                binding.checkboxPaypal.isChecked = false
                binding.checkboxInternalBacking.isChecked = false
            }
        }

        binding.checkboxPaypal.setOnClickListener {
            if (binding.checkboxPaypal.isChecked){
                binding.checkboxCash.isChecked = false
                binding.checkboxInternalBacking.isChecked = false
                binding.checkboxDebitCredit.isChecked = false
            }
        }


    }
}