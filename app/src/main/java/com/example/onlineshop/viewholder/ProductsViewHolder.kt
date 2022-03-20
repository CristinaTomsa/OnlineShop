package com.example.onlineshop.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.data.Product
import com.example.onlineshop.databinding.ViewHolderProductsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_products.view.*

class ProductsViewHolder(val binding: ViewHolderProductsBinding): RecyclerView.ViewHolder(binding.root) {

    fun setData(product: Product){
        binding.tvProductName.text =product.product_name
        binding.tvProductPrice.text = product.price.toString()
        binding.tvDescription.text = product.description

        val url = "${Constants.IMAGE_URL}${product.product_image_url}"
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.noimage).into(binding.ivProductPhoto)


        //????????????????
//        if(product.price == 0.0){
//            binding.buttonDecrease.visibility = View.GONE
//            binding.tvQuantity.visibility = View.GONE
//            binding.buttonIncrease.visibility = View.GONE
//        } else{
//            binding.buttonAdd.visibility = View.GONE
//            binding.buttonDecrease.visibility = View.VISIBLE
//            binding.tvQuantity.visibility = View.VISIBLE
//            binding.buttonIncrease.visibility = View.VISIBLE
//        }

//        itemView.setOnClickListener {
//
//        }

    }
}