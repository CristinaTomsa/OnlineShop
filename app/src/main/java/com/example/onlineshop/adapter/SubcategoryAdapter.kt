package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.data.Category
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.databinding.ViewHolderSubcatecoryBinding
import com.example.onlineshop.viewholder.SubcategoryViewHolder

class SubcategoryAdapter(val subcategory: List<Subcategory>): RecyclerView.Adapter<SubcategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSubcatecoryBinding.inflate(layoutInflater, parent, false)
        return SubcategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val subcategory = subcategory[position]
        holder.bind(subcategory)

        holder.itemView.setOnClickListener {
            if(this::optionSelectedListener.isInitialized) {
                optionSelectedListener(subcategory, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return subcategory.size
    }

    private lateinit var optionSelectedListener: (Subcategory, Int) -> Unit

    fun setOnOptionSelectedListener(listener: (Subcategory, Int) -> Unit) {
        this.optionSelectedListener = listener
    }
}