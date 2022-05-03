package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    @SerialName("card")
    var card: String? = null,
    @SerialName("minute")
    var minute: Int? = null,
    @SerialName("player")
    var player: Player? = null,
    @SerialName("team")
    var team: Team? = null
)