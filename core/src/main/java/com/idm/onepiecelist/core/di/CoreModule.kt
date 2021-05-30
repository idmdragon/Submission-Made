package com.idm.onepiecelist.core.di

import androidx.room.Room
import com.idm.onepiecelist.core.source.OnePieceRepository
import com.idm.onepiecelist.core.source.local.LocalDataSource
import com.idm.onepiecelist.core.source.local.OnePieceDatabase
import com.idm.onepiecelist.core.source.remote.ApiService
import com.idm.onepiecelist.core.source.remote.RemoteDataSource
import com.idm.onepiecelist.core.domain.repository.IOnePieceRepository
import com.idm.onepiecelist.core.utils.Constant.BASE_URL
import com.idm.onepiecelist.core.utils.Constant.DATABASE_NAME
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("onepiece".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            OnePieceDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    }
}

val networkModule = module {
    single {
        val hostname = "api.jikan.moe"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/7WzOtAYFrxzTuC2KKPH0qCk4EB5tukcc+aX96BTmFWI=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
    single<IOnePieceRepository> { OnePieceRepository(get(), get()) }
}

