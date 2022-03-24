package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.activities.Constants
import com.example.onlineshop.data.Product
import com.example.onlineshop.sql.ProductDao
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_cart.view.*

class CartAdapter(var mContext: Context, var productList: MutableList<Product>) :
    RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    var dao = ProductDao(mContext)
    private var listener: OnAdapterInteraction? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.view_holder_cart, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var product: Product = productList[position]
        holder.bind(product, position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, position: Int) {
            itemView.text_view_cart_name.text = product.product_name
            itemView.text_view_cart_price.setText("$" + product.price.toString())

            Picasso.get()
                .load(Constants.IMAGE_URL + product.product_image_url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimage)
                .into(itemView.image_product_cart)

            itemView.tv_qtyu.text = product.qty.toString()


            itemView.button_delete.setOnClickListener {
                listener?.onClickedItemListener(it, position, product.product_id)
            }

            itemView.iv_minus.setOnClickListener {
                listener?.onClickedItemListener(it, position, product.product_id)

            }

            itemView.iv_add.setOnClickListener {
                listener?.onClickedItemListener(it, position, product.product_id)
            }
        }
    }

    interface OnAdapterInteraction {
        fun onClickedItemListener(view: View, position: Int, id: Long)
    }

    fun setAdapterListener(onAdapterInteraction: OnAdapterInteraction) {
        listener = onAdapterInteraction
    }

    fun setData(list: MutableList<Product>) {
        productList = list
        notifyDataSetChanged()
    }
}
