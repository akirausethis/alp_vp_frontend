package com.example.alp_vp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.alp_vp.models.*
import com.example.alp_vp.repositories.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class QuestionViewModel(private val questionRepository: QuestionRepository) : ViewModel() {

    fun getAllQuestions(token: String, surveyId: Int) = liveData(Dispatchers.IO) {
        emit(QuestionResource.Loading(true))
        try {
            val response = getResponse { questionRepository.getAllQuestions(token, surveyId).enqueue(it) }

            if (response.isSuccessful) {
                emit(QuestionResource.Success(response.body()?.data))
            } else {
                emit(QuestionResource.Error("Failed to load questions"))
            }
        } catch (exception: Exception) {
            emit(QuestionResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(QuestionResource.Loading(false))
        }
    }

    fun createQuestion(token: String, questionCreateRequest: QuestionCreateRequest) = liveData(Dispatchers.IO) {
        emit(QuestionResource.Loading(true))
        try {
            val response = getResponse { questionRepository.createQuestion(token, questionCreateRequest).enqueue(it) }

            if (response.isSuccessful) {
                emit(QuestionResource.Success(response.body()))
            } else {
                emit(QuestionResource.Error("Failed to create question"))
            }
        } catch (exception: Exception) {
            emit(QuestionResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(QuestionResource.Loading(false))
        }
    }

    fun updateQuestion(token: String, questionId: Int, questionUpdateRequest: QuestionUpdateRequest) = liveData(Dispatchers.IO) {
        emit(QuestionResource.Loading(true))
        try {
            val response = getResponse { questionRepository.updateQuestion(token, questionId, questionUpdateRequest).enqueue(it) }

            if (response.isSuccessful) {
                emit(QuestionResource.Success(response.body()))
            } else {
                emit(QuestionResource.Error("Failed to update question"))
            }
        } catch (exception: Exception) {
            emit(QuestionResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(QuestionResource.Loading(false))
        }
    }

    fun getQuestion(token: String, questionId: Int) = liveData(Dispatchers.IO) {
        emit(QuestionResource.Loading(true))
        try {
            val response = getResponse { questionRepository.getQuestion(token, questionId).enqueue(it) }

            if (response.isSuccessful) {
                emit(QuestionResource.Success(response.body()?.data))
            } else {
                emit(QuestionResource.Error("Failed to load question"))
            }
        } catch (exception: Exception) {
            emit(QuestionResource.Error("Something went wrong: ${exception.localizedMessage}"))
        } finally {
            emit(QuestionResource.Loading(false))
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

sealed class QuestionResource<T> {
    data class Success<T>(val data: T?) : QuestionResource<T>()
    data class Error<T>(val message: String) : QuestionResource<T>()
    data class Loading<T>(val isLoading: Boolean = true) : QuestionResource<T>()
}
