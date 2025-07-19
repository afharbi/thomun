package com.example.thomun.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thomun.R
import com.example.thomun.domain.models.home.Section
import com.example.thomun.presentation.common.MediaSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    val pagedSections: LazyPagingItems<Section> = viewModel.pagedSections.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        modifier = Modifier.size(55.dp),
                        painter = painterResource(id = R.drawable.thmanyah_logo),
                        tint = Color(0xFFE94E1B),
                        contentDescription = "thmanyah logo",
                    )
                },
                actions = {
                    Row(
                        modifier = modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { onSearchClick.invoke() })
                    ) {
                        IconButton(onClick = onNotificationsClick) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.White.copy(alpha = 0.08f), shape = CircleShape)
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = "Notifications",
                                    tint = Color.White,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0,0,0,0),
        containerColor = Color(0xFF121212)
    ) { innerPadding ->
        Box(modifier = modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.Center) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(pagedSections.itemSnapshotList.items) { section ->
                    MediaSection(
                        section = section.name,
                        sections = listOf(
                            section.type to section.content
                        ),
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