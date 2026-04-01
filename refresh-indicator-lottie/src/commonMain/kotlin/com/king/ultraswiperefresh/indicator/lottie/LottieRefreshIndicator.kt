package com.king.ultraswiperefresh.indicator.lottie

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.king.ultraswiperefresh.UltraSwipeFooterState
import com.king.ultraswiperefresh.UltraSwipeHeaderState
import com.king.ultraswiperefresh.UltraSwipeRefreshState
import com.king.ultraswiperefresh.indicator.animationSpec
import kottie.Kottie
import kottie.KottieAnimation
import kottie.KottieCompositionSpec
import kottie.animateKottieCompositionAsState
import kottie.rememberKottieComposition
import ultraswiperefresh.refresh_indicator_lottie.generated.resources.Res


/**
 * Lottie动画指示器
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@Composable
internal fun LottieRefreshIndicator(
    state: UltraSwipeRefreshState,
    isFooter: Boolean,
    spec: KottieCompositionSpec? = null, // 新增 spec 参数
    modifier: Modifier = Modifier,
    height: Dp = 60.dp,
    alignment: Alignment = Alignment.Center,
    speed: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
) {
//    val composition by rememberLottieComposition(spec = spec)

    var animationJson by remember { mutableStateOf("") }

    // 如果没有提供 spec，则尝试根据 jsonPath 加载
    LaunchedEffect( spec) {
        if (spec == null) {
            try {
                // 注意：这里的 Res 是库 module 的，只能读库里的资源
                val bytes = Res.readBytes("files/usr_default_lottie_animation.json")
                animationJson = bytes.decodeToString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // 优先使用传入的 spec
    val composition = if (spec != null) {
        rememberKottieComposition(spec)
    } else {
        rememberKottieComposition(KottieCompositionSpec.JsonString(animationJson))
    }


    val isPlaying by remember(isFooter, state) {
        derivedStateOf {
            if (isFooter) {
                state.footerState == UltraSwipeFooterState.Loading && !state.isFinishing
            } else {
                state.headerState == UltraSwipeHeaderState.Refreshing && !state.isFinishing
            }
        }
    }

    /*    val progress by animateLottieCompositionAsState(
            composition = composition,
            isPlaying = isPlaying,
            restartOnPlay = true,
            speed = speed,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.OnIterationFinish,
        )*/

    val animationState by animateKottieCompositionAsState(
        composition = composition,
        iterations = Kottie.IterateForever
    )


    val targetAlpha by remember(isFooter, state) {
        derivedStateOf {
            if ((!isFooter && state.indicatorOffset > 0f) || (isFooter && state.indicatorOffset < 0f)) {
                1f
            } else {
                0f
            }
        }
    }

    val alpha by animateFloatAsState(targetValue = targetAlpha, animationSpec = animationSpec)

    Box(
        modifier = Modifier
            .alpha(alpha)
            .fillMaxWidth()
            .height(height),
        contentAlignment = alignment,
    ) {
        /*  LottieAnimation(
              composition = composition,
              progress = { progress },
              modifier = modifier,
              contentScale = contentScale,
          )*/
        KottieAnimation(
            composition = composition,
            progress = { animationState.progress },
            modifier = Modifier.fillMaxSize()
        )
    }
}
