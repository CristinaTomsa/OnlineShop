package com.example.onlineshop.viewholder

import android.provider.SyncStateContract
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.data.Subcategory
import com.example.onlineshop.databinding.ViewHolderSubcatecoryBinding
import com.squareup.picasso.Picasso

class SubcategoryViewHolder(val binding: ViewHolderSubcatecoryBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(subcategory: Subcategory){
        binding.tvSubcategory.text = subcategory.subcategory_name

//        val url = "${Constants.IMAGE_URL}${subcategory.subcategory_image_url}"
//        Picasso.get().load(url).into(binding.ivSubcategoryPhoto)

        /*val url = "${Constants.IMAGE_URL}${category.category_image_url}"
        Picasso.get().load(url).into(binding.ivPhoto)*/
    }
}