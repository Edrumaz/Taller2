package com.naldana.ejemplo10.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naldana.ejemplo10.R
import com.naldana.ejemplo10.models.Coin
import kotlinx.android.synthetic.main.simple_coin_cardview.view.*

class SimpleCoinAdapter (val items: List<Coin>, val clickListener: (Coin) -> Unit):RecyclerView.Adapter<SimpleCoinAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_coin_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Coin, clickListener: (Coin) -> Unit) = with(itemView) {
            simple_coin_symbol.text = item.symbol
            symple_coin_available.text = item.available
            this.setOnClickListener { clickListener(item) }
        }
    }
}