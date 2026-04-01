package com.king.ultraswiperefresh

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

/**
 * 振动效果反馈
 */
@Suppress("DEPRECATION")
@Composable
actual fun VibrationLaunchedEffect(
    vibrationEnabled: Boolean,
    vibrationMillis: Long,
    state: UltraSwipeRefreshState
) {
    val vibrator = rememberVibrator()

    if (!vibrationEnabled || !vibrator.hasVibrator()) return

//    LaunchedEffect(state.headerState, state.footerState) {
//        if (state.headerState == UltraSwipeHeaderState.ReleaseToRefresh ||
//            state.footerState == UltraSwipeFooterState.ReleaseToLoad ||
//            state.headerState == UltraSwipeHeaderState.ReleaseToSecondary ||
//            state.footerState == UltraSwipeFooterState.ReleaseToSecondary
//        ) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vibrator.vibrate(
//                    VibrationEffect.createOneShot(
//                        vibrationMillis,
//                        VibrationEffect.DEFAULT_AMPLITUDE
//                    )
//                )
//            } else {
//                vibrator.vibrate(vibrationMillis)
//            }
//        }
//    }
}

/**
 * Vibrator
 */
@Suppress("DEPRECATION")
@Composable
private fun rememberVibrator(): Vibrator {
    val context = LocalContext.current
    return remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
}
