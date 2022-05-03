package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Score(
    @SerialName("duration")
    var duration: String? = null,
    @SerialName("extraTime")
    var extraTime: ExtraTime? = null,
    @SerialName("fullTime")
    var fullTime: FullTime? = null,
    @SerialName("halfTime")
    var halfTime: HalfTime? = null,
    @SerialName("penalties")
    var penalties: Penalties? = null,
    @SerialName("winner")
    var winner: String? = null
)