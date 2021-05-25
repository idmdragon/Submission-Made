package com.idm.onepiecelist.home

import androidx.lifecycle.ViewModel
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val onePieceUseCase: OnePieceUseCase
) : ViewModel() {
    fun getList() = onePieceUseCase.getAllItems()
}