package com.example.alp_front.viewmodel

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.alp_front.ALPFRONTApplication
import com.example.alp_front.enums.PagesEnum
import com.example.alp_front.models.ErrorModel
import com.example.alp_front.models.GeneralResponseModel
import com.example.alp_front.models.PanitiaModel
import com.example.alp_front.repositories.PanitiaRepository
import com.example.alp_front.repositories.UserRepository
import com.example.alp_front.uiStates.PanitiaFormUIState
import com.example.alp_front.uiStates.StringDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Calendar
import java.util.Date


class PanitiaFormViewModel(
    private val panitiaRepository: PanitiaRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _panitiaFormUIState = MutableStateFlow(PanitiaFormUIState())

    val panitiaFormUIState: StateFlow<PanitiaFormUIState>
        get() {
            return _panitiaFormUIState.asStateFlow()
        }

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    var id by mutableStateOf(-1)
        private set

    var isUpdate by mutableStateOf(false)
        private set

    var submissionStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

    var titleInput by mutableStateOf("")
        private set

    var organisasiInput by mutableStateOf("")
        private set

    var descriptionInput by mutableStateOf("")
        private set

    var startDateInput by mutableStateOf("")
        private set

    var posterInput by mutableStateOf("")
        private set

    fun changeTitleInput(title: String) {
        titleInput = title
    }

    fun changeDescriptionInput(description: String) {
        descriptionInput = description
    }

    fun changeOrganisasiInput(organisasi: String) {
        organisasiInput = organisasi
    }


    fun changeStartDateInput(startDate: String) {
        startDateInput = startDate
    }

    fun changePosterInput(poster: String) {
        posterInput = poster
    }

    fun showDatePickerDialog(datePickerDialog: DatePickerDialog) {
        datePickerDialog.show()
    }

    fun initDatePickerDialog(context: Context): DatePickerDialog {
        // Initializing a Calendar
        val datePickerCalendar = Calendar.getInstance()

        // Fetching current year, month and day
        val calYear = datePickerCalendar.get(Calendar.YEAR)
        val calMonth = datePickerCalendar.get(Calendar.MONTH)
        val calDay = datePickerCalendar.get(Calendar.DAY_OF_MONTH)

        datePickerCalendar.time = Date()

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, calYear: Int, calMonth: Int, calDay: Int ->
                startDateInput = "$calDay/${calMonth + 1}/$calYear"
                checkNullFormValues()
            }, calYear, calMonth, calDay
        )

        return datePickerDialog
    }

    fun checkNullFormValues() {
        if (titleInput.isNotEmpty() && descriptionInput.isNotEmpty() && startDateInput.isNotEmpty() && posterInput.isNotEmpty()) {
            _panitiaFormUIState.update { currentState ->
                currentState.copy(
                    saveButtonEnabled = true
                )
            }
        } else {
            _panitiaFormUIState.update { currentState ->
                currentState.copy(
                    saveButtonEnabled = false
                )
            }
        }
    }

    fun changeSaveButtonColor(): Color {
        if (_panitiaFormUIState.value.saveButtonEnabled) {
            return Color.Blue
        }
        return Color.LightGray
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ALPFRONTApplication)
                val panitiaRepository = application.container.panitiaRepository
                val userRepository = application.container.userRepository
                PanitiaFormViewModel(panitiaRepository, userRepository)
            }
        }
    }


    fun createPanitia(navController: NavHostController, token: String) {
        viewModelScope.launch {
            submissionStatus = StringDataStatusUIState.Loading

            Log.d("token-panitia-form", "TOKEN: $token")

            try {
                val call = panitiaRepository.createPanitia(
                    token = token,
                    organisasi = organisasiInput,
                    title = titleInput,
                    description = descriptionInput,
                    startDate = startDateInput,
                    poster = posterInput
                )

                call.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful && res.body() != null) {
                            Log.d("json", "JSON RESPONSE: ${res.body()!!.data}")
                            submissionStatus = StringDataStatusUIState.Success(res.body()!!.data)

                            resetViewModel()

                            navController.navigate(PagesEnum.Home.name) {
                                popUpTo(PagesEnum.CreatePanitia.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            // Logging raw error body
                            val rawErrorBody = res.errorBody()?.string()
                            Log.e("Error Body", "Raw Error Body: $rawErrorBody")

                            // Handling error parsing
                            val errorText = if (rawErrorBody?.startsWith("{") == true) {
                                try {
                                    val errorModel = Gson().fromJson(rawErrorBody, ErrorModel::class.java)
                                    errorModel.errors ?: "Unknown error occurred"
                                } catch (e: Exception) {
                                    Log.e("Parsing Error", "Failed to parse error body: ${e.localizedMessage}")
                                    "Unknown error occurred"
                                }
                            } else {
                                rawErrorBody ?: "Unknown error occurred"
                            }

                            submissionStatus = StringDataStatusUIState.Failed(errorText)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        Log.e("Network Error", "onFailure: ${t.localizedMessage}")
                        submissionStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                Log.e("IO Error", "Exception: ${error.localizedMessage}")
                submissionStatus = StringDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }


    fun clearErrorMessage() {
        submissionStatus = StringDataStatusUIState.Start
    }


    fun updatePanitia(token: String, getPanitia: () -> Unit) {
        viewModelScope.launch {
            submissionStatus = StringDataStatusUIState.Loading

            try {
                val call = panitiaRepository.updatePanitia(token, id, organisasiInput, titleInput, descriptionInput, startDateInput, posterInput)

                call.enqueue(object: Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful) {
                            submissionStatus = StringDataStatusUIState.Success(res.body()!!.data)

                            resetViewModel()

                            getPanitia()
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            submissionStatus = StringDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        submissionStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                submissionStatus = StringDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun navigateToUpdateForm(navController: NavHostController, panitiaModel: PanitiaModel) {
        organisasiInput = panitiaModel.organisasi
        titleInput = panitiaModel.title
        descriptionInput = panitiaModel.description
        startDateInput = panitiaModel.start_Date
        posterInput = panitiaModel.poster

        isUpdate = true

        navController.navigate(PagesEnum.CreatePanitia.name) {
            popUpTo(PagesEnum.PanitiaDetail.name) {
                inclusive = false
            }
        }
    }

    fun resetViewModel() {
        submissionStatus = StringDataStatusUIState.Start
        organisasiInput = ""
        titleInput = ""
        descriptionInput = ""
        startDateInput = ""
        posterInput = ""
        isUpdate = false

        _panitiaFormUIState.update { state ->
            state.copy(
                saveButtonEnabled = false
            )
        }
    }


}
