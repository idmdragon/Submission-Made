package com.idm.onepiecelist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.idm.onepiecelist.core.data.source.Resource
import com.idm.onepiecelist.core.domain.model.OnePiece
import kotlinx.coroutines.flow.Flow

interface OnePieceUseCase {
    fun getAllItems(): Flow<Resource<List<OnePiece>>>
    fun getFavoriteItems(): Flow<List<OnePiece>>
    fun setFavoriteItems(items: OnePiece, state: Boolean)
}