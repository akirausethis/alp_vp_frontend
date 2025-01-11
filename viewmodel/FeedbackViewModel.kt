package com.example.alp_vp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp.models.FeedbackCreateRequest
import com.example.alp_vp.models.FeedbackResponse
import com.example.alp_vp.models.GeneralModel
import com.example.alp_vp.repositories.FeedbackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedbackViewModel(private val feedbackRepository: FeedbackRepository) : ViewModel() {

    private val _feedbackList = MutableLiveData<List<FeedbackResponse>>()
    val feedbackList: LiveData<List<FeedbackResponse>> = _feedbackList

    private val _feedbackDetail = MutableLiveData<FeedbackResponse>()
    val feedbackDetail: LiveData<FeedbackResponse> = _feedbackDetail

    private val _feedbackCreationResponse = MutableLiveData<GeneralModel>()
    val feedbackCreationResponse: LiveData<GeneralModel> = _feedbackCreationResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Function to fetch all feedbacks
    fun getAllFeedback(token: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedbackRepository.getAllFeedback(token).execute()
                }
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _feedbackList.value = it.map { feedback ->
                            FeedbackResponse(
                                id = feedback.id,
                                feedback = feedback.feedback,
                                userId = feedback.userId,
                                questionId = feedback.questionId
                            )
                        }
                    }
                } else {
                    _error.value = "Failed to load feedback list"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred while fetching feedback"
            } finally {
                _loading.value = false
            }
        }
    }

    // Function to fetch a specific feedback by ID
    fun getFeedback(token: String, feedbackId: Long) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedbackRepository.getFeedback(token, feedbackId).execute()
                }
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _feedbackDetail.value = FeedbackResponse(
                            id = it.id,
                            feedback = it.feedback,
                            userId = it.userId,
                            questionId = it.questionId
                        )
                    }
                } else {
                    _error.value = "Failed to load feedback detail"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred while fetching feedback detail"
            } finally {
                _loading.value = false
            }
        }
    }

    // Function to create new feedback
    fun createFeedback(token: String, feedbackCreateRequest: FeedbackCreateRequest) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedbackRepository.createFeedback(token, feedbackCreateRequest).execute()
                }
                if (response.isSuccessful) {
                    _feedbackCreationResponse.value = response.body()
                } else {
                    _error.value = "Failed to create feedback"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred while creating feedback"
            } finally {
                _loading.value = false
            }
        }
    }
}
