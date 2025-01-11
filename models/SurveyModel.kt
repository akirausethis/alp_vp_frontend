package com.example.alp_vp.models

data class GetAllSurveyResponse(
    val data: List<SurveyModel>
)

data class GetSurveyResponse(
    val data: SurveyModel
)

data class SurveyModel(
    val id: Long,
    val title: String,
    val startDate: String,
    val endDate: String
)

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

fun toSurveyResponse(survey: SurveyModel): SurveyResponse {
    return SurveyResponse(
        id = survey.id,
        title = survey.title,
        startDate = survey.startDate,
        endDate = survey.endDate
    )
}

data class SurveyCreateRequest(
    val title: String,
    val startDate: String,
    val endDate: String
)

data class SurveyResponse(
    val id: Long,
    val title: String,
    val startDate: String,
    val endDate: String
)

data class Survey(
    val id: Long,
    val title: String,
    val startDate: String,
    val endDate: String
)
