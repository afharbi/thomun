package com.example.thomun.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thomun.presentation.common.MediaSection
import com.example.thomun.presentation.common.SearchBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    var searchTerm by rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        SearchBar(
            input = viewModel.state.query,
            onValueChange = { newValue ->
                searchTerm = newValue
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    delay(200)
                    viewModel.onEvent(SearchEvent.Search(searchTerm))
                }
            },
            onClear = {
                searchTerm = ""
                searchJob?.cancel()
                viewModel.onEvent(SearchEvent.SetQuery(""))
                viewModel.onEvent(SearchEvent.ClearResults)
            },
        )
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(viewModel.state.sections) { section ->
                MediaSection(
                    section = section.name,
                    sections = listOf(
                        section.type to section.content
                    ),
                    onSearchClick = {},
                    onPlayClick = {}
                )
            }
        }
    }
}