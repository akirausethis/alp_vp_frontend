package com.example.alp_vp.models

// Response untuk getAllPanitia
data class GetAllSurveyResponse(
    val data: List<SurveyModel>
)

data class GetSurveyResponse(
    val data: SurveyModel
)

data class SurveyModel(
    val id: Long,  // Changed 'Number' to 'Long' for consistency
    val title: String,
    val startDate: String,
    val endDate: String
)

// Model untuk respon survei
data class SurveyResponse(
    val id: Long, // Changed 'Int' to 'Long' for consistency
    val title: String,
    val startDate: String,
    val endDate: String
)

// Fungsi untuk mengonversi daftar Survey menjadi daftar SurveyResponse
fun toSurveyResponseList(surveys: List<SurveyModel>): List<SurveyResponse> {
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
fun toSurveyResponse(survey: SurveyModel): SurveyResponse {
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
    val id: Long, // Changed 'Int' to 'Long' for consistency
    val title: String,
    val startDate: String,
    val endDate: String
)
