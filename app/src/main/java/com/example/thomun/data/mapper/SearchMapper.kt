package com.example.thomun.data.mapper

import com.example.thomun.data.models.search.ContentDto
import com.example.thomun.data.models.search.SearchSectionDto
import com.example.thomun.data.models.search.SearchSectionsDto
import com.example.thomun.domain.models.search.Content
import com.example.thomun.domain.models.search.SearchSections
import com.example.thomun.domain.models.search.Section

fun SearchSectionsDto.toSearchSections(): SearchSections = SearchSections(
    sections = requireNotNull(sections) { "sections cannot be null" }
        .mapIndexed { index, sectionDto ->
            requireNotNull(sectionDto) { "section at index $index cannot be null" }.toSearchSection()
        }
)


fun SearchSectionDto.toSearchSection(): Section = Section(
    content = requireNotNull(content) { "content cannot be null" }
        .mapIndexed { _, contentDto ->
            contentDto.toContent()
        },
    content_type = content_type.orEmpty(),
    name = name.orEmpty(),
    order = order.orEmpty(),
    type = type.orEmpty()
)

fun ContentDto.toContent(): Content = Content(

    avatar_url = avatar_url.orEmpty(),
    description = description.orEmpty(),
    duration = duration.orEmpty(),
    episode_count = episode_count.orEmpty(),
    language = language.orEmpty(),
    name = name.orEmpty(),
    podcast_id = podcast_id.orEmpty(),
    popularityScore = popularityScore.orEmpty(),
    priority = priority.orEmpty(),
    score = score.orEmpty(),
)
