package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.UserAPIService
import retrofit2.Call
import retrofit2.Response

interface UserRepository {
    suspend fun registerUser(registerRequest: RegisterUserRequest): Response<UserResponse>
    suspend fun loginUser(loginRequest: LoginUserRequest): Response<UserResponse>
    suspend fun getUserProfile(token: String): Response<User>
}

class NetworkUserRepository(
    private val userAPIService: UserAPIService
) : UserRepository {

    override suspend fun registerUser(registerRequest: RegisterUserRequest): Response<UserResponse> {
        // Menggunakan Retrofit untuk mengirim permintaan registrasi ke server
        return userAPIService.registerUser(registerRequest)
    }

    override suspend fun loginUser(loginRequest: LoginUserRequest): Response<UserResponse> {
        // Menggunakan Retrofit untuk mengirim permintaan login ke server
        return userAPIService.loginUser(loginRequest)
    }

    override suspend fun getUserProfile(token: String): Response<User> {
        // Menggunakan Retrofit untuk mendapatkan profil pengguna berdasarkan token
        return userAPIService.getUserProfile("Bearer $token")
    }
}
