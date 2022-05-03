package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Substitution(
    @SerialName("minute")
    var minute: Int? = null,
    @SerialName("playerIn")
    var playerIn: PlayerIn? = null,
    @SerialName("playerOut")
    var playerOut: PlayerOut? = null,
    @SerialName("team")
    var team: Team? = null
)