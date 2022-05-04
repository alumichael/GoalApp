package com.michealalu.goalapp.ui.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.michealalu.goalapp.data.network.Resource
import com.michealalu.goalapp.databinding.ActivityHomeBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.google.gson.Gson
import com.michealalu.goalapp.*
import com.michealalu.goalapp.adapter.*
import com.michealalu.goalapp.databinding.DialogTeamsListBinding

import com.michealalu.goalapp.model.FavTeam
import com.michealalu.goalapp.model.match.Matche
import com.michealalu.goalapp.model.team.Team


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    TeamAdapter.FavouriteTeamListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialogTeamsListBinding: DialogTeamsListBinding
    private lateinit var dialogTransparent: Dialog
    private lateinit var dialogFloat: RoundedBottomSheetDialog
    private lateinit var progressView: View
    private lateinit var dialogSheetView: View
    private lateinit var competitionAdapter : CompetitionAdapter
    private lateinit var liveCompetAdapter: LiveCompetAdapter
    private lateinit var matchAdapter : MatchAdapter
    private lateinit var stanidngAdapter : StandingAdapter
    private lateinit var teamAdapter : TeamAdapter
    private val viewModel by viewModels<HomeViewModel>()
    private var selectedCompetitnId: Int = 0
    private var selectedCompetitnName: String =""
    var liveMatch= listOf<Matche>()
    var teamList= listOf<Team>()

    //gson converter
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize progress view
        dialogTransparent = Dialog(this, android.R.style.Theme_Black)
        progressView = LayoutInflater.from(this).inflate(R.layout.custom_progress_bar, null)

        //initialize bottomsheet view
        dialogSheetView = layoutInflater.inflate(R.layout.dialog_teams_list, null, false)
        dialogTeamsListBinding = DialogTeamsListBinding.bind(dialogSheetView)
        dialogFloat = RoundedBottomSheetDialog(this)
        progressBar(dialogTransparent,progressView)

        with(binding){
            //set bottom navigation onclick listener
            bottomNav.setOnItemSelectedListener { item->
                when (item.itemId) {
                    R.id.menu_home -> {
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_teams -> {
                        teamFloatDialog(dialogSheetView,dialogFloat, teamList)
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_fav -> {
                        startNewActivity(FavouriteActivity::class.java)
                        return@setOnItemSelectedListener true
                    }

                    else -> return@setOnItemSelectedListener true
                }
            }
            //set swipeRefreshlistener
            swipeRefreshLayout.setOnRefreshListener(this@HomeActivity)
            //set adapters
            competitionAdapter = CompetitionAdapter(this@HomeActivity)
            competitionRecyclerView.adapter = competitionAdapter

            if(isOnline(this@HomeActivity)){
                viewModel.onGetAllCompetitions()
                viewModel.competitionResponse.observe(this@HomeActivity, Observer {
                    lifecycleScope.launch {
                        dialogTransparent.show()
                        try {
                            when (it) {
                                is Resource.Success -> {
                                    if(!it.value.competitions.isNullOrEmpty()){
                                        (competitionRecyclerView.itemAnimator as SimpleItemAnimator)
                                            .supportsChangeAnimations = false
                                        val sortedList = it.value.competitions!!
                                            .sortedByDescending { it.id == 2021 }

                                        competitionAdapter.submitList(sortedList)

                                        selectedCompetitnId= sortedList[0].id!!
                                        selectedCompetitnName= sortedList[0].name!!
                                        getMatches(selectedCompetitnId)


                                        //on click competition
                                        competitionAdapter.onItemClick={
                                            dialogTransparent.show()
                                            //update competition Id
                                            selectedCompetitnId= it.id!!
                                            selectedCompetitnName= it.name!!
                                            getMatches(selectedCompetitnId)


                                        }

                                    }else{
                                        //dialogTransparent.dismiss()
                                        //display a modal
                                        handleError(
                                            optional_msg = "No available competitions",
                                            context = this@HomeActivity,
                                            dialogTransparent = dialogTransparent
                                        )
                                    }



                                }
                                is Resource.Failure -> {
                                    handleError(
                                        resource = it,
                                        context = this@HomeActivity,
                                        dialogTransparent = dialogTransparent
                                    )

                                }

                                else -> {}
                            }
                        } catch (e: Exception) {
                            //dialogTransparent.dismiss()
                            handleError(
                                optional_msg = e.message,
                                context = this@HomeActivity,
                                dialogTransparent = dialogTransparent,
                            )
                        }

                    }

                })

                viewModel.matchResponse.observe(this@HomeActivity, Observer {
                    lifecycleScope.launch {
                        try {
                            when (it) {
                                is Resource.Success -> {
                                    if(!it.value.matches.isNullOrEmpty()){
                                        liveMatch= it.value.matches?.asSequence()
                                            ?.filter { it.status.equals("IN_PLAY") }
                                            ?.toList()!!

                                        if(liveMatch.isNotEmpty()){
                                            liveCompetAdapter = LiveCompetAdapter(this@HomeActivity,selectedCompetitnName)
                                            liveRecyclerView.adapter = liveCompetAdapter
                                            liveCompetAdapter.submitList(liveMatch)
                                        }else{
                                            liveTxt.text=getString(R.string.no_live_match_txt)
                                        }

                                        matchAdapter = MatchAdapter(this@HomeActivity, selectedCompetitnName)
                                        macthRecyclerview.adapter = matchAdapter
                                        matchAdapter.submitList(it.value.matches!!
                                            .sortedByDescending { it.status.equals("SCHEDULED") })

                                        //on clicking a match
                                        matchAdapter.onItemClick={

                                                val contentJson = gson.toJson(it)
                                            Log.i("contentJson", contentJson)
                                                startNewActivityByID(MatchDetailActivity::class.java,
                                                    contentJson
                                                )


                                        }


                                    }else{

                                        emptyTxt.visibility=View.VISIBLE
                                        matchTxt.visibility=View.INVISIBLE

                                        standingTxt.visibility=View.INVISIBLE
                                        standingHeader.visibility=View.INVISIBLE

                                        //display a modal
                                        handleError(
                                            optional_msg = "No available match",
                                            context = this@HomeActivity,
                                            dialogTransparent = dialogTransparent,
                                        )

                                    }

                                }
                                is Resource.Failure -> {
                                    handleError(
                                        resource = it,
                                        context = this@HomeActivity,
                                        dialogTransparent = dialogTransparent,
                                    )

                                }

                                else -> {}
                            }
                        } catch (e: Exception) {
                            //dialogTransparent.dismiss()
                            handleError(
                                optional_msg = e.message,
                                context = this@HomeActivity,
                                dialogTransparent = dialogTransparent,
                            )
                        }

                    }

                })

                viewModel.standingResponse.observe(this@HomeActivity, Observer {
                    lifecycleScope.launch {
                        try {
                            when (it) {
                                is Resource.Success -> {

                                    if(!it.value.standings?.get(0)?.table.isNullOrEmpty()){
                                        stanidngAdapter = StandingAdapter(this@HomeActivity)
                                        standingsRecyclerview.adapter = stanidngAdapter
                                        stanidngAdapter.submitList(it.value.standings!![0].table!!)

                                    }else{
                                        standingTxt.visibility=View.INVISIBLE
                                        standingHeader.visibility=View.INVISIBLE


                                    }

                                }
                                is Resource.Failure -> {
                                    handleError(
                                        resource = it,
                                        context = this@HomeActivity,
                                        dialogTransparent = dialogTransparent
                                    )

                                }

                                else -> {}
                            }
                        } catch (e: Exception) {
                            //dialogTransparent.dismiss()
                            handleError(
                                optional_msg = e.message,
                                context = this@HomeActivity,
                                dialogTransparent = dialogTransparent
                            )
                        }

                    }

                })

                viewModel.teamsResponse.observe(this@HomeActivity, Observer {
                    lifecycleScope.launch {
                        try {
                            when (it) {
                                is Resource.Success -> {
                                    dialogTransparent.dismiss()
                                    swipeRefreshLayout.isRefreshing = false

                                    if(!it.value.teams.isNullOrEmpty()){
                                        teamList= it.value.teams!!

                                    }

                                }
                                is Resource.Failure -> {
                                    handleError(
                                        resource = it,
                                        context = this@HomeActivity,
                                        dialogTransparent = dialogTransparent,
                                    )

                                }

                                else -> {}
                            }
                        } catch (e: Exception) {
                            //dialogTransparent.dismiss()
                            handleError(
                                optional_msg = e.message,
                                context = this@HomeActivity,
                                dialogTransparent = dialogTransparent,
                            )
                        }

                    }

                })
            }else{
                homeLayout.visibility=View.INVISIBLE
                handleError(
                    optional_msg = "No Internet connection",
                    context = this@HomeActivity,
                    dialogTransparent = dialogTransparent,
                )
            }



        }
    }
    fun getMatches(selectedCompetitnId:Int){
        dialogTransparent.show()
        viewModel.onGetMatchByCompetitn(selectedCompetitnId)
            .invokeOnCompletion { viewModel.onGetStandByCompetition(selectedCompetitnId)
            .invokeOnCompletion { viewModel.onGetTeamsByCompetition(selectedCompetitnId) }}
    }


    override fun onRefresh() {
        binding.swipeRefreshLayout.isRefreshing = true
        getMatches(selectedCompetitnId)

    }

    private fun teamFloatDialog(
        dialogSheetView:View,
        dialog: RoundedBottomSheetDialog,
        teamList:List<Team>
    ) {
        dialog.setContentView(dialogSheetView)
        dialog.setCancelable(true)
        with(dialogTeamsListBinding){
            if(!teamList.isNullOrEmpty()){
                dialogSubTitle.text=selectedCompetitnName.plus(" Teams")
                teamAdapter = TeamAdapter(this@HomeActivity,this@HomeActivity)
                recyclerviewDialog.adapter = teamAdapter
                teamAdapter.submitList(teamList)
            }else{
                emptyTxt.visibility=View.VISIBLE
            }


        }

        dialogTeamsListBinding.cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    override fun onSelectTeam(team: Team) {

        team.id?.let {
            FavTeam(
                id = it,
                address = team.address,
                clubColors = team.clubColors,
                crestUrl = team.crestUrl,
                email = team.email,
                founded = team.founded,
                lastUpdated = team.lastUpdated,
                name = team.name,
                phone = team.phone,
                shortName = team.shortName,
                tla = team.tla,
                venue = team.venue,
                website = team.website,
                favourite = team.favourite
            )
        }?.let { viewModel.insertTeamDetails(it) }
        }
       


    override fun onUnSelectTeam(team: Team) {
        team.id?.let { viewModel.deleteSingleTeamRecord(it) }
    }
}