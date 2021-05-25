package com.idm.onepiecelist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.vo.Resource

interface OnePieceUseCase {
    fun getAllItems(): LiveData<Resource<List<OnePiece>>>
    fun getFavoriteItems(): LiveData<List<OnePiece>>
    fun setFavoriteItems(items: OnePiece, state: Boolean)
}