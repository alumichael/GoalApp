package com.michealalu.goalapp.model.match


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Goal(
    @SerialName("assist")
    var assist: Assist? = null,
    @SerialName("extraTime")
    var extraTime: Int? = null,
    @SerialName("minute")
    var minute: Int? = null,
    @SerialName("scorer")
    var scorer: Scorer? = null,
    @SerialName("team")
    var team: Team? = null,
    @SerialName("type")
    var type: String? = null
)