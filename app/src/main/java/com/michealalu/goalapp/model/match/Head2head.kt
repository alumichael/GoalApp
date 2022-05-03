package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Head2head(
    @SerialName("awayTeam")
    var awayTeam: AwayTeamX? = null,
    @SerialName("homeTeam")
    var homeTeam: HomeTeamX? = null,
    @SerialName("numberOfMatches")
    var numberOfMatches: Int? = null,
    @SerialName("totalGoals")
    var totalGoals: Int? = null
)