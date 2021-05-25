package com.idm.onepiecelist.favorite

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: OnePieceRepository
) : ViewModel() {
    val favoriteItems = repository.getFavoriteItems()
}
