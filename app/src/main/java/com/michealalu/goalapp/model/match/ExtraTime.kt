package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExtraTime(
    @SerialName("awayTeam")
    var awayTeam: Int? = null,
    @SerialName("homeTeam")
    var homeTeam: Int? = null
)