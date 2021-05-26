package com.idm.onepiecelist.detail

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase

class DetailViewModel(private val onePieceUseCase: OnePieceUseCase):ViewModel() {
    fun setFavoriteItem(items: OnePiece, newStatus:Boolean) = onePieceUseCase.setFavoriteItems(items, newStatus)
}