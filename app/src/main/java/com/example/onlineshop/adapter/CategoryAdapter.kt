package com.example.onlineshop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.data.Category
import com.example.onlineshop.databinding.ViewHolderCategoryBinding
import com.example.onlineshop.viewholder.CategoryViewHolder

class CategoryAdapter (val category: List<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = category[position]
        holder.bind(category)

        holder.itemView.setOnClickListener {
            if(this::optionSelectedListener.isInitialized) {
                optionSelectedListener(category, position)
            }
        }

    }

    override fun getItemCount() = category.size

    private lateinit var optionSelectedListener: (Category, Int) -> Unit

    fun setOnOptionSelectedListener(listener: (Category, Int) -> Unit) {
        this.optionSelectedListener = listener
    }
}