package com.example.thomun.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thomun.domain.models.Content
import com.example.thomun.domain.models.Section
import com.example.thomun.presentation.common.EpisodePreviewCard
import kotlin.time.DurationUnit
import kotlin.time.toDuration

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
                    HomeScreen(
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

@Composable
fun HomeScreen(
    section: String,
    sections: List<Pair<String, List<Content>>>,
    onSearchClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(10.dp)) {
        sections.forEachIndexed { idx, (sectionTitle, episodes) ->
            if (idx > 0) Spacer(Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    section,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
            }
            LazyRow(
                contentPadding = PaddingValues(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(episodes) { episode ->
                    EpisodePreviewCard(
                        squareSize = when (sectionTitle) {
                            SectionType.BOOKS.type, SectionType.BIG_ITEM_HORIZONTAL_LIST.type -> 250
                            else -> 140
                        },
                        playButtonOffset = when (sectionTitle) {
                            SectionType.BOOKS.type, SectionType.BIG_ITEM_HORIZONTAL_LIST.type -> -38
                            else -> -12
                        },
                        imageUrl = episode.avatar_url,
                        title = episode.name,
                        author = episode.podcast_name,
                        duration = episode.duration.toDuration(DurationUnit.SECONDS)
                            .toString(),
                        onPlayClick = { onPlayClick() }
                    )
                }
            }

        }
    }
}

enum class SectionType(val type: String) {
    AUDIO_BOOK("2_lines_grid"),
    BOOKS("big_square"),
    AUDIO_ARTICLE("square"),
    BIG_ITEM_HORIZONTAL_LIST("big_square"),
    QUEUE("queue")
}