package com.idm.onepiecelist.core.di

import androidx.room.Room
import com.idm.onepiecelist.core.data.source.OnePieceRepository
import com.idm.onepiecelist.core.data.source.local.LocalDataSource
import com.idm.onepiecelist.core.data.source.local.OnePieceDatabase
import com.idm.onepiecelist.core.data.source.remote.ApiService
import com.idm.onepiecelist.core.data.source.remote.RemoteDataSource
import com.idm.onepiecelist.core.domain.repository.IOnePieceRepository
import com.idm.onepiecelist.core.utils.AppExecutors
import com.idm.onepiecelist.core.utils.Constant.BASE_URL
import com.idm.onepiecelist.core.utils.Constant.DATABASE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<OnePieceDatabase>().onePieceDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            OnePieceDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IOnePieceRepository> { OnePieceRepository(get(), get(), get()) }
}

