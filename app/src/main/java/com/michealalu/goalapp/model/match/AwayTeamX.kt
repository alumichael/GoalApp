package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwayTeamX(
    @SerialName("draws")
    var draws: Int? = null,
    @SerialName("losses")
    var losses: Int? = null,
    @SerialName("wins")
    var wins: Int? = null
)