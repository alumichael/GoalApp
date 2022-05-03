package com.michealalu.goalapp.model.competitions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentSeason(
    @SerialName("currentMatchday")
    var currentMatchday: Int? = null,
    @SerialName("endDate")
    var endDate: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("startDate")
    var startDate: String? = null
)