package com.idm.onepiecelist.core.di

import android.content.Context
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.idm.onepiecelist.core.BaseApplication
import com.idm.onepiecelist.core.data.source.local.OnePieceDatabase
import com.idm.onepiecelist.core.data.source.local.dao.OnePieceDao
import com.idm.onepiecelist.core.data.source.remote.ApiService
import com.idm.onepiecelist.core.utils.Constant
import com.idm.onepiecelist.core.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app : Context) : BaseApplication {
    return app as BaseApplication
    }


    @Provides
    fun provideBaseUrl() = Constant.BASE_URL


    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieItemDatabase(
        @ApplicationContext context : Context
    )= Room.databaseBuilder(context, OnePieceDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(
        database: OnePieceDatabase
    ) : OnePieceDao {
        return database.onePieceDao()
    }
}