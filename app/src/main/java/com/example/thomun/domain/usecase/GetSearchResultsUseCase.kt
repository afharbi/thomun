package com.example.thomun.domain.usecase

import com.example.thomun.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchResultsUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun invoke(query: String) = repository.getSearchResults(query = query)
}
