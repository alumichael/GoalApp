package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAMatch(
    @SerialName("head2head")
    var head2head: Head2head? = null,
    @SerialName("match")
    var match: Match? = null
)