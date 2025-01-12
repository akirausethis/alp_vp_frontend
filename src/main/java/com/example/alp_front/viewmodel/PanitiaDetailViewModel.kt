package com.example.alp_front.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.example.alp_front.models.GetPanitiaResponse
import com.example.alp_front.repositories.PanitiaRepository
import com.example.alp_front.uiStates.StringDataStatusUIState
import com.example.alp_front.uiStates.PanitiaDetailDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PanitiaDetailViewModel(
    private val panitiaRepository: PanitiaRepository
): ViewModel() {
    var dataStatus: PanitiaDetailDataStatusUIState by mutableStateOf(PanitiaDetailDataStatusUIState.Start)
        private set

    var deleteStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

    fun getPanitia(token: String, panitiaId: Int, navController: NavHostController, isUpdating: Boolean) {
        viewModelScope.launch {
            dataStatus = PanitiaDetailDataStatusUIState.Loading

            try {
                val call = panitiaRepository.getPanitia(token, panitiaId)

                call.enqueue(object: Callback<GetPanitiaResponse> {
                    override fun onResponse(call: Call<GetPanitiaResponse>, res: Response<GetPanitiaResponse>) {
                        if (res.isSuccessful) {
                            dataStatus = PanitiaDetailDataStatusUIState.Success(res.body()!!.data)

                            Log.d("get-panitia-result", "GET PANITIA: ${res.body()}")

                            if (isUpdating) {
                                navController.popBackStack()
                            } else {
                                navController.navigate(PagesEnum.PanitiaDetail.name) {
                                    popUpTo(PagesEnum.Home.name) {
                                        inclusive = false
                                    }
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            dataStatus = PanitiaDetailDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GetPanitiaResponse>, t: Throwable) {
                        dataStatus = PanitiaDetailDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                dataStatus = PanitiaDetailDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    fun deletePanitia(token: String, id: Int, navController: NavHostController) {
        viewModelScope.launch {
            deleteStatus = StringDataStatusUIState.Loading

            try {
                val call = panitiaRepository.deletePanitia(token, id)

                call.enqueue(object: Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful) {
                            deleteStatus = StringDataStatusUIState.Success(res.body()!!.data)

                            Log.d("delete-status", "Delete status: ${res.body()!!.data}")

                            navController.popBackStack()
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            deleteStatus = StringDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        deleteStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                deleteStatus = StringDataStatusUIState.Failed(error.localizedMessage)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ALPFRONTApplication)
                val panitiaRepository = application.container.panitiaRepository
                PanitiaDetailViewModel(panitiaRepository)
            }
        }
    }

    fun clearErrorMessage() {
        deleteStatus = StringDataStatusUIState.Start
    }
}
