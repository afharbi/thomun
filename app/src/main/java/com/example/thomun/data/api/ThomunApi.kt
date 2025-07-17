package com.example.thomun.data.api

import com.example.thomun.data.models.HomeSectionsDto
import retrofit2.http.GET

interface ThomunApi {

    @GET("https://api-v2-b2sit6oh3a-uc.a.run.app/home_sections")
    suspend fun getHomeSections(): HomeSectionsDto

    @GET("https://mock.apidog.com/m1/735111-711675-default/search")
    suspend fun search(): HomeSectionsDto
}