package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.QuestionAPIService
import retrofit2.Call

interface QuestionRepository {

    fun getAllQuestions(token: String, surveyId: Int): Call<GetAllQuestionResponse>

    fun createQuestion(
        token: String,
        questionCreateRequest: QuestionCreateRequest
    ): Call<GeneralModel>

    fun updateQuestion(
        token: String,
        questionId: Int,
        questionUpdateRequest: QuestionUpdateRequest
    ): Call<GeneralModel>

    fun getQuestion(token: String, questionId: Int): Call<GetQuestionResponse>
}

class NetworkQuestionRepository(
    private val questionAPIService: QuestionAPIService
) : QuestionRepository {

    override fun getAllQuestions(token: String, surveyId: Int): Call<GetAllQuestionResponse> {
        return questionAPIService.getAllQuestions(token, surveyId)
    }

    override fun createQuestion(
        token: String,
        questionCreateRequest: QuestionCreateRequest
    ): Call<GeneralModel> {
        return questionAPIService.createQuestion(token, questionCreateRequest)
    }

    override fun updateQuestion(
        token: String,
        questionId: Int,
        questionUpdateRequest: QuestionUpdateRequest
    ): Call<GeneralModel> {
        return questionAPIService.updateQuestion(token, questionId, questionUpdateRequest)
    }

    override fun getQuestion(token: String, questionId: Int): Call<GetQuestionResponse> {
        return questionAPIService.getQuestion(token, questionId)
    }
}
