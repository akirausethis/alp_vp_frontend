package com.example.alp_vp.models

data class GetAllPanitiaResponse(
    val data: List<PanitiaModel>
)

data class GetPanitiaResponse(
    val data: PanitiaModel
)

data class PanitiaModel(
    val id: Number,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)


data class PanitiaResponse(
    val id: Number,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)

data class PanitiaCreateRequest(
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)


data class PanitiaUpdateRequest(
    val id: Number,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)

data class PanitiaDeleteRequest(
    val id: Number
)

data class Panitia(
    val id: Int,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)

fun toPanitiaResponseList(panitiaList: List<Panitia>): List<PanitiaResponse> {
    return panitiaList.map { panitia ->
        PanitiaResponse(
            id = panitia.id,
            organisasi = panitia.organisasi,
            title = panitia.title,
            description = panitia.description,
            startDate = panitia.startDate,
            poster = panitia.poster ?: ""
        )
    }
}

fun toPanitiaResponse(panitia: Panitia): PanitiaResponse {
    return PanitiaResponse(
        id = panitia.id,
        organisasi = panitia.organisasi,
        title = panitia.title,
        description = panitia.description,
        startDate = panitia.startDate,
        poster = panitia.poster ?: ""
    )
}
