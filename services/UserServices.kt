package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPIService {
    @POST("/api/register")
    suspend fun registerUser(@Body registerRequest: RegisterUserRequest): Response<UserResponse>

    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginUserRequest): Response<UserResponse>

    @GET("/api/user/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<User>
}
