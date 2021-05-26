package com.idm.onepiecelist.favorite.favorite.di

import com.idm.onepiecelist.favorite.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}