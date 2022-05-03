package com.michealalu.goalapp.model.competitions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCompetitions(
    @SerialName("competitions")
    var competitions: List<Competition>? = null,
    @SerialName("count")
    var count: Int? = null,
    @SerialName("filters")
    var filters: Filters? = null
)