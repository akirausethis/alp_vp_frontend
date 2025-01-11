package com.example.alp_vp.models

// Response untuk getAllPanitia
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
    val startDate: String,  // Konsisten nama properti
    val poster: String?
)

// Response untuk Panitia
data class PanitiaResponse(
    val id: Number,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String
)

// Request untuk membuat Panitia
data class PanitiaCreateRequest(
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?  // Optional
)

// Request untuk memperbarui Panitia
data class PanitiaUpdateRequest(
    val id: Number,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?  // Optional
)

// Request untuk menghapus Panitia
data class PanitiaDeleteRequest(
    val id: Number
)

// Mock model
data class Panitia(
    val id: Int,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)

// Fungsi konversi
fun toPanitiaResponseList(panitiaList: List<Panitia>): List<PanitiaResponse> {
    return panitiaList.map { panitia ->
        PanitiaResponse(
            id = panitia.id,
            organisasi = panitia.organisasi,
            title = panitia.title,
            description = panitia.description,
            startDate = panitia.startDate,
            poster = panitia.poster ?: ""  // Default jika null
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
        poster = panitia.poster ?: ""  // Default jika null
    )
}
