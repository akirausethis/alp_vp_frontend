package com.example.alp_vp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alp_vp.repositories.AuthenticationRepository
import com.example.alp_vp.view.LoginRegisterViewModel

class LoginRegisterViewModelFactory(
    private val repository: AuthenticationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginRegisterViewModel::class.java)) {
            return LoginRegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
