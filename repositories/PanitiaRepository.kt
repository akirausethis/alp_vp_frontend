package com.example.alp_vp.repositories

import com.example.alp_vp.models.*
import com.example.alp_vp.services.PanitiaAPIService
import retrofit2.Call

interface PanitiaRepository {
    fun getAllPanitia(token: String): Call<GetAllPanitiaResponse>

    fun createPanitia(
        token: String,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String?
    ): Call<GeneralModel>

    fun getPanitia(token: String, panitiaId: Int): Call<GetPanitiaResponse>

    fun updatePanitia(
        token: String,
        panitiaId: Int,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String?
    ): Call<GeneralModel>

    fun deletePanitia(token: String, panitiaId: Int): Call<GeneralModel>
}

class NetworkPanitiaRepository(
    private val panitiaAPIService: PanitiaAPIService
) : PanitiaRepository {
    override fun getAllPanitia(token: String): Call<GetAllPanitiaResponse> {
        return panitiaAPIService.getAllPanitia(token)
    }

    override fun createPanitia(
        token: String,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String?
    ): Call<GeneralModel> {
        return panitiaAPIService.createPanitia(
            token,
            PanitiaCreateRequest(
                organisasi,
                title,
                description,
                startDate,
                poster
            )
        )
    }

    override fun getPanitia(token: String, panitiaId: Int): Call<GetPanitiaResponse> {
        return panitiaAPIService.getPanitia(token, panitiaId)
    }

    override fun updatePanitia(
        token: String,
        panitiaId: Int,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String?
    ): Call<GeneralModel> {
        return panitiaAPIService.updatePanitia(
            token,
            panitiaId,
            PanitiaUpdateRequest(
                panitiaId,
                organisasi,
                title,
                description,
                startDate,
                poster
            )
        )
    }

    override fun deletePanitia(token: String, panitiaId: Int): Call<GeneralModel> {
        return panitiaAPIService.deletePanitia(token, panitiaId)
    }
}
