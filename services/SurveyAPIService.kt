package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface SurveyAPIService {

    // Fetch all surveys with token authorization
    @GET("api/survey")
    fun getAllSurvey(
        @Header("X-API-TOKEN") token: String // Token for authorization
    ): Call<GetAllSurveyResponse>

    // Fetch a single survey by ID using token
    @GET("api/survey/{id}")
    fun getSurvey(
        @Header("X-API-TOKEN") token: String, // Token for authorization
        @Path("id") surveyId: Long // Use Long for the survey ID
    ): Call<GetSurveyResponse>

    // Create a new survey
    @POST("api/survey")
    fun createSurvey(
        @Header("X-API-TOKEN") token: String, // Token for authorization
        @Body surveyCreateRequest: SurveyCreateRequest // Pass the request body
    ): Call<GeneralModel>
}
