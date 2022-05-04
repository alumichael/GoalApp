package com.michealalu.goalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.michealalu.goalapp.R
import com.michealalu.goalapp.databinding.RefereeListBinding
import com.michealalu.goalapp.model.match.Referee


class RefereeAdapter(val context: Context)
    : ListAdapter<Referee, RefereeAdapter.RefereeViewHolder>(RefereeAdapter.ItemGroupDiffUtill()){

    var onItemClick: ((Referee) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefereeViewHolder {
        return RefereeViewHolder(
            RefereeListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: RefereeViewHolder, position: Int) {
        val cardoption = getItem(position)
        val initialA=cardoption.name?.split(' ')?.get(0)?.first().toString()
        val initialB=cardoption.name?.split(' ')?.get(1)?.first().toString()
        holder.binding.nameIcon.text= initialA.plus(initialB)
        holder.binding.teamName.text=cardoption.name
        holder.binding.nationalityTxt.text=cardoption.nationality


    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Referee>(){
        override fun areItemsTheSame(oldItem: Referee, newItem: Referee): Boolean {
            return oldItem.id===newItem.id
        }

        override fun areContentsTheSame(oldItem: Referee, newItem: Referee): Boolean {
            return oldItem == newItem
        }

    }


    inner class RefereeViewHolder( val binding: RefereeListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))

            }
        }

    }
}