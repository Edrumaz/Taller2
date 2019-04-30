package com.naldana.ejemplo10.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naldana.ejemplo10.R
import kotlinx.android.synthetic.main.coin_cardview.view.*
import com.naldana.ejemplo10.models.Coin

class CoinAdapter(val items: List<Coin>, val clickListener: (Coin) -> Unit):RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], clickListener)


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Coin, clickListener: (Coin) -> Unit) = with(itemView) {
            tv_coin_id.text = item.id.toString()
            tv_coin_name.text = item.name
            this.setOnClickListener { clickListener(item) }
        }
    }
}