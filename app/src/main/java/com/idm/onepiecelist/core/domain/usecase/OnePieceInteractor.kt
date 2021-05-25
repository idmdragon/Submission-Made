package com.idm.onepiecelist.core.domain.usecase

import androidx.lifecycle.LiveData
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.domain.repository.IOnePieceRepository
import com.idm.onepiecelist.core.vo.Resource

class OnePieceInteractor (private val onePieceRepository: IOnePieceRepository) : OnePieceUseCase {
    override fun getAllItems(): LiveData<Resource<List<OnePiece>>>  = onePieceRepository.getAllItems()

    override fun getFavoriteItems(): LiveData<List<OnePiece>> = onePieceRepository.getFavoriteItems()

    override fun setFavoriteItems(items: OnePiece, state: Boolean) = onePieceRepository.setFavoriteItems(items,state)
}