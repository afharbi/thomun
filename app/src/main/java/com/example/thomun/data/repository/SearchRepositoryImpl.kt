package com.example.thomun.data.repository

import coil.network.HttpException
import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.mapper.toSearchSections
import com.example.thomun.domain.models.search.SearchSections
import com.example.thomun.domain.repository.SearchRepository
import okio.IOException

class SearchRepositoryImpl(
    private val api: ThomunApi
): SearchRepository {
    override suspend fun getSearchResults(query: String): Result<SearchSections> {
        return try {
            Result.success(api.search(query).toSearchSections())
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}