package com.idm.onepiecelist.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "one_piece")
data class OnePieceEntity(
    val airing: Boolean,
    val episodes: Int,
    val image_url: String,
    @PrimaryKey
    val mal_id: Int,
    val members: Int,
    val rated: String,
    val score: Double,
    val start_date: String,
    val synopsis: String,
    val title: String,
    val type: String,
    var isFavorite: Boolean = false
) : Parcelable