package com.michealalu.goalapp.model.team


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Squad(
    @SerialName("countryOfBirth")
    var countryOfBirth: String? = null,
    @SerialName("dateOfBirth")
    var dateOfBirth: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("nationality")
    var nationality: String? = null,
    @SerialName("position")
    var position: String? = null,
    @SerialName("role")
    var role: String? = null
)