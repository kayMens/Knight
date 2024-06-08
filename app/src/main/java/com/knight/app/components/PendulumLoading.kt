package com.knight.app.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PendulumLoading(
    modifier: Modifier = Modifier,
    circleSize: Dp = 30.dp,
    spaceBetween: Dp = 8.dp
) {
    val distance = with(LocalDensity.current) { circleSize }

    Row(
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        (0..2).forEach { value ->
            val startOffsetX = fl(circleSize = distance, delay = value * 300)
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xB4788FBB),
                                Color(0xDF3C76EC),
                                Color(0xB4788FBB),
                            ),
                            startX = startOffsetX,
                            endX = startOffsetX + circleSize.value
                        ),
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
private fun fl(circleSize: Dp, delay: Int): Float {
    val transition = rememberInfiniteTransition(label = "$delay")

    val startOffSetX by transition.animateFloat(
        initialValue = -2 * circleSize.value,
        targetValue = 2 * circleSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, delayMillis = delay),
            repeatMode = RepeatMode.Reverse
        ),
        label = "$delay"
    )
    return startOffSetX
}