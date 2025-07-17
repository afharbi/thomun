package com.example.thomun.domain.usecase

import com.example.thomun.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend fun invoke() = repository.getHomeSections()
}