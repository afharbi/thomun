package com.example.thomun.di

import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.repository.HomeRepositoryImpl
import com.example.thomun.data.repository.SearchRepositoryImpl
import com.example.thomun.domain.repository.HomeRepository
import com.example.thomun.domain.repository.SearchRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    @Named("JWTClient")
    fun provideJwtOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Add interceptors, authenticators, etc. as needed
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("JWTClient") okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ThomunApi =
        Retrofit.Builder()
            .baseUrl("https://api-v2-b2sit6oh3a-uc.a.run.app/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
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