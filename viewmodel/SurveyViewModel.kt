package com.example.alp_vp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.alp_vp.models.*
import com.example.alp_vp.repositories.SurveyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SurveyViewModel(private val surveyRepository: SurveyRepository) : ViewModel() {

    fun getAllSurveys(token: String) = liveData(Dispatchers.IO) {
        emit(SurveyResource.Loading(true))
        try {
            // Using suspending function to handle Retrofit's callback
            val response = getResponse { surveyRepository.getAllSurvey(token).enqueue(it) }

            if (response.isSuccessful) {
                emit(SurveyResource.Success(response.body()?.data))
            } else {
                emit(SurveyResource.Error("Failed to load surveys"))
            }
        } catch (exception: Exception) {
            emit(SurveyResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(SurveyResource.Loading(false))
        }
    }

    fun createSurvey(token: String, surveyCreateRequest: SurveyCreateRequest) = liveData(Dispatchers.IO) {
        emit(SurveyResource.Loading(true))
        try {
            val response = getResponse { surveyRepository.createSurvey(token, surveyCreateRequest).enqueue(it) }

            if (response.isSuccessful) {
                emit(SurveyResource.Success(response.body()))
            } else {
                emit(SurveyResource.Error("Failed to create survey"))
            }
        } catch (exception: Exception) {
            emit(SurveyResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(SurveyResource.Loading(false))
        }
    }

    fun getSurvey(token: String, surveyId: Long) = liveData(Dispatchers.IO) {
        emit(SurveyResource.Loading(true))
        try {
            // Using suspending function to handle Retrofit's callback
            val response = getResponse { surveyRepository.getSurvey(token, surveyId).enqueue(it) }

            if (response.isSuccessful) {
                emit(SurveyResource.Success(response.body()?.data))
            } else {
                emit(SurveyResource.Error("Failed to load survey"))
            }
        } catch (exception: Exception) {
            emit(SurveyResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(SurveyResource.Loading(false))
        }
    }

    private suspend fun <T> getResponse(call: (Callback<T>) -> Unit): Response<T> {
        return suspendCancellableCoroutine { continuation ->
            val callback = object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (continuation.isActive) {
                        continuation.resume(response)
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(t)
                    }
                }
            }
            call(callback)
        }
    }
}

sealed class SurveyResource<T> {
    data class Success<T>(val data: T?) : SurveyResource<T>()
    data class Error<T>(val message: String) : SurveyResource<T>()
    data class Loading<T>(val isLoading: Boolean = true) : SurveyResource<T>()
}
