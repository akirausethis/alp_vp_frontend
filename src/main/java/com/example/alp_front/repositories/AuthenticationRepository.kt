package com.example.alp_front.repositories

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.alp_front.models.UserModel
import com.example.alp_front.models.UserResponse
import com.example.alp_front.services.AuthenticationAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// In general a repository class:
//
//1. Exposes data to the rest of the app.
//2. Centralizes changes to data.
//3. Resolves conflicts between multiple data sources.
//4. Abstracts sources of data from the rest of the app.
//5. Contains business logic.

interface AuthenticationRepository {
    fun register(username: String, email: String, password: String): Call<UserResponse>

    fun login(email: String, password: String): Call<UserResponse>
}

class NetworkAuthenticationRepository(
    private val authenticationAPIService: AuthenticationAPIService
): AuthenticationRepository {
    override fun register(username: String, email: String, password: String): Call<UserResponse> {
        var registerMap = HashMap<String, String>()

        registerMap["username"] = username
        registerMap["email"] = email
        registerMap["password"] = password

        return authenticationAPIService.register(registerMap)
    }

    override fun login(email: String, password: String): Call<UserResponse> {
        var loginMap = HashMap<String, String>()

        loginMap["email"] = email
        loginMap["password"] = password

        return authenticationAPIService.login(loginMap)
    }
}