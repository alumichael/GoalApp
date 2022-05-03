package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lineup(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("position")
    var position: String? = null,
    @SerialName("shirtNumber")
    var shirtNumber: Int? = null
)