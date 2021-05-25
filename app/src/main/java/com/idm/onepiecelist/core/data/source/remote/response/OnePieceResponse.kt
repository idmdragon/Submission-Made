package com.idm.onepiecelist.core.data.source.remote.response

data class OnePieceResponse(
    val last_page: Int,
    val results: List<OnePieceResultResponse>
)