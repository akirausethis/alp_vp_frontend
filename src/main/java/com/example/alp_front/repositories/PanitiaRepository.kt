package com.example.alp_front.repositories

import com.example.alp_front.models.GeneralResponseModel
import com.example.alp_front.models.GetAllPanitiaResponse
import com.example.alp_front.models.GetPanitiaResponse
import com.example.alp_front.models.PanitiaRequest
import com.example.alp_front.services.PanitiaAPIService
import retrofit2.Call

interface PanitiaRepository {
    fun getAllPanitias(token: String): Call<GetAllPanitiaResponse>

    fun createPanitia(token: String, organisasi: String, title: String, description: String, startDate: String, poster: String): Call<GeneralResponseModel>

    fun getPanitia(token: String, id: Int): Call<GetPanitiaResponse>

    fun updatePanitia(token: String, id: Int, organisasi: String, title: String, description: String, startDate: String, poster: String): Call<GeneralResponseModel>

    fun deletePanitia(token: String, id: Int): Call<GeneralResponseModel>
}

class NetworkPanitiaRepository(
    private val panitiaAPIService: PanitiaAPIService
): PanitiaRepository {

    override fun getAllPanitias(token: String): Call<GetAllPanitiaResponse> {
        return panitiaAPIService.getAllPanitia(token)
    }

    override fun createPanitia(
        token: String,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String
    ): Call<GeneralResponseModel> {
        return panitiaAPIService.createPanitia(
            token,
            PanitiaRequest(organisasi, title, description, startDate, poster)
        )
    }

    override fun getPanitia(token: String, id: Int): Call<GetPanitiaResponse> {
        return panitiaAPIService.getPanitia(token, id)
    }

    override fun updatePanitia(
        token: String,
        id: Int,
        organisasi: String,
        title: String,
        description: String,
        startDate: String,
        poster: String
    ): Call<GeneralResponseModel> {
        return panitiaAPIService.updatePanitia(
            token,
            id,
            PanitiaRequest(organisasi, title, description, startDate, poster)
        )
    }

    override fun deletePanitia(token: String, id: Int): Call<GeneralResponseModel> {
        return panitiaAPIService.deletePanitia(token, id)
    }
}
