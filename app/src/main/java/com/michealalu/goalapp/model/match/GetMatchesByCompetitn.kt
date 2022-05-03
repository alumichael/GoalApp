package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMatchesByCompetitn(
    @SerialName("competition")
    var competition: Competition? = null,
    @SerialName("count")
    var count: Int? = null,
    @SerialName("filters")
    var filters: Filters? = null,
    @SerialName("matches")
    var matches: List<Matche>? = null
)