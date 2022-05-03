package com.michealalu.goalapp.model.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStandingByCompetitn(
    @SerialName("competition")
    var competition: Competition? = null,
    @SerialName("filters")
    var filters: Filters? = null,
    @SerialName("season")
    var season: Season? = null,
    @SerialName("standings")
    var standings: List<Standing>? = null
)