package com.michealalu.goalapp.data.local.favourite_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.michealalu.goalapp.model.FavTeam
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    /**
     * CREATE
     */
    //insert data to room database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToRoomDatabase(team: FavTeam)

    /**
     * READ
     */
    //get all the team saved as favourite to room database
    @Transaction
    @Query("SELECT * FROM team_table ORDER BY id DESC")
    fun getTeamDetail() : List<FavTeam>

    //get single team saved as favourite to room database
    @Transaction
    @Query("SELECT * FROM team_table WHERE id = :id ORDER BY id DESC")
    fun getSingleTeamDetail(id: Int) : FavTeam

    /**
     * UPDATE
     */
    //update favourite team
    @Update
    suspend fun updateFavTeamDetails(team: FavTeam)

    /**
     * DELETE
     */
    //delete single team details
    @Query("DELETE FROM team_table WHERE id = :id")
    suspend fun deleteSingleFavTeamDetails(id: Int)

    //delete all team details
    @Delete
    suspend fun deleteAllFavTeamsDetails(team: FavTeam)
}