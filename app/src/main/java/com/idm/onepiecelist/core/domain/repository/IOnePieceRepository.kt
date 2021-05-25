package com.idm.onepiecelist.core.domain.repository

import com.idm.onepiecelist.core.data.source.Resource
import com.idm.onepiecelist.core.domain.model.OnePiece
import kotlinx.coroutines.flow.Flow

interface IOnePieceRepository {

    fun getAllItems(): Flow<Resource<List<OnePiece>>>
    fun getFavoriteItems(): Flow<List<OnePiece>>
    fun setFavoriteItems(items: OnePiece, state: Boolean)
}