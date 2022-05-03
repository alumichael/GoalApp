package com.michealalu.goalapp.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michealalu.goalapp.model.match.*
import com.michealalu.goalapp.model.team.Area
import kotlinx.serialization.SerialName

@Entity(tableName = "match_table")
data class FavMatch(
    @SerialName("id")
    @PrimaryKey var id: Int,

    @SerialName("attendance")
    var attendance: Int? = null,
    @SerialName("awayTeam")
    @Embedded var awayTeam: AwayTeam? = null,
    @SerialName("goals")
    @Embedded var goals: List<Goal>? = null,
    @SerialName("group")
    var group: String? = null,
    @SerialName("homeTeam")
    @Embedded var homeTeam: HomeTeam? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("matchday")
    var matchday: Int? = null,
    @SerialName("score")
    @Embedded var score: Score? = null,
    @SerialName("stage")
    var stage: String? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("utcDate")
    var utcDate: String? = null,
    @SerialName("competition_name")
    var competitionName: String? = null,
    @SerialName("favourite")
    var favourite: Boolean? = null

)