package com.example.thomun.di

import com.example.thomun.domain.repository.HomeRepository
import com.example.thomun.domain.repository.SearchRepository
import com.example.thomun.domain.usecase.GetHomeSectionsUseCase
import com.example.thomun.domain.usecase.GetSearchResultsUseCase
import com.example.thomun.domain.usecase.ThomunUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {
    @ViewModelScoped
    @Provides
    fun provideUseCases(
        homeRepository: HomeRepository,
        searchRepository: SearchRepository
    ): ThomunUseCases = ThomunUseCases(
        getHomeSections = GetHomeSectionsUseCase(homeRepository),
        getSearchResults = GetSearchResultsUseCase(searchRepository)
    )

}