package com.example.thomun.data.di

import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.repository.HomeRepositoryImpl
import com.example.thomun.data.repository.SearchRepositoryImpl
import com.example.thomun.domain.repository.HomeRepository
import com.example.thomun.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("JWTClient") okHttpClient: OkHttpClient
    ): ThomunApi =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient).build().create(ThomunApi::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(
        api: ThomunApi
    ): HomeRepository = HomeRepositoryImpl(
        api = api
    )

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: ThomunApi
    ): SearchRepository = SearchRepositoryImpl(
        api = api
    )
}