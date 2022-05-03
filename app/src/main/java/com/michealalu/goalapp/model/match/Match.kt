package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Match(
    @SerialName("awayTeam")
    var awayTeam: AwayTeamXX? = null,
    @SerialName("competition")
    var competition: Competition? = null,
    @SerialName("group")
    var group: String? = null,
    @SerialName("homeTeam")
    var homeTeam: HomeTeamXX? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("matchday")
    var matchday: Int? = null,
    @SerialName("odds")
    var odds: Odds? = null,
    @SerialName("referees")
    var referees: List<Referee>? = null,
    @SerialName("score")
    var score: Score? = null,
    @SerialName("season")
    var season: Season? = null,
    @SerialName("stage")
    var stage: String? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("utcDate")
    var utcDate: String? = null,
    @SerialName("venue")
    var venue: String? = null
)