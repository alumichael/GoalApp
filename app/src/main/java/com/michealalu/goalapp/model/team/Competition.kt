package com.michealalu.goalapp.model.team


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("area")
    var area: Area? = null,
    @SerialName("code")
    var code: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("plan")
    var plan: String? = null
)