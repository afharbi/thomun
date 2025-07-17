package com.example.thomun.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Example UI based on state
    when (state) {
        is HomeState.Loading -> {
            // Show loading UI
        }
        is HomeState.Success -> {
            val homeSections = (state as HomeState.Success).data
            androidx.compose.foundation.lazy.LazyColumn(modifier = modifier) {
                items(homeSections.sections.size) { sectionIndex ->
                    val section = homeSections.sections[sectionIndex]
                    androidx.compose.material3.Text(text = section.name)
                    section.content.forEach { content ->
                        androidx.compose.material3.Text(text = "- ${content.name}")
                    }
                }
            }
        }
        is HomeState.Error -> {
            // Show error UI
        }
    }
}