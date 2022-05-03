package com.michealalu.goalapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.michealalu.goalapp.adapter.FavFavTeamAdapter
import com.michealalu.goalapp.adapter.StandingAdapter
import com.michealalu.goalapp.data.local.ResourceDb
import com.michealalu.goalapp.databinding.ActivityFavouriteBinding
import com.michealalu.goalapp.handleError
import com.michealalu.goalapp.model.FavTeam
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity(), FavFavTeamAdapter.FavouriteListener {
    private lateinit var binding: ActivityFavouriteBinding
    private val viewModel by viewModels<FavouriteViewModel>()
    private lateinit var favListAdapter : FavFavTeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        //fetch all favourite team from local db
        getTeamDetails()

    }

    private fun getTeamDetails(){
        lifecycleScope.launch {

                viewModel.getAllFavTeam()
                viewModel.getallfavteamResponse.observe(this@FavouriteActivity, Observer {
                    try {
                        with(binding){
                            when (it) {
                                is ResourceDb.Success -> {
                                    if(!it.value.isNullOrEmpty()){
                                        favListAdapter = FavFavTeamAdapter(this@FavouriteActivity,this@FavouriteActivity)
                                        favRecyclerview.adapter = favListAdapter
                                        favListAdapter.submitList(it.value)
                                    }else{
                                        emptyTxt.visibility= View.VISIBLE

                                    }

                                }
                                is ResourceDb.Failure -> {
                                    handleError(
                                        context = this@FavouriteActivity,
                                        resourcedb = it
                                    )

                                }

                                else -> {}
                            }
                        }

                    } catch (e: Exception) {
                        //dialogTransparent.dismiss()
                        handleError(
                            optional_msg = e.message,
                            context = this@FavouriteActivity
                        )
                    }
                })


        }



    }


    override fun onSelectFavTeam(team: FavTeam) {}

    override fun onUnSelectFavTeam(team: FavTeam,pos:Int) {
        viewModel.deleteSingleTeamRecord(team.id)
        favListAdapter.notifyItemRemoved(pos)
    }
}