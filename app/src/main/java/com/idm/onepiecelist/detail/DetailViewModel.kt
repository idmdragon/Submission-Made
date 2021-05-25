package com.idm.onepiecelist.detail

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: OnePieceRepository
) : ViewModel() {
    fun setFavoriteItem(items: OnePieceEntity, newStatus:Boolean) = repository.setFavoriteItems(items, newStatus)
}