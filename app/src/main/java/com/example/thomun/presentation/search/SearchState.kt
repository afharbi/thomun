package com.example.thomun.presentation.search

import com.example.thomun.domain.models.search.Section

data class SearchState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: Boolean = false,
    val errorMessage: String = "",
    val query: String = "",
    val sections: List<Section> = emptyList()
)