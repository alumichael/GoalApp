package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwayTeam(
    @SerialName("bench")
    var bench: List<Bench>? = null,
    @SerialName("captain")
    var captain: Captain? = null,
    @SerialName("coach")
    var coach: Coach? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("lineup")
    var lineup: List<Lineup>? = null,
    @SerialName("name")
    var name: String? = null
)