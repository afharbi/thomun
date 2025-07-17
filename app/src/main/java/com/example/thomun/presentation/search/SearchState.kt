package com.example.thomun.presentation.search

import com.example.thomun.domain.models.HomeSections

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val data: HomeSections) : SearchState()
    data class Error(val message: String) : SearchState()
} 