package com.example.thomun.data.models.home

import com.squareup.moshi.Json

data class PaginationDto(
    @Json(name = "next_page")
    val next_page: String?,
    @Json(name = "total_pages")
    val total_pages: Int?
)