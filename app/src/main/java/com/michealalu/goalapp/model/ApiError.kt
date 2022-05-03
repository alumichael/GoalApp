package com.michealalu.goalapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("errorCode")
    var errorCode: Int? = null,
    @SerialName("message")
    var message: String? = null
)