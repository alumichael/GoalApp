package com.michealalu.goalapp.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michealalu.goalapp.data.network.Resource
import com.michealalu.goalapp.UserRepository
import com.michealalu.goalapp.data.local.ResourceDb
import com.michealalu.goalapp.data.network.ApiInterface
import com.michealalu.goalapp.model.FavTeam
import com.michealalu.goalapp.model.competitions.Competition
import com.michealalu.goalapp.model.competitions.GetCompetitions
import com.michealalu.goalapp.model.match.GetAMatch
import com.michealalu.goalapp.model.match.GetMatchesByCompetitn
import com.michealalu.goalapp.model.standings.GetStandingByCompetitn
import com.michealalu.goalapp.model.team.GetATeam
import com.michealalu.goalapp.model.team.GetTeamByCompetitn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
         val userRepository: UserRepository,
         val api: ApiInterface
  ) : ViewModel() {

    //get all competitions live data
    private val _competitionResponse: MutableLiveData<Resource<GetCompetitions>> = MutableLiveData()
    val competitionResponse: LiveData<Resource<GetCompetitions>> get() = _competitionResponse

    //get a competition live data
    private val _oneCompetitionResponse: MutableLiveData<Resource<GetCompetitions>> = MutableLiveData()
    val oneCompetitionResponse: LiveData<Resource<GetCompetitions>> get() = _oneCompetitionResponse

    //get all match-by-competition live data
    private val _matchResponse: MutableLiveData<Resource<GetMatchesByCompetitn>> = MutableLiveData()
    val matchResponse: LiveData<Resource<GetMatchesByCompetitn>> get() = _matchResponse

    //get all teams-by-competition live data
    private val _teamsResponse: MutableLiveData<Resource<GetTeamByCompetitn>> = MutableLiveData()
    val teamsResponse: LiveData<Resource<GetTeamByCompetitn>> get() = _teamsResponse

    //get all teams-by-competition live data
    private val _oneTeamsResponse: MutableLiveData<Resource<GetATeam>> = MutableLiveData()
    val oneTeamsResponse: LiveData<Resource<GetATeam>> get() = _oneTeamsResponse

    //get standing-by-competition live data
    private val _standingResponse: MutableLiveData<Resource<GetStandingByCompetitn>> = MutableLiveData()
    val standingResponse: LiveData<Resource<GetStandingByCompetitn>> get() = _standingResponse

    //db favourite team livedata
    private val _getallfavteamResponse: MutableLiveData<ResourceDb<Flow<List<FavTeam>>>> = MutableLiveData()
    val getallfavteamResponse: LiveData<ResourceDb<Flow<List<FavTeam>>>> get() = _getallfavteamResponse


    /**
     * Api logic
     * **/
    fun onGetAllCompetitions() = viewModelScope.launch {
        _competitionResponse.value = Resource.Loading
        _competitionResponse.value = userRepository.onGetAllCompetitions()
    }

    fun onGetACompetition(id:Int) = viewModelScope.launch {
        _oneCompetitionResponse.value = Resource.Loading
        _oneCompetitionResponse.value = userRepository.onGetACompetition(id)
    }

    fun onGetMatchByCompetitn(id:Int) = viewModelScope.launch {
        _matchResponse.value = Resource.Loading
        _matchResponse.value = userRepository.onGetMatchByCompetitn(id)
    }


    fun onGetTeamsByCompetition(id:Int) = viewModelScope.launch {
        _teamsResponse.value = Resource.Loading
        _teamsResponse.value = userRepository.onGetTeamsByCompetition(id)
    }

    fun onGetATeams(id:Int) = viewModelScope.launch {
        _oneTeamsResponse.value = Resource.Loading
        _oneTeamsResponse.value = userRepository.onGetATeams(id)
    }

    fun onGetStandByCompetition(id:Int) = viewModelScope.launch {
        _standingResponse.value = Resource.Loading
        _standingResponse.value = userRepository.onGetStandByCompetition(id)
    }


    /**
     * Local logic
     * **/
    fun insertTeamDetails(team:FavTeam) = viewModelScope.launch {
        userRepository.createTeamRecords(team)
    }


    fun deleteSingleTeamRecord(id:Int) = viewModelScope.launch {
        userRepository.deleteSingleTeamRecord(id)
    }

}