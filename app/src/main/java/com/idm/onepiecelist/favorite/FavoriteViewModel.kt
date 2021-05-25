package com.idm.onepiecelist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData

import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
     onePieceUseCase: OnePieceUseCase
) : ViewModel() {
    val favoriteItems = onePieceUseCase.getFavoriteItems().asLiveData()
}
