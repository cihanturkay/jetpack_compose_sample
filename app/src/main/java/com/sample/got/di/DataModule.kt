package com.sample.got.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sample.got.data.repo.DefaultRepository
import com.sample.got.data.repo.Repository
import com.sample.got.data.source.DataSource
import com.sample.got.data.source.remote.Api
import com.sample.got.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteDataSourceType

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalDataSourceType

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGOTRepository(
        @RemoteDataSourceType remoteDataSource: DataSource,
    ): Repository {
        return DefaultRepository(remoteDataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @RemoteDataSourceType
    @Provides
    fun provideGOTRemoteDataSource(api: Api): DataSource = RemoteDataSource(api)

    @Singleton
    @Provides
    fun provideService(client: OkHttpClient): Api {
        val contentType = "application/json".toMediaType()
        val kotlinxConverterFactory = Json.asConverterFactory(contentType)

        return Retrofit.Builder()
            .baseUrl("https://www.anapioficeandfire.com/api/")
            .addConverterFactory(kotlinxConverterFactory)
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}