package com.michealalu.goalapp.model.team


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("address")
    var address: String? = null,
    @SerialName("area")
    var area: Area? = null,
    @SerialName("clubColors")
    var clubColors: String? = null,
    @SerialName("crestUrl")
    var crestUrl: String? = null,
    @SerialName("email")
    var email: String? = null,
    @SerialName("founded")
    var founded: Int? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("lastUpdated")
    var lastUpdated: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("phone")
    var phone: String? = null,
    @SerialName("shortName")
    var shortName: String? = null,
    @SerialName("tla")
    var tla: String? = null,
    @SerialName("venue")
    var venue: String? = null,
    @SerialName("website")
    var website: String? = null,
    @SerialName("favourite")
    var favourite: Boolean? = null

)