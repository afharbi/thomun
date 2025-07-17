package com.example.thomun.data.mapper

import com.example.thomun.data.models.ContentDto
import com.example.thomun.data.models.HomeSectionsDto
import com.example.thomun.data.models.PaginationDto
import com.example.thomun.data.models.SectionDto
import com.example.thomun.domain.models.Content
import com.example.thomun.domain.models.HomeSections
import com.example.thomun.domain.models.Pagination
import com.example.thomun.domain.models.Section

fun HomeSectionsDto.toHomeSections(): HomeSections = HomeSections(
    pagination = pagination?.toPagination() ?: throw Exception("pagination cannot be null"),
    sections = requireNotNull(sections) { "sections cannot be null" }
        .mapIndexed { index, sectionDto ->
            requireNotNull(sectionDto) { "section at index $index cannot be null" }.toSection()
        }
)

fun PaginationDto.toPagination(): Pagination = Pagination(
    next_page = next_page.orEmpty(),
    total_pages = total_pages ?: 0
)

fun SectionDto.toSections(): Section = Section(
    content = content.map { it.toContent() },
    content_type = content_type.orEmpty(),
    name = name.orEmpty(),
    order = order ?: 0,
    type = type.orEmpty()
)
fun ContentDto.toContent(): Content = Content(
    article_id = article_id.orEmpty(),
    audio_url = audio_url.orEmpty(),
    audiobook_id = audiobook_id.orEmpty(),
    author_name = author_name.orEmpty(),
    avatar_url = avatar_url.orEmpty(),
    description = description.orEmpty(),
    duration = duration ?: 0,
    episode_count = episode_count ?: 0,
    episode_id = episode_id.orEmpty(),
    episode_type = episode_type.orEmpty(),
    free_transcript_url = free_transcript_url ?: "",
    language = language.orEmpty(),
    name = name.orEmpty(),
    number = number ?: 0,
    paid_early_access_audio_url = paid_early_access_audio_url ?: "",
    paid_early_access_date = paid_early_access_date ?: "",
    paid_exclusive_start_time = paid_exclusive_start_time ?: 0,
    paid_exclusivity_type = paid_exclusivity_type ?: "",
    paid_is_early_access = paid_is_early_access ?: false,
    paid_is_exclusive = paid_is_exclusive ?: false,
    paid_is_exclusive_partially = paid_is_exclusive_partially ?: false,
    paid_is_now_early_access = paid_is_now_early_access ?: false,
    paid_transcript_url = paid_transcript_url ?: "",
    podcastPopularityScore = podcastPopularityScore ?: 0,
    podcastPriority = podcastPriority ?: 0,
    podcast_id = podcast_id.orEmpty(),
    podcast_name = podcast_name.orEmpty(),
    popularityScore = popularityScore ?: 0,
    priority = priority ?: 0,
    release_date = release_date.orEmpty(),
    score = score ?: 0.0,
    season_number = season_number ?: 0,
    separated_audio_url = separated_audio_url.orEmpty()
)
