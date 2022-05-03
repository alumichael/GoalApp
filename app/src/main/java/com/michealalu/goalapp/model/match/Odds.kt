package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Odds(
    @SerialName("msg")
    var msg: String? = null
)