package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface QuestionAPIService {

    @GET("api/survey/{surveyId}/questions")
    fun getAllQuestions(
        @Header("X-API-TOKEN") token: String,
        @Path("surveyId") surveyId: Int
    ): Call<GetAllQuestionResponse>

    @GET("api/question/{id}")
    fun getQuestion(
        @Header("X-API-TOKEN") token: String,
        @Path("id") questionId: Int
    ): Call<GetQuestionResponse>

    @POST("api/question")
    fun createQuestion(
        @Header("X-API-TOKEN") token: String,
        @Body questionCreateRequest: QuestionCreateRequest
    ): Call<GeneralModel>

    @PUT("api/question/{id}")
    fun updateQuestion(
        @Header("X-API-TOKEN") token: String,
        @Path("id") questionId: Int,
        @Body questionUpdateRequest: QuestionUpdateRequest
    ): Call<GeneralModel>
}
