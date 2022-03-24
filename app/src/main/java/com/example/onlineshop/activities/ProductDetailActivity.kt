package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.Product.Companion.KEY_PRODUCT
import com.example.onlineshop.data.ProductDetailsResponse
import com.example.onlineshop.data.ProductResponse
import com.example.onlineshop.databinding.ActivityAddAddressBinding
import com.example.onlineshop.databinding.ActivityProductDetailBinding
import com.example.onlineshop.sql.ProductDao
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_products.view.*

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    lateinit var queue: RequestQueue
    lateinit var dao: ProductDao
    lateinit var product: Product
    var productId = 0
    var quantity: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        queue = Volley.newRequestQueue(baseContext)
        binding.ivBack.setOnClickListener {
            startActivity(Intent(this, SubcategoryActivity::class.java))
        }

        dao = ProductDao(baseContext)


        binding.btnAddToCard.setOnClickListener {
            product.qty = 1
            dao.addProduct(product)
            binding.tvQtyu.text = product.qty.toString()

            binding.btnAddToCard.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE

        }


        binding.ivAdd.setOnClickListener {
            product.qty++
            dao.updateProduct(product, product.qty)
            binding.tvQtyu.text = product.qty.toString()

            Log.i("product", product.qty.toString())

        }

        binding.ivMinus.setOnClickListener {
            product.qty--
            dao.updateProduct(product, product.qty)
            binding.tvQtyu.text = product.qty.toString()
        }

        /*Product/details/{product_id}*/
        getProductDetails()


        productId = intent.getLongExtra(KEY_PRODUCT, 0).toInt()
    }

    private fun getProductDetails() {

        val url = "${Constants.BASE_URL}Product/details/1"

        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                val gson = Gson()
                val response = gson.fromJson(it, ProductDetailsResponse::class.java)

                if (response.status == 0){
                    val product = response.product
                    binding.tvProductNameD.text = product.product_name
                    binding.tvProductDescriptionD.text = product.description
                    binding.priceD.text = "$${product.price}"


                    val imageUrl = "${Constants.IMAGE_URL}${product.product_image_url}"
                    Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.noimage).into(binding.ivPhotoD)


                }

            },
            {
                it.printStackTrace()
                Toast.makeText(baseContext, "Error is : $it", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(request)
    }

//    private fun addToCart() {
//        val id = product.product_id
//        val name = product.product_name
//        val qty = quantity
//        val price = product.price
//
//        val data = Product(null,0,null,null,price, id.toLong(),null,name,0,null, qty)
//        val productId = dao.addProduct(data)
//
//        if(productId > 0){
//            Toast.makeText(this,"Product added to cart",  Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(this,"Failed to add to cart. Try again.", Toast.LENGTH_LONG).show()
//        }
//    }


}