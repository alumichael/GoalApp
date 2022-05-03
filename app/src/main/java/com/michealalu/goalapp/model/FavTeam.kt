package com.michealalu.goalapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michealalu.goalapp.model.team.Area
import kotlinx.serialization.SerialName

@Entity(tableName = "team_table")
data class FavTeam(

    @SerialName("id")
    @PrimaryKey var id: Int,

    @SerialName("address")
    var address: String? = null,
    @SerialName("clubColors")
    var clubColors: String? = null,
    @SerialName("crestUrl")
    var crestUrl: String? = null,
    @SerialName("email")
    var email: String? = null,
    @SerialName("founded")
    var founded: Int? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("phone")
    var phone: String? = null,
    @SerialName("shortName")
    var shortName: String? = null,
    @SerialName("tla")
    var tla: String? = null,
    @SerialName("venue")
    var venue: String? = null,
    @SerialName("website")
    var website: String? = null,
    @SerialName("favourite")
    var favourite: Boolean? = null

)