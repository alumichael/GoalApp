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
import com.michealalu.goalapp.Constant.Companion.BASE_URL
import com.michealalu.goalapp.Constant.Companion.LOGOBASE_URL
import com.michealalu.goalapp.databinding.LiveListBinding
import com.michealalu.goalapp.model.match.Matche
import com.squareup.picasso.Picasso

class LiveCompetAdapter(val context: Context,val competitionName: String)
    : ListAdapter<Matche, LiveCompetAdapter.MatcheViewHolder>(LiveCompetAdapter.ItemGroupDiffUtill()){

    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    var selectedItemId = -1

    var onItemClick: ((Matche) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatcheViewHolder {
        return MatcheViewHolder(
            LiveListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: MatcheViewHolder, position: Int) {
        val cardoption = getItem(position)

        holder.binding.competitnName.text = competitionName
        cardoption.competitionName=competitionName
        holder.binding.homeTeamTxt.text = cardoption.homeTeam?.name
        holder.binding.awayTeamTxt.text = cardoption.awayTeam?.name

        holder.binding.score.text = cardoption.score?.fullTime?.homeTeam
            .toString().plus(" : ").plus(cardoption.score?.fullTime?.awayTeam
                .toString())


        holder.loadLogo(LOGOBASE_URL.plus(cardoption.homeTeam?.id).plus(".svg"),
            LOGOBASE_URL.plus(cardoption.awayTeam?.id).plus(".svg"))
        
        Log.i("LogoUrl",BASE_URL.plus(cardoption.awayTeam?.id).plus(".svg"))


    }


    class ItemGroupDiffUtill: DiffUtil.ItemCallback<Matche>(){
        override fun areItemsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem.id===newItem.id
        }

        override fun areContentsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem == newItem
        }

    }


    inner class MatcheViewHolder( val binding: LiveListBinding) : RecyclerView.ViewHolder(binding.root) {

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

        fun loadLogo(url_home: String,url_away:String) {

            with(binding){
                if(url_home.endsWith("svg")){
                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url_home), homeLogo)

                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url_away),awayLogo)
                }else{

                    Picasso.get()
                        .load(url_home)
                        .resize(40,40)
                        .centerCrop()
                        .into(homeLogo)

                    Picasso.get()
                        .load(url_away)
                        .resize(40,40)
                        .centerCrop()
                        .into(awayLogo)

                   /* Glide.with(context).load(url_home).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(homeLogo)

                    Glide.with(context).load(url_away).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(awayLogo)*/
                }
            }

            with(binding) {


            }
        }
        

    }
}