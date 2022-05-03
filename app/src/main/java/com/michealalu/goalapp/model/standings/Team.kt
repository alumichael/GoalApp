package com.michealalu.goalapp.model.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("crestUrl")
    var crestUrl: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null
)