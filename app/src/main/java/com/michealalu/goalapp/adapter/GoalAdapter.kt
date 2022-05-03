package com.michealalu.goalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.michealalu.goalapp.R
import com.michealalu.goalapp.databinding.GoalListBinding
import com.michealalu.goalapp.model.match.Goal


class GoalAdapter(val context: Context)
    : ListAdapter<Goal, GoalAdapter.GoalViewHolder>(GoalAdapter.ItemGroupDiffUtill()){

    var onItemClick: ((Goal) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(
            GoalListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val cardoption = getItem(position)
        holder.binding.scorerNameTxt.text= cardoption.scorer?.name
        holder.binding.assistNameTxt.text=cardoption.assist?.name
        holder.binding.teamName.text=cardoption.team?.name
        holder.binding.minTxt.text=cardoption.minute.toString()


    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Goal>(){
        override fun areItemsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem.team?.id===newItem.team?.id
        }

        override fun areContentsTheSame(oldItem: Goal, newItem: Goal): Boolean {
            return oldItem == newItem
        }

    }


    inner class GoalViewHolder( val binding: GoalListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))

            }
        }

    }
}