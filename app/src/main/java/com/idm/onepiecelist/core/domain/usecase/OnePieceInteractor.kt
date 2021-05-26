package com.idm.onepiecelist.core.domain.usecase

import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.domain.repository.IOnePieceRepository

class OnePieceInteractor (private val onePieceRepository: IOnePieceRepository) : OnePieceUseCase {
    override fun getAllItems() = onePieceRepository.getAllItems()

    override fun getFavoriteItems()= onePieceRepository.getFavoriteItems()

    override fun setFavoriteItems(items: OnePiece, state: Boolean) = onePieceRepository.setFavoriteItems(items,state)
}