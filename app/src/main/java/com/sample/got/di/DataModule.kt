package com.sample.got.di

import com.sample.got.data.repo.DefaultGOTRepository
import com.sample.got.data.repo.GOTRepository
import com.sample.got.data.source.GOTDataSource
import com.sample.got.data.source.remote.GOTApi
import com.sample.got.data.source.remote.GOTRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteGOTDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalGOTDataSource

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGOTRepository(
        @RemoteGOTDataSource remoteDataSource: GOTDataSource,
    ): GOTRepository {
        return DefaultGOTRepository(remoteDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteGOTDataSource
    @Provides
    fun provideGOTRemoteDataSource(api: GOTApi): GOTDataSource = GOTRemoteDataSource(api)

    @Singleton
    @Provides
    fun provideGOTService(client: OkHttpClient): GOTApi {
        return Retrofit.Builder()
            .baseUrl("https://www.anapioficeandfire.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GOTApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        var okHttpClient: OkHttpClient? = null
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}