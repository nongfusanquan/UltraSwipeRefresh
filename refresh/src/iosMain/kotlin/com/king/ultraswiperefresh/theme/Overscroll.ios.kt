package com.king.ultraswiperefresh.theme

import androidx.compose.runtime.Composable

@Composable
actual fun NoOverscrollEffect(content: @Composable () -> Unit) {
    content()
}
