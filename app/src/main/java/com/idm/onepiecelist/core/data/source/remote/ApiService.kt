package com.idm.onepiecelist.core.data.source.remote

import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v3/search/anime?q=One%20Piece")
    suspend fun getList(
    ): Response<OnePieceResponse>
}