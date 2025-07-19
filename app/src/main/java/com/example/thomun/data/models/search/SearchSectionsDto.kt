package com.example.thomun.data.models.search

import com.squareup.moshi.Json

data class SearchSectionsDto(
    @Json(name = "sections")
    val sections: List<SearchSectionDto?>?
)