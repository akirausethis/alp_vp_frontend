package com.example.alp_front.uiStates

import com.example.alp_front.models.PanitiaModel


sealed interface PanitiaDetailDataStatusUIState {
    data class Success(val data: PanitiaModel): PanitiaDetailDataStatusUIState
    object Loading: PanitiaDetailDataStatusUIState
    object Start: PanitiaDetailDataStatusUIState
    data class Failed(val errorMessage: String): PanitiaDetailDataStatusUIState
}