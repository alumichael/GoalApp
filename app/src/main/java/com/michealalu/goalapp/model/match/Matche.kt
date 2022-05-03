package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Matche(
    @SerialName("attendance")
    var attendance: Int? = null,
    @SerialName("awayTeam")
    var awayTeam: AwayTeam? = null,
    @SerialName("bookings")
    var bookings: List<Booking>? = null,
    @SerialName("goals")
    var goals: List<Goal>? = null,
    @SerialName("group")
    var group: String? = null,
    @SerialName("competition")
    var competition: Competition? = null,
    @SerialName("homeTeam")
    var homeTeam: HomeTeam? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("matchday")
    var matchday: Int? = null,
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
    @SerialName("substitutions")
    var substitutions: List<Substitution>? = null,
    @SerialName("utcDate")
    var utcDate: String? = null,
    @SerialName("competition_name")
    var competitionName: String? = null,
    @SerialName("favourite")
    var favourite: Boolean? = null
)