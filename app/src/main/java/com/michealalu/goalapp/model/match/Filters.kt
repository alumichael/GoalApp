package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Filters(
    @SerialName("matchday")
    var matchday: String? = null
)