package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface SurveyAPIService {

    @GET("api/survey")
    fun getAllSurvey(
        @Header("X-API-TOKEN") token: String
    ): Call<GetAllSurveyResponse>

    @GET("api/survey/{id}")
    fun getSurvey(
        @Header("X-API-TOKEN") token: String,
        @Path("id") surveyId: Long
    ): Call<GetSurveyResponse>

    @POST("api/survey")
    fun createSurvey(
        @Header("X-API-TOKEN") token: String,
        @Body surveyCreateRequest: SurveyCreateRequest
    ): Call<GeneralModel>
}
