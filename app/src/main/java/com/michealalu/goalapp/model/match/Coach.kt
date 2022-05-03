package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coach(
    @SerialName("countryOfBirth")
    var countryOfBirth: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("nationality")
    var nationality: String? = null
)