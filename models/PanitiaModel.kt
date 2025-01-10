package com.example.alp_vp.models

// Response model untuk Panitia
data class PanitiaResponse(
    val id: Int,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String
)

// Fungsi untuk mengonversi daftar Panitia menjadi daftar PanitiaResponse
fun toPanitiaResponseList(panitiaList: List<Panitia>): List<PanitiaResponse> {
    return panitiaList.map { panitia ->
        PanitiaResponse(
            id = panitia.id,
            organisasi = panitia.organisasi,
            title = panitia.title,
            description = panitia.description,
            startDate = panitia.startDate,
            poster = panitia.poster ?: ""  // Jika poster null, beri nilai default
        )
    }
}

// Fungsi untuk mengonversi Panitia tunggal menjadi PanitiaResponse
fun toPanitiaResponse(panitia: Panitia): PanitiaResponse {
    return PanitiaResponse(
        id = panitia.id,
        organisasi = panitia.organisasi,
        title = panitia.title,
        description = panitia.description,
        startDate = panitia.startDate,
        poster = panitia.poster ?: ""  // Jika poster null, beri nilai default
    )
}

// Request model untuk membuat Panitia
data class PanitiaCreateRequest(
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?  // poster bisa null
)

// Request model untuk memperbarui Panitia
data class PanitiaUpdateRequest(
    val id: Int,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?  // poster bisa null
)

// Mock model untuk representasi data Panitia
data class Panitia(
    val id: Int,
    val organisasi: String,
    val title: String,
    val description: String,
    val startDate: String,
    val poster: String?
)
