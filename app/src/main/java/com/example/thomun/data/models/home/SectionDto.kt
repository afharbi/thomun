package com.example.thomun.data.models.home

import com.squareup.moshi.Json

data class SectionDto(
    @Json(name = "content")
    val content: List<ContentDto>?,
    @Json(name = "content_type")
    val content_type: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "type")
    val type: String?
)