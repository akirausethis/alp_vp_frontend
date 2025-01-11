package com.example.alp_vp.services

import com.example.alp_vp.models.*
import retrofit2.Call
import retrofit2.http.*

interface PanitiaAPIService {

    // Mendapatkan semua data Panitia
    @GET("api/panitia")
    fun getAllPanitia(
        @Header("X-API-TOKEN") token: String
    ): Call<GetAllPanitiaResponse>

    // Mendapatkan detail Panitia berdasarkan ID
    @GET("api/panitia/{id}")
    fun getPanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int
    ): Call<GetPanitiaResponse>

    // Membuat Panitia baru
    @POST("api/panitia")
    fun createPanitia(
        @Header("X-API-TOKEN") token: String,
        @Body panitiaCreateRequest: PanitiaCreateRequest
    ): Call<GeneralModel>

    // Memperbarui data Panitia berdasarkan ID
    @PUT("api/panitia/{id}")
    fun updatePanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int,
        @Body panitiaUpdateRequest: PanitiaUpdateRequest
    ): Call<GeneralModel>

    // Menghapus Panitia berdasarkan ID
    @DELETE("api/panitia/{id}")
    fun deletePanitia(
        @Header("X-API-TOKEN") token: String,
        @Path("id") panitiaId: Int
    ): Call<GeneralModel>
}
