package com.idm.onepiecelist.core.source.remote.response

data class OnePieceResultResponse(
    val airing: Boolean,
    val episodes: Int,
    val image_url: String,
    val mal_id: Int,
    val members: Int,
    val rated: String,
    val score: Double,
    val start_date: String,
    val synopsis: String,
    val title: String,
    val type: String,
)