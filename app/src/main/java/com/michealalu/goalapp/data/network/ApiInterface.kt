package com.michealalu.goalapp.data.network



import com.michealalu.goalapp.model.competitions.GetCompetitions
import com.michealalu.goalapp.model.match.GetAMatch
import com.michealalu.goalapp.model.match.GetMatchesByCompetitn
import com.michealalu.goalapp.model.standings.GetStandingByCompetitn
import com.michealalu.goalapp.model.team.GetATeam
import com.michealalu.goalapp.model.team.GetTeamByCompetitn
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiInterface {

    //competitions
    @GET("v2/competitions")
    suspend fun onGetAllCompetitions(): GetCompetitions

    @GET("v2/competitions/{id}")
    fun onGetACompetition(
        @Path("id") id:Int
    ): GetCompetitions

    //match
    @GET("v2/competitions/{id}/matches")
    suspend fun onGetMatchByCompetitn(
        @Path("id") id:Int
    ): GetMatchesByCompetitn

    @GET("v2/matches/{id}")
    suspend fun onGetAMatch(
        @Path("id") id:Int
    ): GetAMatch

    //teams
    @GET("v2/competitions/{id}/teams")
    suspend fun onGetTeamsByCompetition(
        @Path("id") id:Int
    ): GetTeamByCompetitn

    @GET("v2/teams/{id}")
    suspend fun onGetATeams(
        @Path("id") id:Int
    ): GetATeam

    //standing
    @GET("v2/competitions/{id}/standings")
    suspend fun onGetStandByCompetition(
        @Path("id") id:Int
    ): GetStandingByCompetitn







}

