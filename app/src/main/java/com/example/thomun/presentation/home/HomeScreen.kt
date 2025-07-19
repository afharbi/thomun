package com.example.thomun.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thomun.domain.models.home.Section
import com.example.thomun.presentation.common.MediaSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val pagedSections: LazyPagingItems<Section> = viewModel.pagedSections.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .background(Color(0xFF121212))
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Home") },
            actions = {
                Row(
                    modifier = modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onSearchClick.invoke() })
                ) {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            }
        )
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(pagedSections.itemSnapshotList.items) { section ->
                    MediaSection(
                        section = section.name,
                        sections = listOf(
                            section.type to section.content
                        ),
                        onSearchClick = onSearchClick,
                        onPlayClick = {}
                    )
                }
            }
            when {
                pagedSections.loadState.refresh is androidx.paging.LoadState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                pagedSections.loadState.refresh is androidx.paging.LoadState.Error -> {
                    val error =
                        (pagedSections.loadState.refresh as androidx.paging.LoadState.Error).error
                    Text(
                        text = "Error: ${'$'}{error.localizedMessage}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}