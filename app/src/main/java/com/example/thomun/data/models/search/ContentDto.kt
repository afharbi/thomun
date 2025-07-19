package com.example.thomun.data.models.search

import com.squareup.moshi.Json

data class ContentDto(
    @Json(name="description")
    val description: String?,
    @Json(name="duration")
    val duration: String?,
    @Json(name="language")
    val language: String?,
    @Json(name="name")
    val name: String?,
    @Json(name="number")
    val number: String?,
    @Json(name="podcastPopularityScore")
    val podcastPopularityScore: String?,
    @Json(name="podcastPriority")
    val podcastPriority: String?,
    @Json(name="episode_count")
    val episode_count: String?,
    @Json(name="popularityScore")
    val popularityScore: String?,
    @Json(name="priority")
    val priority: String?,
    @Json(name="score")
    val score: String?,
    @Json(name="avatar_url")
    val avatar_url: String?,
    @Json(name="podcast_id")
    val podcast_id: String?,
)