package com.idm.onepiecelist.core.utils

import androidx.room.PrimaryKey
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.data.source.remote.response.OnePieceResponse


object DataMapper {
    fun mapResponsesToEntities(input: OnePieceResponse): List<OnePieceEntity> {
        val itemList = ArrayList<OnePieceEntity>()
        input.results.map {
          val item =   OnePieceEntity(
                airing = it.airing,
                episodes = it.episodes,
                image_url = it.image_url,
                mal_id = it.mal_id,
                members = it.members,
                rated = it.rated,
                score = it.score,
                start_date = it.start_date,
                synopsis = it.synopsis,
                title = it.title,
                type = it.type,
                isFavorite = it.airing
            )
            itemList.add(item)
        }
        return itemList
    }
}