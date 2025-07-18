package com.example.thomun.domain.usecase

import androidx.paging.PagingData
import com.example.thomun.domain.models.Section
import com.example.thomun.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(private val repository: HomeRepository) {
    fun getPagedSections(): Flow<PagingData<Section>> = repository.getPagedSections()
}