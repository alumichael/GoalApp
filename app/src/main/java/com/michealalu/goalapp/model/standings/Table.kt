package com.michealalu.goalapp.model.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Table(
    @SerialName("draw")
    var draw: Int? = null,
    @SerialName("goalDifference")
    var goalDifference: Int? = null,
    @SerialName("goalsAgainst")
    var goalsAgainst: Int? = null,
    @SerialName("goalsFor")
    var goalsFor: Int? = null,
    @SerialName("lost")
    var lost: Int? = null,
    @SerialName("playedGames")
    var playedGames: Int? = null,
    @SerialName("points")
    var points: Int? = null,
    @SerialName("position")
    var position: Int? = null,
    @SerialName("team")
    var team: Team? = null,
    @SerialName("won")
    var won: Int? = null
)