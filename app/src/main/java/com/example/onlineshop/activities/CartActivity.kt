package com.example.onlineshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.R
import com.example.onlineshop.adapter.CartAdapter
import com.example.onlineshop.data.Product
import com.example.onlineshop.databinding.ActivityCartBinding
import com.example.onlineshop.sql.ProductDao
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.view_holder_cart.*

class CartActivity : AppCompatActivity(), CartAdapter.OnAdapterInteraction {

    lateinit var binding: ActivityCartBinding
    lateinit var products: MutableList<Product>
    lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            startActivity(Intent(baseContext, DashboardActivity::class.java))
        }

        val productDao = ProductDao(baseContext)
        products = productDao.getProducts()


        binding.layoutEmptyCart.visibility = View.GONE

        adapter = CartAdapter(this, products)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setAdapterListener(this)

        calculateTotals()

        if(products.size == 0){
            binding.layoutFullCart.visibility = View.GONE
            binding.layoutEmptyCart.visibility = View.VISIBLE
            binding.buttonGoShopping.setOnClickListener {
                startActivity(Intent(this, DashboardActivity::class.java))
            }
        }

        binding.buttonAddress.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }


    }

    private fun calculateTotals() {
        var subTotal: Double = 0.0
        var delivery: Double = 0.0
        var orderAmount: Double = 0.0

        for (product: Product in products){
            orderAmount += (product.price * product.qty)
        }

        delivery = if(orderAmount > 150){
            0.00
        }else{
            25.0
        }

        orderAmount+= delivery

        binding.textViewDeliveryAmount.text = "$ ${delivery.toString()}"
        binding.textViewTotalAmount.text = "$ ${orderAmount.toString()}"

    }

    private fun isCartEmpty(list: List<Product>):Boolean{
        var isEmpty = false
        if(products.size == 0){
            layout_full_cart.visibility = View. GONE
            layout_empty_cart.visibility = View.VISIBLE
            button_go_shopping.setOnClickListener {
                startActivity(Intent(this, DashboardActivity::class.java ))
            }
            isEmpty = true
        }else{
            //
        }
        return isEmpty
    }

    override fun onClickedItemListener(view: View, position: Int, id: Long) {
        var dao = ProductDao(baseContext)
        var product: Product = products[position]

        when(view.id){
            R.id.button_delete ->{
                dao.deleteProduct(id)
                products.removeAt(position)
                adapter.notifyDataSetChanged()
            }

            R.id.iv_minus ->{
                if(product.qty > 1){
                    product.qty--
                    tv_qtyu.text = product.qty.toString()
                    dao.updateProduct(product, product.qty)
                    adapter.notifyDataSetChanged()
                }else{
                    dao.deleteProduct(product.product_id)
                    products.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
            }
            R.id.iv_add ->{
                product.qty++
                tv_qtyu.text = product.qty.toString()
                adapter.notifyDataSetChanged()
            }
        }


    }
}


