package com.example.alp_vp.services

import com.example.alp_vp.models.GeneralModel
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPIService {
    @POST("api/logout")
    fun logout(@Header("X-API-TOKEN") token: String): Call<GeneralModel>
}
