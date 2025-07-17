package com.example.thomun.data.models.home

import com.squareup.moshi.Json

data class ContentDto(
    @Json(name = "article_id")
    val article_id: String?,
    @Json(name="audio_url")
    val audio_url: String?,
    @Json(name="audiobook_id")
    val audiobook_id: String?,
    @Json(name="author_name")
    val author_name: String?,
    @Json(name="avatar_url")
    val avatar_url: String?,
    @Json(name="description")
    val description: String?,
    @Json(name="duration")
    val duration: Int?,
    @Json(name="episode_count")
    val episode_count: Int?,
    @Json(name="episode_id")
    val episode_id: String?,
    @Json(name="episode_type")
    val episode_type: String?,
    @Json(name="free_transcript_url")
    val free_transcript_url: Any?,
    @Json(name="language")
    val language: String?,
    @Json(name="name")
    val name: String?,
    @Json(name="number")
    val number: Any?,
    @Json(name="paid_early_access_audio_url")
    val paid_early_access_audio_url: Any?,
    @Json(name="paid_early_access_date")
    val paid_early_access_date: Any?,
    @Json(name="paid_exclusive_start_time")
    val paid_exclusive_start_time: Int?,
    @Json(name="paid_exclusivity_type")
    val paid_exclusivity_type: Any?,
    @Json(name="paid_is_early_access")
    val paid_is_early_access: Boolean?,
    @Json(name="paid_is_exclusive")
    val paid_is_exclusive: Boolean?,
    @Json(name="paid_is_exclusive_partially")
    val paid_is_exclusive_partially: Boolean?,
    @Json(name="paid_is_now_early_access")
    val paid_is_now_early_access: Boolean?,
    @Json(name="paid_transcript_url")
    val paid_transcript_url: Any?,
    @Json(name="podcastPopularityScore")
    val podcastPopularityScore: Int?,
    @Json(name="podcastPriority")
    val podcastPriority: Int?,
    @Json(name="podcast_id")
    val podcast_id: String?,
    @Json(name="podcast_name")
    val podcast_name: String?,
    @Json(name="popularityScore")
    val popularityScore: Int?,
    @Json(name="priority")
    val priority: Int?,
    @Json(name="release_date")
    val release_date: String?,
    @Json(name="score")
    val score: Double?,
    @Json(name="season_number")
    val season_number: Any?,
    @Json(name="separated_audio_url")
    val separated_audio_url: String?
)