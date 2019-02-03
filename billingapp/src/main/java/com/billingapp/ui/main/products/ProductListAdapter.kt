package com.billingapp.ui.main.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.billingapp.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductListAdapter(val list: MutableList<SkuDetails>, val listener: (pos: Int, data: SkuDetails) -> Unit) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val view = holder.itemView
        view.textProductName.text = list[position].title
        view.textProductPrice.text = list[position].price
        view.btnPurchase.setOnClickListener { listener(position, list[position]) }
    }


    class ProductListViewHolder(view: View, listener: (pos: Int, data: SkuDetails) -> Unit) : RecyclerView.ViewHolder(view)
}