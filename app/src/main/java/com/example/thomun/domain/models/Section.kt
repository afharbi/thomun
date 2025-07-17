package com.example.thomun.domain.models

data class Section(
    val content: List<Content>,
    val content_type: String,
    val name: String,
    val order: Int,
    val type: String
)