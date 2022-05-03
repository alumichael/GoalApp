package com.michealalu.goalapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.michealalu.goalapp.Constant
import com.michealalu.goalapp.R
import com.michealalu.goalapp.databinding.CompetitionListBinding
import com.michealalu.goalapp.model.competitions.Competition

class CompetitionAdapter(val context: Context)
    : ListAdapter<Competition, CompetitionAdapter.CompetitionViewHolder>(ItemGroupDiffUtill()){

    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    var selectedItemId = -1

    var onItemClick: ((Competition) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        return CompetitionViewHolder(
            CompetitionListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val cardoption = getItem(position)
        if(position == selectedItemPos)
            holder.selectedBg()

        else
            holder.defaultBg()

        holder.binding.chipTxt.text = cardoption.name

        if(!cardoption.emblemUrl.isNullOrEmpty()){
            holder.loadLogo(cardoption.emblemUrl!!)

            Log.i("leagueLogoUrl", cardoption.emblemUrl!!)
        }

    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Competition>(){
        override fun areItemsTheSame(oldItem: Competition, newItem: Competition): Boolean {
            return oldItem.id===newItem.id
        }

        override fun areContentsTheSame(oldItem: Competition, newItem: Competition): Boolean {
            return oldItem == newItem
        }

    }


    inner class CompetitionViewHolder( val binding: CompetitionListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
                selectedItemPos = bindingAdapterPosition
                lastItemSelectedPos = if(lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }

        fun loadLogo(url: String) {
            with(binding) {
                if(url.endsWith("svg")) {
                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url), chipIcon)
                }else{
                    Glide.with(context).load(url).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(chipIcon)
                }


            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun defaultBg() {
            binding.customChipLayout.background = context.getDrawable(R.drawable.unselect_competitn)
            binding.chipTxt.setTextColor(Color.parseColor("#212121"))


        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun selectedBg() {
            binding.customChipLayout.background = context.getDrawable(R.drawable.select_competitn)
            binding.chipTxt.setTextColor(Color.parseColor("#ffffff"))
        }

    }
}