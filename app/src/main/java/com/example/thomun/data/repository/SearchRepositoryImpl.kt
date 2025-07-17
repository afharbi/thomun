package com.example.thomun.data.repository

import coil.network.HttpException
import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.mapper.toHomeSections
import com.example.thomun.domain.models.HomeSections
import com.example.thomun.domain.repository.SearchRepository
import okio.IOException

class SearchRepositoryImpl(
    private val api: ThomunApi
): SearchRepository {
    override suspend fun getSearchResults(): Result<HomeSections> {
        return try {
            Result.success(api.search().toHomeSections())
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}