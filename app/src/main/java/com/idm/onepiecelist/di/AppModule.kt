package com.idm.onepiecelist.di
import com.idm.onepiecelist.core.domain.usecase.OnePieceInteractor
import com.idm.onepiecelist.core.domain.usecase.OnePieceUseCase
import com.idm.onepiecelist.detail.DetailViewModel
import com.idm.onepiecelist.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<OnePieceUseCase> { OnePieceInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DetailViewModel(get())}
}