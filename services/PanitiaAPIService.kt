package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface PanitiaAPIService {

    @GET("api/panitia")
    fun getAllPanitia(
        @Header("X-API-TOKEN") token: String
    ): Call<GetAllPanitiaResponse>

    @GET("api/panitia/{id}")
    fun getPanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int
    ): Call<GetPanitiaResponse>

    @POST("api/panitia")
    fun createPanitia(
        @Header("X-API-TOKEN") token: String,
        @Body panitiaCreateRequest: PanitiaCreateRequest
    ): Call<GeneralModel>

    @PUT("api/panitia/{id}")
    fun updatePanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int,
        @Body panitiaUpdateRequest: PanitiaUpdateRequest
    ): Call<GeneralModel>

    @DELETE("api/panitia/{id}")
    fun deletePanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int
    ): Call<GeneralModel>
}
