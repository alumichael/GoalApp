package com.michealalu.goalapp.adapter

import android.content.Context
import android.graphics.Color
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
import com.michealalu.goalapp.databinding.MatchListBinding
import com.michealalu.goalapp.model.match.Matche
import com.squareup.picasso.Picasso


class MatchAdapter(val context: Context, val competitionName: String)
    : ListAdapter<Matche, MatchAdapter.MatcheViewHolder>(MatchAdapter.ItemGroupDiffUtill()){

    var onItemClick: ((Matche) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatcheViewHolder {
        return MatcheViewHolder(
            MatchListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }


    override fun onBindViewHolder(holder: MatcheViewHolder, position: Int) {
        val cardoption = getItem(position)

        holder.binding.homeTeamTxt.text = cardoption.homeTeam?.name
        holder.binding.awayTeamTxt.text = cardoption.awayTeam?.name
        cardoption.competitionName=competitionName

        holder.loadLogo(LOGOBASE_URL.plus(cardoption.homeTeam?.id).plus(".svg"),
            LOGOBASE_URL.plus(cardoption.awayTeam?.id).plus(".svg"))


            if(cardoption.status?.equals("SCHEDULED") == true){
                holder.binding.score.text = context.getString(com.michealalu.goalapp.R.string.vs_txt)
                holder.binding.status.text = cardoption.status?.lowercase()


            }else if(cardoption.status?.equals("FINISHED") == true){

                holder.binding.score.text = cardoption.score?.fullTime?.homeTeam
                    .toString().plus(" : ").plus(cardoption.score?.fullTime?.awayTeam
                        .toString())
                holder.binding.status.text = cardoption.status?.lowercase()


            }else if(cardoption.status?.equals("IN_PLAY") == true){
                holder.binding.score.text = cardoption.score?.fullTime?.homeTeam
                    .toString().plus(" : ").plus(cardoption.score?.fullTime?.awayTeam
                        .toString())
                holder.binding.status.text = cardoption.status?.lowercase()
                holder.binding.status.setTextColor(Color.parseColor("#0A64D3"))

            }

    /*    holder.binding.favCheck.setOnClickListener {
            val checked: Boolean = holder.binding.favCheck.isChecked
            if(checked){
                cardoption.favourite=checked
            }else{
                cardoption.favourite=checked
            }
        }*/



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


    inner class MatcheViewHolder( val binding: MatchListBinding) : RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))

            }
        }

        fun loadLogo(url_home: String,url_away:String) {

            with(binding){
                if(url_home.endsWith("svg")){
                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url_home), homelogo)

                    GlideToVectorYou
                        .init()
                        .with(context)
                        .load(Uri.parse(url_away), awaylogo)
                }else{

                    Picasso.get()
                        .load(url_home)
                        .resize(40,40)
                        .centerCrop()
                        .into(homelogo)

                    Picasso.get()
                        .load(url_away)
                        .resize(40,40)
                        .centerCrop()
                        .into(awaylogo)

                   /* Glide.with(context).load(url_home).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(homelogo)

                    Glide.with(context).load(url_away).apply(
                        RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                    ).into(awaylogo)*/
                }
            }


        }
        

    }
}