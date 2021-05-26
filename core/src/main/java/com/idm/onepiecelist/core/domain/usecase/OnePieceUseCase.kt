package com.idm.onepiecelist.core.domain.usecase

import com.idm.onepiecelist.core.source.Resource
import com.idm.onepiecelist.core.domain.model.OnePiece
import kotlinx.coroutines.flow.Flow

interface OnePieceUseCase {
    fun getAllItems(): Flow<Resource<List<OnePiece>>>
    fun getFavoriteItems(): Flow<List<OnePiece>>
    fun setFavoriteItems(items: OnePiece, state: Boolean)
}