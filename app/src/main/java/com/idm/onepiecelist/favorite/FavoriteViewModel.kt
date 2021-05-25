package com.idm.onepiecelist.favorite

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
     onePieceUseCase: OnePieceUseCase
) : ViewModel() {
    val favoriteItems = onePieceUseCase.getFavoriteItems()
}
