package com.example.thomun.data.repository

import com.example.thomun.domain.models.HomeSections
import com.example.thomun.domain.repository.SearchRepository

class SearchRepositoryImpl : SearchRepository {
    override suspend fun getSearchResults(): HomeSections {
        TODO("Not yet implemented")
    }
}