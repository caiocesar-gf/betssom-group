package com.betsson.interviewtest.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betsson.interviewtest.data.model.Bet
import com.betsson.interviewtest.databinding.ListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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

            binding.imageProgress.visibility = View.VISIBLE

            Glide.with(binding.oddImage.context)
                .load(bet.image)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.imageProgress.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.imageProgress.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.oddImage)
        }
    }
}