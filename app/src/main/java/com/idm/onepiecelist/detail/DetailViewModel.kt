package com.idm.onepiecelist.detail

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val onePieceUseCase: OnePieceUseCase
) : ViewModel() {
    fun setFavoriteItem(items: OnePiece, newStatus:Boolean) = onePieceUseCase.setFavoriteItems(items, newStatus)
}