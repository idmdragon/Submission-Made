package com.idm.onepiecelist.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnePiece(
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
    val isFavorite: Boolean
) : Parcelable