package com.example.thomun.data.api

import com.example.thomun.data.models.home.HomeSectionsDto
import com.example.thomun.data.models.search.SearchSectionsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ThomunApi {

    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int
    ): HomeSectionsDto

    @GET("https://mock.apidog.com/m1/735111-711675-default/search")
    suspend fun search(
        @Query("q") query: String
    ): SearchSectionsDto
}