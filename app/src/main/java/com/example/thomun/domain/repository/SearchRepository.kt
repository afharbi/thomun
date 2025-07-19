package com.example.thomun.domain.repository

import com.example.thomun.domain.models.search.SearchSections

interface SearchRepository {
    suspend fun getSearchResults(query: String): Result<SearchSections>
}