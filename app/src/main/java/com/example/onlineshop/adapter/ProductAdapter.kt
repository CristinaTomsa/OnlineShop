package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.data.Category
import com.example.onlineshop.data.Product
import com.example.onlineshop.databinding.ViewHolderProductsBinding
import com.example.onlineshop.viewholder.ProductsViewHolder

class ProductAdapter(var products: List<Product>): RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderProductsBinding.inflate(layoutInflater)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = products[position]
        holder.setData(products)

        holder.itemView.setOnClickListener {
            if(this::optionSelectedListener.isInitialized) {
                optionSelectedListener(products, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }



    private lateinit var optionSelectedListener: (Product, Int) -> Unit

    fun setOnOptionSelectedListener(listener: (Product, Int) -> Unit) {
        this.optionSelectedListener = listener
    }
}