package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Assist(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null
)