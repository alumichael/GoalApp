package com.michealalu.goalapp.ui.activity

import android.R
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.android.material.chip.Chip
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.michealalu.goalapp.*
import com.michealalu.goalapp.adapter.*
import com.michealalu.goalapp.databinding.ActivityMatchDetailBinding
import com.michealalu.goalapp.model.match.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MatchDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchDetailBinding
    private val viewModel by viewModels<MatchDetailViewModel>()
    private lateinit var goalAdapter : GoalAdapter
    private lateinit var refereeAdapter : RefereeAdapter
    private lateinit var dialogTransparent: Dialog
    private lateinit var progressView: View
    private lateinit var matchContent: String
    private lateinit var match: Matche
    var goalList= listOf<Goal>()
    var refereList= listOf<Referee>()
    var subtitutionList= listOf<Substitution>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize progress view
        dialogTransparent = Dialog(this, R.style.Theme_Black)
        progressView = LayoutInflater.from(this).inflate(com.michealalu.goalapp.R.layout.custom_progress_bar, null)
        progressBar(dialogTransparent,progressView)

        val extras = intent.extras
        if (extras != null) {
            matchContent = intent.getStringExtra("CONTENT").toString()

            try {

                val gson = GsonBuilder().create()
                    match = gson.fromJson(matchContent, object :
                        TypeToken<Matche>() {}.type)

                if(match.id!=null){
                    with(binding){
                        toolbar.title= match.competitionName

                        loadLogo(
                            Constant.LOGOBASE_URL.plus(match.homeTeam?.id).plus(".svg"),
                            Constant.LOGOBASE_URL.plus(match.awayTeam?.id).plus(".svg"))

                        macthDay.text= match.matchday.toString().plus("'")
                        homeTeamTxt.text=match.homeTeam?.name
                        awayTeamTxt.text=match.awayTeam?.name
                        stageTxt.text=match.stage
                        dateTxt.text= match.utcDate?.let { convertDateTime(it) }

                        if(match.status?.equals("SCHEDULED") == true){
                            score.text = "vs"
                            status.text = match.status?.lowercase()

                        }else if(match.status?.equals("FINISHED") == true){

                            score.text = match.score?.fullTime?.homeTeam
                                .toString().plus(" : ").plus(match.score?.fullTime?.awayTeam
                                    .toString())
                            status.text = match.status?.lowercase()


                        }else if(match.status?.equals("IN_PLAY") == true){
                            score.text = match.score?.fullTime?.homeTeam
                                .toString().plus(" : ").plus(match.score?.fullTime?.awayTeam
                                    .toString())
                            status.text = match.status?.lowercase()
                            status.setTextColor(Color.parseColor("#0A64D3"))

                        }


                        if(!match.goals.isNullOrEmpty()){
                            goalList = match.goals!!
                        }

                        if(!match.substitutions.isNullOrEmpty()){
                            subtitutionList = match.substitutions!!
                        }


                        if(!match.referees.isNullOrEmpty()){
                            refereList = match.referees!!
                        }

                        setCurentRecyclerView(referee = true)
                    }
                }


            }catch (e:Exception){
                handleError(context = this, optional_msg = e.message)
            }

        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip = group.findViewById(checkedId)
            when (chip.tag) {
                "chipGoals"->{
                    chip.chipBackgroundColor= ColorStateList.valueOf(Color.parseColor("#E9EF1397"))
                    setCurentRecyclerView(goal = true)
                    toast("Check back",this)

                }

                "chip_lineup"->{
                    chip.chipBackgroundColor= ColorStateList.valueOf(Color.parseColor("#E9EF1397"))
                    toast("Check back",this)
                }

                "chip_subtitution"->{
                    chip.chipBackgroundColor= ColorStateList.valueOf(Color.parseColor("#E9EF1397"))
                    toast("Check back",this)
                }

                "chip_refree"->{
                    chip.chipBackgroundColor= ColorStateList.valueOf(Color.parseColor("#E9EF1397"))
                    setCurentRecyclerView(referee = true)
                }

            }
        }

       with(binding){
           toolbar.setNavigationOnClickListener {
               onBackPressed()
           }

       }

    }


private fun setCurentRecyclerView(goal:Boolean?=false, lineUp:Boolean?=false, substitution:Boolean?=false,
                                  referee:Boolean?=false){
        with(binding){
            if(goal == true){
                if(!goalList.isNullOrEmpty()) {
                    refreeRecyclerview.visibility = View.INVISIBLE
                    substitionRecyclerview.visibility = View.INVISIBLE
                    goalsRecyclerview.visibility = View.VISIBLE

                    goalAdapter = GoalAdapter(this@MatchDetailActivity)
                    goalsRecyclerview.adapter = goalAdapter
                    goalAdapter.submitList(goalList)
                }

            }else if(referee == true){
                if(!refereList.isNullOrEmpty()) {
                    substitionRecyclerview.visibility = View.INVISIBLE
                    goalsRecyclerview.visibility = View.INVISIBLE
                    refreeRecyclerview.visibility = View.VISIBLE

                    Log.i("refereListSize",refereList.size.toString())

                    refereeAdapter = RefereeAdapter(this@MatchDetailActivity)
                    refreeRecyclerview.adapter = refereeAdapter
                    refereeAdapter.submitList(refereList)
                }

            }
        }


    }

    fun loadLogo(url_home: String,url_away:String) {

        with(binding){
            if(url_home.endsWith("svg")){
                GlideToVectorYou
                    .init()
                    .with(this@MatchDetailActivity)
                    .load(Uri.parse(url_home), homeLogo)

                GlideToVectorYou
                    .init()
                    .with(this@MatchDetailActivity)
                    .load(Uri.parse(url_away), awayLogo)
            }else{

                Picasso.get()
                    .load(url_home)
                    .centerCrop()
                    .into(homeLogo)

                Picasso.get()
                    .load(url_away)
                    .centerCrop()
                    .into(awayLogo)

                /*Glide.with(this@MatchDetailActivity).load(url_home).apply(
                    RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                ).into(homeLogo)

                Glide.with(this@MatchDetailActivity).load(url_away).apply(
                    RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).fitCenter()
                ).into(awayLogo)*/
            }
        }




    }


    private fun convertDateTime(utcDate: String): String {

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val output = SimpleDateFormat("d MMM yyyy h:mm a", Locale.ENGLISH)
        input.timeZone = TimeZone.getTimeZone("Africa/Lagos")
        output.timeZone = TimeZone.getTimeZone("Africa/Lagos")

        var d: Date? = null
        try {
            d = input.parse(utcDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val formatted = output.format(d)
        Log.i("utcdate", "" + formatted)
        return formatted

    }


}