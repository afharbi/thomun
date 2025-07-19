package com.example.thomun.domain.models.search

data class Section(
    val content: List<Content>,
    val content_type: String,
    val name: String,
    val order: String,
    val type: String
)