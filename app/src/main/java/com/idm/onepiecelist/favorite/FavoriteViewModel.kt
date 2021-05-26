package com.idm.onepiecelist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase

class FavoriteViewModel(onePieceUseCase: OnePieceUseCase):ViewModel() {
    val favoriteItems = onePieceUseCase.getFavoriteItems().asLiveData()
}
