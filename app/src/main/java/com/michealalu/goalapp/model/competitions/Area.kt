package com.michealalu.goalapp.model.competitions


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Area(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null
)