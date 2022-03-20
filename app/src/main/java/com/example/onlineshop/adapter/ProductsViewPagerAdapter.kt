package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.databinding.ViewHolderSubcategoryProductsBinding
import com.example.onlineshop.viewholder.SubcategoryProductsViewHolder

class ProductsViewPagerAdapter(val subcategory: List<Subcategory>): RecyclerView.Adapter<SubcategoryProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubcategoryProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSubcategoryProductsBinding.inflate(layoutInflater,parent, false)
        return SubcategoryProductsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SubcategoryProductsViewHolder, position: Int) {
        holder.bind(subcategory[position])
    }

    override fun getItemCount(): Int {
        return subcategory.size
    }
}