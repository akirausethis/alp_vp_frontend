package com.example.alp_front.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_front.ALPFRONTApplication
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.enums.PrioritiesEnum
import com.example.alp_front.models.ErrorModel
import com.example.alp_front.models.GeneralResponseModel
import com.example.alp_front.models.GetAllPanitiaResponse
import com.example.alp_front.repositories.PanitiaRepository
import com.example.alp_front.repositories.UserRepository
import com.example.alp_front.uiStates.HomeUIState
import com.example.alp_front.uiStates.StringDataStatusUIState
import com.example.alp_front.uiStates.PanitiaDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    private val userRepository: UserRepository,
    private val panitiaRepository: PanitiaRepository
) : ViewModel() {

    private val _homeUIState = MutableStateFlow(HomeUIState())

    var logoutStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

    var dataStatus: PanitiaDataStatusUIState by mutableStateOf(PanitiaDataStatusUIState.Start)
        private set

    val username: StateFlow<String> = userRepository.currentUsername.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun clearDialog() {
        _homeUIState.update { state ->
            state.copy(
                showDialog = false
            )
        }
    }

    fun changePriorityTextBackgroundColor(priority: PrioritiesEnum): androidx.compose.ui.graphics.Color {
        return when (priority) {
            PrioritiesEnum.High -> androidx.compose.ui.graphics.Color.Red
            PrioritiesEnum.Medium -> androidx.compose.ui.graphics.Color.Yellow
            else -> androidx.compose.ui.graphics.Color.Green
        }
    }

    fun logoutUser(token: String, navController: androidx.navigation.NavHostController) {
        viewModelScope.launch {
            logoutStatus = StringDataStatusUIState.Loading

            try {
                val call = userRepository.logout(token)
                call.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(call: Call<GeneralResponseModel>, res: Response<GeneralResponseModel>) {
                        if (res.isSuccessful && res.body() != null) {
                            logoutStatus = StringDataStatusUIState.Success(data = res.body()!!.data)

                            saveUsernameToken("Unknown", "Unknown")

                            navController.navigate(PagesEnum.Login.name) {
                                popUpTo(PagesEnum.Home.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            val errorMessage: ErrorModel? = try {
                                Gson().fromJson(res.errorBody()?.charStream(), ErrorModel::class.java)
                            } catch (e: Exception) {
                                Log.e("Error Parsing", "Failed to parse error body: ${e.localizedMessage}")
                                null
                            }
                            val errorText = errorMessage?.errors ?: "Unknown error occurred"
                            logoutStatus = StringDataStatusUIState.Failed(errorText)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        logoutStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                logoutStatus = StringDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun getAllPanitias(token: String) {
        viewModelScope.launch {
            dataStatus = PanitiaDataStatusUIState.Loading
            try {
                val call = panitiaRepository.getAllPanitias(token)
                call.enqueue(object : Callback<GetAllPanitiaResponse> {
                    override fun onResponse(call: Call<GetAllPanitiaResponse>, res: Response<GetAllPanitiaResponse>) {
                        val responseBody = res.errorBody()?.string() ?: res.body()?.toString()
                        Log.d("API Response", "Response Body: $responseBody")

                        if (res.isSuccessful && res.body() != null) {
                            dataStatus = PanitiaDataStatusUIState.Success(res.body()!!.data)
                        } else {
                            val errorMessage: ErrorModel? = try {
                                Gson().fromJson(res.errorBody()?.charStream(), ErrorModel::class.java)
                            } catch (e: Exception) {
                                Log.e("Error Parsing", "Failed to parse error body: ${e.localizedMessage}")
                                null
                            }

                            val errorText = errorMessage?.errors ?: "Unknown error occurred"
                            Log.e("API Error", "Error Message: $errorText")
                            dataStatus = PanitiaDataStatusUIState.Failed(errorText)
                        }
                    }

                    override fun onFailure(call: Call<GetAllPanitiaResponse>, t: Throwable) {
                        dataStatus = PanitiaDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = PanitiaDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun createPanitia(
        token: String,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String
    ) {
        viewModelScope.launch {
            try {
                val call = panitiaRepository.createPanitia(token, organisasi, title, description, startDate, poster)
                call.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(call: Call<GeneralResponseModel>, res: Response<GeneralResponseModel>) {
                        if (res.isSuccessful) {
                            // Handle success
                        } else {
                            val errorMessage: ErrorModel? = try {
                                Gson().fromJson(res.errorBody()?.charStream(), ErrorModel::class.java)
                            } catch (e: Exception) {
                                Log.e("Error Parsing", "Failed to parse error body: ${e.localizedMessage}")
                                null
                            }
                            val errorText = errorMessage?.errors ?: "Unknown error occurred"
                            dataStatus = PanitiaDataStatusUIState.Failed(errorText)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        dataStatus = PanitiaDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = PanitiaDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ALPFRONTApplication)
                val userRepository = application.container.userRepository
                val panitiaRepository = application.container.panitiaRepository
                HomeViewModel(userRepository, panitiaRepository)
            }
        }
    }

    fun saveUsernameToken(token: String, username: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
        }
    }

    fun clearLogoutErrorMessage() {
        logoutStatus = StringDataStatusUIState.Start
    }

    fun clearDataErrorMessage() {
        dataStatus = PanitiaDataStatusUIState.Start
    }
}
