package com.michealalu.goalapp.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.michealalu.goalapp.R
import com.michealalu.goalapp.databinding.StandingListBinding
import com.michealalu.goalapp.databinding.TeamsListBinding
import com.michealalu.goalapp.model.team.Team


class TeamAdapter(val context: Context, private val favTeamListener:FavouriteTeamListener)
    : ListAdapter<Team, TeamAdapter.TeamViewHolder>(ItemGroupDiffUtill()){

    var onItemClick: ((Team) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            TeamsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val cardoption = getItem(position)
        holder.binding.teamName.text= cardoption.name

        if(!cardoption.crestUrl.isNullOrEmpty()){
            holder.loadLogo(cardoption.crestUrl!!)
            Log.i("leagueLogoUrl", cardoption.crestUrl!!)
        }

        holder.binding.favCheck.setOnClickListener {
            val checked: Boolean = holder.binding.favCheck.isChecked
            if(checked){
                cardoption.favourite=checked
                //insert team
                favTeamListener.onSelectTeam(cardoption)
            }else{
                cardoption.favourite=checked
                //delete team
                favTeamListener.onUnSelectTeam(cardoption)
            }
        }


    }
    interface FavouriteTeamListener{
        fun onSelectTeam(team:Team)
        fun onUnSelectTeam(team:Team)
    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Team>(){
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id===newItem.id
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }

    }


    inner class TeamViewHolder( val binding: TeamsListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))

            }
        }

        fun loadLogo(url: String) {
            with(binding) {
                if(url.endsWith("svg")) {
                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url), teamLogo)
                }else{
                    Glide.with(context).load(url).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(teamLogo)
                }


            }
        }

    }
}