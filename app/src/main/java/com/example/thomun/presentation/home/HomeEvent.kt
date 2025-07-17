package com.example.thomun.presentation.home

sealed class HomeEvent {
    data object LoadHome : HomeEvent()
    data class ShowError(val message: String) : HomeEvent()
} 