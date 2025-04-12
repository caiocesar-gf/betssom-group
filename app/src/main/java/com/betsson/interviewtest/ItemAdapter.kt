package com.betsson.interviewtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private var bets: List<Bet>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    fun updateList(newList: List<Bet>) {
        bets = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = bets.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(bets[position])
    }

    inner class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bet: Bet) {
            binding.oddName.text = bet.type
            binding.oddSellIn.text = "Sell in: ${bet.sellIn}"
            binding.oddValue.text = "Odds: ${bet.odds}"
            Glide.with(binding.oddImage.context).load(bet.image).into(binding.oddImage)
        }
    }
}