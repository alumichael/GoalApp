package com.michealalu.goalapp.model.standings


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Standing(
    @SerialName("group")
    var group: String? = null,
    @SerialName("stage")
    var stage: String? = null,
    @SerialName("table")
    var table: List<Table>? = null,
    @SerialName("type")
    var type: String? = null
)