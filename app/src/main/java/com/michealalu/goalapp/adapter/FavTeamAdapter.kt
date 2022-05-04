package com.michealalu.goalapp.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.michealalu.goalapp.databinding.FavTeamListBinding
import com.michealalu.goalapp.model.FavTeam
import com.squareup.picasso.Picasso


class FavFavTeamAdapter(val context: Context, private val favTeamListener:FavouriteListener)
    : ListAdapter<FavTeam, FavFavTeamAdapter.FavTeamViewHolder>(ItemGroupDiffUtill()){

    var onItemClick: ((FavTeam) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamViewHolder {
        return FavTeamViewHolder(
            FavTeamListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: FavTeamViewHolder, position: Int) {
        val cardoption = getItem(position)
        holder.binding.teamName.text= cardoption.name
        holder.binding.shortNameTxt.text= cardoption.shortName
        holder.binding.foundedTxt.text= cardoption.founded.toString()
        holder.binding.emailTxt.text= cardoption.email
        holder.binding.phoneTxt.text= cardoption.phone
        holder.binding.addrTxt.text= cardoption.address
        holder.binding.weTxt.text= cardoption.website


        if(!cardoption.crestUrl.isNullOrEmpty()){
            holder.loadLogo(cardoption.crestUrl!!)
            Log.i("leagueLogoUrl", cardoption.crestUrl!!)
        }

        holder.binding.favCheck.setOnClickListener {
            val checked: Boolean = holder.binding.favCheck.isChecked
            if(checked){
                cardoption.favourite=checked
                //insert team
                favTeamListener.onSelectFavTeam(cardoption)
            }else{
                cardoption.favourite=checked
                //delete team
                favTeamListener.onUnSelectFavTeam(cardoption,position)
            }
        }


    }
    interface FavouriteListener{
        fun onSelectFavTeam(team:FavTeam)
        fun onUnSelectFavTeam(team:FavTeam,pos:Int)
    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<FavTeam>(){
        override fun areItemsTheSame(oldItem: FavTeam, newItem: FavTeam): Boolean {
            return oldItem.id===newItem.id
        }

        override fun areContentsTheSame(oldItem: FavTeam, newItem: FavTeam): Boolean {
            return oldItem == newItem
        }

    }


    inner class FavTeamViewHolder( val binding: FavTeamListBinding) : RecyclerView.ViewHolder(binding.root) {

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
                    Picasso.get()
                        .load(url)
                        .resize(65,65)
                        .centerCrop()
                        .into(teamLogo)
                }


            }
        }

    }
}