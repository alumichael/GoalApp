package com.michealalu.goalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.michealalu.goalapp.R
import com.michealalu.goalapp.databinding.StandingListBinding
import com.michealalu.goalapp.model.standings.Table


class StandingAdapter(val context: Context)
    : ListAdapter<Table, StandingAdapter.TableViewHolder>(StandingAdapter.ItemGroupDiffUtill()){

    var onItemClick: ((Table) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        return TableViewHolder(
            StandingListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val cardoption = getItem(position)
        holder.binding.positionTxt.text= cardoption.position.toString()
        holder.binding.teamName.text=cardoption.team?.name
        holder.binding.playTxt.text=cardoption.playedGames.toString()
        holder.binding.pointsTxt.text=cardoption.points.toString()
        holder.binding.diffTxt.text=cardoption.goalDifference.toString()

        if(cardoption.position!! >10){
            holder.binding.positionBg.setColorFilter(ContextCompat.getColor(context, R.color.red))

        }else{
            holder.binding.positionBg.setColorFilter(ContextCompat.getColor(context, R.color.green))

        }



    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Table>(){
        override fun areItemsTheSame(oldItem: Table, newItem: Table): Boolean {
            return oldItem.team?.id===newItem.team?.id
        }

        override fun areContentsTheSame(oldItem: Table, newItem: Table): Boolean {
            return oldItem == newItem
        }

    }


    inner class TableViewHolder( val binding: StandingListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))

            }
        }

    }
}