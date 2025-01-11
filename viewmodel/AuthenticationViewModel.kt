package com.example.alp_vp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp.models.UserResponse
import com.example.alp_vp.repositories.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel(private val authenticationRepository: AuthenticationRepository) : ViewModel() {

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> = _userResponse

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Function to register user
    fun register(username: String, email: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    authenticationRepository.register(username, email, password).execute()
                }
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                } else {
                    _error.value = "Registration failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred during registration"
            } finally {
                _loading.value = false
            }
        }
    }

    // Function to login user
    fun login(email: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    authenticationRepository.login(email, password).execute()
                }
                if (response.isSuccessful) {
                    _userResponse.value = response.body()
                } else {
                    _error.value = "Login failed: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage ?: "An error occurred during login"
            } finally {
                _loading.value = false
            }
        }
    }
}
