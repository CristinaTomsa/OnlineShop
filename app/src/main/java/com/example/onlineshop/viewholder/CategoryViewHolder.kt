package com.example.onlineshop.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.data.Category
import com.example.onlineshop.databinding.ViewHolderCategoryBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder  (val binding: ViewHolderCategoryBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(category: Category){
        binding.tvCategory.text = category.category_name

        val url = "${Constants.IMAGE_URL}${category.category_image_url}"
        Picasso.get().load(url).placeholder(R.drawable.loading).error(R.drawable.noimage).into(binding.ivPhoto)
    }
}