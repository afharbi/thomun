package com.example.thomun.data.repository

import coil.network.HttpException
import com.example.thomun.data.api.ThomunApi
import com.example.thomun.data.mapper.toHomeSections
import com.example.thomun.domain.models.HomeSections
import com.example.thomun.domain.repository.HomeRepository
import okio.IOException

class HomeRepositoryImpl(
    private val api: ThomunApi
): HomeRepository {
    override suspend fun getHomeSections(): Result<HomeSections> {
        return try {
            Result.success(api.getHomeSections().toHomeSections())
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}