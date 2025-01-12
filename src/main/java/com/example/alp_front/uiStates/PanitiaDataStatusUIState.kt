package com.example.alp_front.uiStates

import com.example.alp_front.models.PanitiaModel

sealed interface PanitiaDataStatusUIState {
    data class Success(val data: List<PanitiaModel>): PanitiaDataStatusUIState
    object Start: PanitiaDataStatusUIState
    object Loading: PanitiaDataStatusUIState
    data class Failed(val errorMessage:String): PanitiaDataStatusUIState
}