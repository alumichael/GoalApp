package com.michealalu.goalapp.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michealalu.goalapp.data.network.Resource
import com.michealalu.goalapp.UserRepository
import com.michealalu.goalapp.data.local.ResourceDb
import com.michealalu.goalapp.model.FavTeam
import com.michealalu.goalapp.model.competitions.GetCompetitions
import com.michealalu.goalapp.model.match.GetAMatch
import com.michealalu.goalapp.model.match.GetMatchesByCompetitn
import com.michealalu.goalapp.model.standings.GetStandingByCompetitn
import com.michealalu.goalapp.model.team.GetATeam
import com.michealalu.goalapp.model.team.GetTeamByCompetitn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
        private val userRepository: UserRepository
  ) : ViewModel() {

    //db favourite team livedata
    private val _getallfavteamResponse: MutableLiveData<ResourceDb<List<FavTeam>>> = MutableLiveData()
    val getallfavteamResponse: LiveData<ResourceDb<List<FavTeam>>>  get() = _getallfavteamResponse


    fun getAllFavTeam() = viewModelScope.launch {
        _getallfavteamResponse.value = userRepository.getAllFavTeam()
    }

    fun deleteSingleTeamRecord(id:Int) = viewModelScope.launch {
        userRepository.deleteSingleTeamRecord(id)
    }


}