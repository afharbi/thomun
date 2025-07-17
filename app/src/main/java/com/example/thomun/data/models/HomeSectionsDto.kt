package com.example.thomun.data.models

import com.squareup.moshi.Json

data class HomeSectionsDto(
    @Json(name = "pagination")
    val pagination: PaginationDto?,
    @Json(name = "sections")
    val sections: List<SectionDto?>?
)