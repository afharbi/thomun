package com.example.thomun.presentation.search

sealed class SearchEvent {
    data class Search(val query: String) : SearchEvent()
    data class ShowError(val message: String) : SearchEvent()
} 