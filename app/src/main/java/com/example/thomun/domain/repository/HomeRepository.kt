package com.example.thomun.domain.repository

import androidx.paging.PagingData
import com.example.thomun.domain.models.home.Section
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPagedSections(): Flow<PagingData<Section>>
}