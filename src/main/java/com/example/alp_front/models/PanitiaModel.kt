package com.example.alp_front.models

import com.example.alp_front.enums.PrioritiesEnum
import com.google.gson.annotations.SerializedName

data class GetAllPanitiaResponse(
    val data: List<PanitiaModel>
)

data class GetPanitiaResponse(
    val data: PanitiaModel
)

data class PanitiaModel(
    val id: Int = 0,
    val organisasi: String = "",
    val title: String = "",
    val description: String = "",
    val poster: String = "",

    @SerializedName("start_date")
    val start_Date: String = ""
)

data class PanitiaRequest(
    val title: String = "",
    val organisasi: String = "",
    val description: String = "",
    val poster: String = "",


    @SerializedName("start_date")
    val Start_Date: String = ""
)


