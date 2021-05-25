package com.idm.onepiecelist.core.data.source.remote

import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/search/anime?q=One%20Piece")
    suspend fun getList(): OnePieceResponse
}