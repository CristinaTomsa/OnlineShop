package com.example.onlineshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshop.R
import com.example.onlineshop.data.OrderResponse

class OrdersAdapter(var mContext: Context, var orderList: List<OrderResponse>)
    : RecyclerView.Adapter<OrdersAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersAdapter.MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.view_holder_order, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var order = orderList[position]
        holder.bind(order)

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(order: OrderResponse){
            var orderSummary = order.order.bill_amount
            var shippingAddress = order.order.address



        }
    }

    fun setData(list: List<OrderResponse>){
        orderList = list
        notifyDataSetChanged()
    }

}