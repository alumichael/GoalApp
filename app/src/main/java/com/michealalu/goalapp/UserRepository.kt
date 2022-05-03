package com.michealalu.goalapp




import com.michealalu.goalapp.data.local.SafeDatabaseCall
import com.michealalu.goalapp.data.local.favourite_db.TeamDao
import com.michealalu.goalapp.data.network.ApiInterface
import com.michealalu.goalapp.data.network.SafeApiCall
import com.michealalu.goalapp.model.FavTeam
import javax.inject.Inject

class UserRepository @Inject constructor(
    private  val api: ApiInterface,
    private val teamDao: TeamDao
) : SafeApiCall , SafeDatabaseCall {

    /**
     * Online Data
     */
    suspend fun onGetAllCompetitions(
    ) =safeApiCall{
        api.onGetAllCompetitions()
    }

    suspend fun onGetACompetition(
        id:Int
    ) =safeApiCall{
        api.onGetACompetition(id)
    }

    suspend fun onGetMatchByCompetitn(
        id:Int
    ) =safeApiCall{
        api.onGetMatchByCompetitn(id)
    }

    suspend fun onGetAMatch(
        id:Int
    ) =safeApiCall{
        api.onGetAMatch(id)
    }

    suspend fun onGetTeamsByCompetition(
        id:Int
    ) =safeApiCall{
        api.onGetTeamsByCompetition(id)
    }

    suspend fun onGetATeams(
        id:Int
    ) =safeApiCall{
        api.onGetATeams(id)
    }

    suspend fun onGetStandByCompetition(
        id:Int
    ) =safeApiCall{
        api.onGetStandByCompetition(id)
    }

    /**
     * Local Data
     * **/

    //insert team to room
    suspend fun createTeamRecords(team: FavTeam) = safeDbCall {
        teamDao.insertToRoomDatabase(team)
    }

    //insert team to room
    suspend fun getAllFavTeam() = safeDbCall {
        teamDao.getTeamDetail()
    }

    //delete single team record
    suspend fun deleteSingleTeamRecord(id:Int)= safeDbCall {
        teamDao.deleteSingleFavTeamDetails(id)
    }


}