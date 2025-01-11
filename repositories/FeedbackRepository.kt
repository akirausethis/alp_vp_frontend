package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.FeedbackAPIService
import retrofit2.Call

interface FeedbackRepository {

    fun getAllFeedback(token: String): Call<GetAllFeedbackResponse>

    fun createFeedback(
        token: String,
        feedbackCreateRequest: FeedbackCreateRequest
    ): Call<GeneralModel>

    fun getFeedback(token: String, feedbackId: Long): Call<GetFeedbackResponse>
}

class NetworkFeedbackRepository(
    private val feedbackAPIService: FeedbackAPIService
) : FeedbackRepository {

    override fun getAllFeedback(token: String): Call<GetAllFeedbackResponse> {
        return feedbackAPIService.getAllFeedback(token)
    }

    override fun createFeedback(
        token: String,
        feedbackCreateRequest: FeedbackCreateRequest
    ): Call<GeneralModel> {
        return feedbackAPIService.createFeedback(token, feedbackCreateRequest)
    }

    override fun getFeedback(token: String, feedbackId: Long): Call<GetFeedbackResponse> {
        return feedbackAPIService.getFeedback(token, feedbackId)
    }
}
