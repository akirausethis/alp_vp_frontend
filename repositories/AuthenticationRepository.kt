package com.example.alp_vp.repositories

import com.example.alp_vp.models.UserResponse
import com.example.alp_vp.services.AuthenticationAPIService
import retrofit2.Call

interface AuthenticationRepository {
    fun register(username: String, email: String, password: String): Call<UserResponse>
    fun login(email: String, password: String): Call<UserResponse>
}

class NetworkAuthRepository(
    private val authAPIService: AuthenticationAPIService
) : AuthenticationRepository {
    override fun register(username: String, email: String, password: String): Call<UserResponse> {
        val registerMap = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to password
        )
        return authAPIService.register(registerMap)
    }

    override fun login(email: String, password: String): Call<UserResponse> {
        val loginMap = hashMapOf(
            "email" to email,
            "password" to password
        )
        return authAPIService.login(loginMap)
    }
}
