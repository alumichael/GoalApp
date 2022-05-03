package com.michealalu.goalapp.model.team


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTeamByCompetitn(
    @SerialName("competition")
    var competition: Competition? = null,
    @SerialName("count")
    var count: Int? = null,
    @SerialName("filters")
    var filters: Filters? = null,
    @SerialName("season")
    var season: Season? = null,
    @SerialName("teams")
    var teams: List<Team>? = null
)