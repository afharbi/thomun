package com.example.thomun.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun EpisodePreviewCard(
    modifier: Modifier = Modifier,
    squareSize: Int,
    playButtonOffset: Int = -8,
    imageUrl: String,
    title: String,
    author: String,
    duration: String,
    onPlayClick: () -> Unit
) {
    Column {
        Box(
            modifier = modifier
                .width(squareSize.dp)
                .height(squareSize.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            // Thumbnail
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(squareSize.dp)
                    .height(squareSize.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.TopCenter)
            )
            // Play Button
            IconButton(
                onClick = onPlayClick,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = (-8).dp, y = (playButtonOffset).dp)
                    .padding(bottom = 15.dp)
                    .background(Color.Black, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White
                )
            }
        }
        // Duration
        Text(
            text = duration,
            color = Color(0xFFB3B3B3),
            fontSize = 12.sp,
            modifier = Modifier
                //.align(Alignment.BottomStart)
                .offset(x = 8.dp, y = (-8).dp)
        )
        // Title & Author
        Column(
            modifier = Modifier
                .width(squareSize.dp)
                .padding(bottom = 36.dp),
            //horizontalAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = author,
                color = Color(0xFFB3B3B3),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}