package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface FeedbackAPIService {

    // Fetch all feedbacks with token authorization
    @GET("api/feedback")
    fun getAllFeedback(
        @Header("X-API-TOKEN") token: String // Token for authorization
    ): Call<GetAllFeedbackResponse>

    // Fetch a single feedback by ID using token
    @GET("api/feedback/{id}")
    fun getFeedback(
        @Header("X-API-TOKEN") token: String, // Token for authorization
        @Path("id") feedbackId: Long // Use Long for the feedback ID
    ): Call<GetFeedbackResponse>

    // Create a new feedback
    @POST("api/feedback")
    fun createFeedback(
        @Header("X-API-TOKEN") token: String, // Token for authorization
        @Body feedbackCreateRequest: FeedbackCreateRequest // Pass the request body
    ): Call<GeneralModel>
}
