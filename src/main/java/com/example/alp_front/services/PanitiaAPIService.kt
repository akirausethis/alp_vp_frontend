package com.example.alp_front.services

import com.example.alp_front.models.GeneralResponseModel
import com.example.alp_front.models.GetAllPanitiaResponse
import com.example.alp_front.models.GetPanitiaResponse
import com.example.alp_front.models.PanitiaModel
import com.example.alp_front.models.PanitiaRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PanitiaAPIService {
    @GET("api/panitia")
    fun getAllPanitia(@Header("X-API-TOKEN") token: String): Call<GetAllPanitiaResponse>

    @GET("api/panitia/{id}")
    fun getPanitia(@Header("X-API-TOKEN") token: String, @Path("id") id: Int): Call<GetPanitiaResponse>

    @POST("api/panitia")
    fun createPanitia(@Header("X-API-TOKEN") token: String, @Body PanitiaModel: PanitiaRequest): Call<GeneralResponseModel>

    @PUT("api/panitia/{id}")
    fun updatePanitia(@Header("X-API-TOKEN") token: String, @Path("id") id: Int, @Body PanitiaModel: PanitiaRequest): Call<GeneralResponseModel>

    @DELETE("api/panitia/{id}")
    fun deletePanitia(@Header("X-API-TOKEN") token: String, @Path("id") id: Int): Call<GeneralResponseModel>
}