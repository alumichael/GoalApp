package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null
)