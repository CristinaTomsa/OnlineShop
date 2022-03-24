package com.example.onlineshop.viewholder

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.activities.ProductDetailActivity
import com.example.onlineshop.data.Product
import com.example.onlineshop.data.ProductInfo
import com.example.onlineshop.databinding.ViewHolderProductsBinding
import com.example.onlineshop.sql.DBHelper
import com.example.onlineshop.sql.ProductDao
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_products.view.*

class ProductsViewHolder(val binding: ViewHolderProductsBinding): RecyclerView.ViewHolder(binding.root) {

    fun setData(product: Product){
        binding.tvProductName.text =product.product_name
        binding.tvProductPrice.text = product.price.toString()
        binding.tvDescription.text = product.description

        val url = "${Constants.IMAGE_URL}${product.product_image_url}"
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.noimage).into(binding.ivProductPhoto)

        var productDao = ProductDao(binding.root.context)

       // binding.btnAddToCard.setOnClickListener{



//        if (product.qty == 0) {
//            binding.ivAdd.visibility = View.GONE
//            binding.ivMinus.visibility = View.GONE
//            binding.tvQtyu.visibility = View.GONE
//
//        }else{
//            binding.btnAddToCard.visibility = View.GONE
//            binding.ivAdd.visibility = View.VISIBLE
//            binding.ivMinus.visibility = View.VISIBLE
//            binding.tvQtyu.visibility = View.VISIBLE
//        }

//        itemView.setOnClickListener {
//            var intent = Intent(binding.root.context, ProductDetailActivity::class.java)
//            intent.putExtra(Product.KEY_PRODUCT, product)
//            binding.root.context.startActivity(intent)
//        }




        binding.btnAddToCard.setOnClickListener {
            product.qty = 1
            productDao.addProduct(product)
            itemView.tv_qtyu.text = product.qty.toString()

            binding.btnAddToCard.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE

        }


        binding.ivAdd.setOnClickListener {
            product.qty++
            productDao.updateProduct(product, product.qty)
            itemView.tv_qtyu.text = product.qty.toString()

            Log.i("product", product.qty.toString())

        }

        binding.ivMinus.setOnClickListener {
            product.qty--
            productDao.updateProduct(product, product.qty)
            binding.tvQtyu.text = product.qty.toString()
        }



//        if(product.qty == 1){
//            binding.ivMinus.visibility = View.GONE
//            binding.tvQtyu.visibility = View.GONE
//            binding.ivAdd.visibility = View.GONE
//        } else{
//            binding.btnAddToCard.visibility = View.GONE
//            binding.ivMinus.visibility = View.VISIBLE
//            binding.tvQtyu.visibility = View.VISIBLE
//            binding.ivAdd.visibility = View.VISIBLE
//        }


    }
}