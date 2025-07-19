package com.example.thomun.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.thomun.R
import kotlinx.coroutines.delay

@SuppressLint("RememberReturnType", "UseOfNonLambdaOffsetOverload")
@Composable
fun SplashScreen(
    onAnimationFinished: () -> Unit,
    modifier: Modifier = Modifier
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.logo))
    // Animation states
    val logoScale = remember { Animatable(0.6f) }
    val logoAlpha = remember { Animatable(0f) }
    val textOffsetY = remember { Animatable(40f) }
    val textAlpha = remember { Animatable(0f) }
    val pulseAlpha = remember { Animatable(0.2f) }

    // Launch the animation sequence
    LaunchedEffect(Unit) {
        logoScale.animateTo(1.1f, animationSpec = tween(700, easing = EaseOutBack))
        logoAlpha.animateTo(1f, animationSpec = tween(500))
        pulseAlpha.animateTo(0.5f, animationSpec = tween(700))
        textAlpha.animateTo(1f, animationSpec = tween(500, delayMillis = 200))
        textOffsetY.animateTo(0f, animationSpec = tween(500, delayMillis = 200))
        delay(1700)
        onAnimationFinished()
    }
    Box(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(Color(0x80000000), Color(0xFF000000))
                )
            ).fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(180.dp)
                .graphicsLayer {
                    alpha = pulseAlpha.value
                    scaleX = logoScale.value * 1.3f
                    scaleY = logoScale.value * 1.3f
                }
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x80000000), Color.Transparent)
                    ),
                    shape = CircleShape
                )
        )
        // Logo
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                iterations = 1
            )
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .offset(y = textOffsetY.value.dp)
                    .alpha(textAlpha.value),
                painter = painterResource(id = R.drawable.thmanyah_logo),
                tint = Color(0xFFE94E1B),
                contentDescription = "thmanyah logo",
            )
        }
    }
}