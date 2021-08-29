package com.emrehmrc.hmrceffect

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Default linear color list
val defaultColors = listOf(
    Color.Gray.copy(alpha = 0.9f),
    Color.Gray.copy(alpha = 0.8f),
    Color.Gray.copy(alpha = 0.9f),
)

@Composable
fun HmrcEffect(
    modifier: Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    height: Dp = 450.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    colors: List<Color> = defaultColors,
) {
    var animPlayed by remember {
        mutableStateOf(false)
    }
    BoxWithConstraints {
        val pxHeightValue = with(LocalDensity.current) { height.toPx() }
        val calculatedMaxValue = maxWidth.value + pxHeightValue
        // Middle color width
        val calculatedOffSet = calculatedMaxValue * 0.1f

        val currentOffset = animateFloatAsState(
            targetValue = if (animPlayed) calculatedMaxValue else 0f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = animDuration,
                    delayMillis = animDelay,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        LaunchedEffect(key1 = true) {
            animPlayed = true
        }

        val brush = linearGradient(
            colors = colors,
            start = Offset(
                currentOffset.value - calculatedOffSet,
                currentOffset.value - calculatedOffSet
            ),
            end = Offset(
                currentOffset.value,
                currentOffset.value
            ),
        )

        Card(
            shape = shape,
            modifier = modifier
        ) {
            Spacer(
                modifier = Modifier
                    .height(height)
                    .background(brush = brush)
            )

        }
    }
}


