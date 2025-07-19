package com.example.thomun.presentation.search

sealed class SearchEvent {
    data class SetQuery(val query: String) : SearchEvent()
    data object ClearResults: SearchEvent()
    data class Search(val query: String) : SearchEvent()
} 