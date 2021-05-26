package com.idm.onepiecelist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase

class HomeViewModel(onePieceUseCase: OnePieceUseCase): ViewModel() {
    val items = onePieceUseCase.getAllItems().asLiveData()
}