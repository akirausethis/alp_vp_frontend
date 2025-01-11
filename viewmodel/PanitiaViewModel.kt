package com.example.alp_vp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp.models.*
import com.example.alp_vp.repositories.PanitiaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PanitiaViewModel(private val panitiaRepository: PanitiaRepository) : ViewModel() {

    private val _panitiaList = MutableLiveData<List<PanitiaResponse>>()
    val panitiaList: LiveData<List<PanitiaResponse>> = _panitiaList

    private val _panitiaDetail = MutableLiveData<PanitiaResponse>()
    val panitiaDetail: LiveData<PanitiaResponse> = _panitiaDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getAllPanitia(token: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    panitiaRepository.getAllPanitia(token).execute()
                }
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _panitiaList.value = it.map { panitia ->
                            PanitiaResponse(
                                id = panitia.id.toInt(),
                                organisasi = panitia.organisasi,
                                title = panitia.title,
                                description = panitia.description,
                                startDate = panitia.startDate,
                                poster = panitia.poster
                            )
                        }
                    }
                } else {
                    _error.value = "Failed to load Panitia list"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred"
            } finally {
                _loading.value = false
            }
        }
    }

    fun getPanitiaDetail(token: String, panitiaId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    panitiaRepository.getPanitia(token, panitiaId).execute()
                }
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        _panitiaDetail.value = PanitiaResponse(
                            id = it.id.toInt(),
                            organisasi = it.organisasi,
                            title = it.title,
                            description = it.description,
                            startDate = it.startDate,
                            poster = it.poster
                        )
                    }
                } else {
                    _error.value = "Failed to load Panitia detail"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred"
            } finally {
                _loading.value = false
            }
        }
    }
}
