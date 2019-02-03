package com.billingapp.ui.main.purchases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.Purchase
import com.billingapp.R
import kotlinx.android.synthetic.main.item_purchase.view.*
import java.util.*

class ProductPurchasedAdapter(val list: List<Purchase>, val listener: (pos: Int, data: Purchase) -> Unit) : RecyclerView.Adapter<ProductPurchasedAdapter.ProductListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val view = holder.itemView
        view.textProductName.text = list[position].originalJson
        view.textProductPrice.text = Date(list[position].purchaseTime).toString()
        view.btnConsume.setOnClickListener { listener(position, list[position]) }
    }


    class ProductListViewHolder(view: View, listener: (pos: Int, data: Purchase) -> Unit) : RecyclerView.ViewHolder(view)
}