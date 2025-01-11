package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.FeedbackAPIService
import retrofit2.Call

interface FeedbackRepository {

    // Fetch all feedbacks with the token for authentication
    fun getAllFeedback(token: String): Call<GetAllFeedbackResponse>

    // Create a new feedback
    fun createFeedback(
        token: String,  // Add token for API authentication
        feedbackCreateRequest: FeedbackCreateRequest // Accept FeedbackCreateRequest to simplify data input
    ): Call<GeneralModel>

    // Fetch a single feedback by ID
    fun getFeedback(token: String, feedbackId: Long): Call<GetFeedbackResponse>
}

class NetworkFeedbackRepository(
    private val feedbackAPIService: FeedbackAPIService
) : FeedbackRepository {

    // Get all feedbacks using the token
    override fun getAllFeedback(token: String): Call<GetAllFeedbackResponse> {
        return feedbackAPIService.getAllFeedback(token)
    }

    // Create a new feedback using the FeedbackCreateRequest
    override fun createFeedback(
        token: String,  // Pass token for authentication
        feedbackCreateRequest: FeedbackCreateRequest // Single parameter to simplify API call
    ): Call<GeneralModel> {
        return feedbackAPIService.createFeedback(token, feedbackCreateRequest) // Pass token and request
    }

    // Get a feedback by its ID
    override fun getFeedback(token: String, feedbackId: Long): Call<GetFeedbackResponse> {
        return feedbackAPIService.getFeedback(token, feedbackId) // Correct the ID type to Long
    }
}
