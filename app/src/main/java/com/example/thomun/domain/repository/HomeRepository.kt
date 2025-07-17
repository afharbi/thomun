package com.example.thomun.domain.repository

import com.example.thomun.domain.models.HomeSections

interface HomeRepository {
    suspend fun getHomeSections(): HomeSections
}