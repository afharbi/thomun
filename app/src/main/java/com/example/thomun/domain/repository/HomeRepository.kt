package com.example.thomun.domain.repository

import androidx.paging.PagingData
import com.example.thomun.domain.models.HomeSections
import com.example.thomun.domain.models.Section
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPagedSections(): Flow<PagingData<Section>>
}