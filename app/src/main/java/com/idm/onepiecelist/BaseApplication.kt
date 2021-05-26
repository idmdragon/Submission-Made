package com.idm.onepiecelist

import android.app.Application
import com.idm.onepiecelist.core.di.databaseModule
import com.idm.onepiecelist.core.di.networkModule
import com.idm.onepiecelist.core.di.repositoryModule
import com.idm.onepiecelist.di.useCaseModule
import com.idm.onepiecelist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}