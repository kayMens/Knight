package com.knight.app.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FlashLoading(
    modifier: Modifier = Modifier,
    circleSize: Dp = 30.dp,
    circleColor: Color = Color(0xFF2E7D32),
    spaceBetween: Dp = 8.dp
) {
    val circles = listOf(
        remember { Animatable(1f) },
        remember { Animatable(1f) },
        remember { Animatable(1f) }
    )
    circles.forEachIndexed { index, animaTable ->
        LaunchedEffect(key1 = animaTable) {
            delay(index * 100L)

            animaTable.animateTo(
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        1.0f at 0 using LinearOutSlowInEasing
                        0.0f at 300 using LinearOutSlowInEasing
                        1.0f at 600 using LinearOutSlowInEasing
                        1.0f at 1200 using LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
    val circleValues = circles.map { it.value }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEach { value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .alpha(value)
                    .scale(value)
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview
@Composable
private fun FlashLoadingPreview() {
    FlashLoading()
}