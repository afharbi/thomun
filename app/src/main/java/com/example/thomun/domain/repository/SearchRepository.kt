package com.example.thomun.domain.repository

import com.example.thomun.domain.models.HomeSections

interface SearchRepository {
    suspend fun getSearchResults(): Result<HomeSections>
}