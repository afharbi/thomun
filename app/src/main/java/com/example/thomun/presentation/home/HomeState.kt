package com.example.thomun.presentation.home

import com.example.thomun.domain.models.home.HomeSections

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val data: HomeSections) : HomeState()
    data class Error(val message: String) : HomeState()
} 