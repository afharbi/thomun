package com.example.thomun.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thomun.domain.models.search.Content
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun MediaSection(
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
                        author = episode.name,
                        duration = episode.duration.toInt().toDuration(DurationUnit.SECONDS)
                            .toString(),
                        onPlayClick = { onPlayClick() }
                    )
                }
            }

        }
    }
}