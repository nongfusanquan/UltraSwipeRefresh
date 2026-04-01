package com.king.ultraswiperefresh

import androidx.compose.runtime.Composable

/**
 * 振动效果反馈
 */
@Composable
actual fun VibrationLaunchedEffect(
    vibrationEnabled: Boolean,
    vibrationMillis: Long,
    state: UltraSwipeRefreshState
) {
    // iOS 暂未实现振动反馈
}
