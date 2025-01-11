package com.example.alp_vp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp.models.UserResponse
import com.example.alp_vp.repositories.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRegisterViewModel (private val repository: AuthenticationRepository) : ViewModel() {

    private val _response = MutableLiveData<UserResponse?>()
    val response: LiveData<UserResponse?> = _response

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.register(username, email, password).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        _response.postValue(response.body())
                    } else {
                        _error.postValue("Registration failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _error.postValue("Error: ${t.message}")
                }
            })
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(email, password).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        _response.postValue(response.body())
                    } else {
                        _error.postValue("Login failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _error.postValue("Error: ${t.message}")
                }
            })
        }
    }
}
