package com.example.borutoapp.di

import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.data.repository.RemoteDataSourceImpl
import com.example.borutoapp.domain.repository.RemoteDataSource
import com.example.borutoapp.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi{
     return retrofit.create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
    }
}