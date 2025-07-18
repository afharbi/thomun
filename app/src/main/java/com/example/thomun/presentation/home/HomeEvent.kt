package com.example.thomun.presentation.home

sealed class HomeEvent {
    data class ShowError(val message: String) : HomeEvent()
} 