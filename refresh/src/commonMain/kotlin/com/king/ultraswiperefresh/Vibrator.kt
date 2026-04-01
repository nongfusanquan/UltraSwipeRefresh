package com.king.ultraswiperefresh

import androidx.compose.runtime.Composable

/**
 * 振动效果反馈
 */
@Composable
expect fun VibrationLaunchedEffect(
    vibrationEnabled: Boolean,
    vibrationMillis: Long,
    state: UltraSwipeRefreshState
)
