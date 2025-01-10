package com.example.alp_vp.models

// Model untuk respon survei
data class SurveyResponse(
    val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String
)

// Fungsi untuk mengonversi daftar Survey menjadi daftar SurveyResponse
fun toSurveyResponseList(surveys: List<Survey>): List<SurveyResponse> {
    return surveys.map { survey ->
        SurveyResponse(
            id = survey.id,
            title = survey.title,
            startDate = survey.startDate,
            endDate = survey.endDate
        )
    }
}

// Fungsi untuk mengonversi Survey tunggal menjadi SurveyResponse
fun toSurveyResponse(survey: Survey): SurveyResponse {
    return SurveyResponse(
        id = survey.id,
        title = survey.title,
        startDate = survey.startDate,
        endDate = survey.endDate
    )
}

// Model untuk membuat survei baru
data class SurveyCreateRequest(
    val title: String,
    val startDate: String,
    val endDate: String
)

// Representasi data Survey (mock model yang cocok dengan backend)
data class Survey(
    val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String
)
