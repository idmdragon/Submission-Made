package com.idm.onepiecelist.home

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: OnePieceRepository
) : ViewModel() {
    fun getList() = repository.getAllItems()
}