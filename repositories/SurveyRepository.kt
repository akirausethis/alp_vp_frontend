package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.SurveyAPIService
import retrofit2.Call

interface SurveyRepository {

    fun getAllSurvey(token: String): Call<GetAllSurveyResponse>

    fun createSurvey(
        token: String,
        surveyCreateRequest: SurveyCreateRequest
    ): Call<GeneralModel>

    fun getSurvey(token: String, surveyId: Long): Call<GetSurveyResponse>
}

class NetworkSurveyRepository(
    private val surveyAPIService: SurveyAPIService
) : SurveyRepository {

    override fun getAllSurvey(token: String): Call<GetAllSurveyResponse> {
        return surveyAPIService.getAllSurvey(token)
    }

    override fun createSurvey(
        token: String,
        surveyCreateRequest: SurveyCreateRequest
    ): Call<GeneralModel> {
        return surveyAPIService.createSurvey(token, surveyCreateRequest)
    }

    override fun getSurvey(token: String, surveyId: Long): Call<GetSurveyResponse> {
        return surveyAPIService.getSurvey(token, surveyId)
    }
}
