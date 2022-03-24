package com.example.onlineshop.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.onlineshop.R
import com.example.onlineshop.activities.PaymentActivity
import com.example.onlineshop.data.Addresse
import kotlinx.android.synthetic.main.activity_address.view.*
import kotlinx.android.synthetic.main.view_holder_address.view.*


class AddressAdapter(var addressList: List<Addresse>) : RecyclerView.Adapter<AddressAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_address, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var address: Addresse = addressList[position]
        holder.bind(address, position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(address: Addresse, position: Int){
            itemView.tv_title.text = address.title
            itemView.tv_address.text = address.address


            itemView.setOnClickListener {
                var intent = Intent(itemView.context, PaymentActivity::class.java)
                intent.putExtra(Addresse.KEY_ADDRESS, address)
                itemView.context.startActivity(intent)
            }
        }
    }
}