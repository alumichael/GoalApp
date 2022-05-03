package com.michealalu.goalapp.model.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("availableStages")
    var availableStages: List<String>? = null,
    @SerialName("currentMatchday")
    var currentMatchday: Int? = null,
    @SerialName("endDate")
    var endDate: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("startDate")
    var startDate: String? = null
)