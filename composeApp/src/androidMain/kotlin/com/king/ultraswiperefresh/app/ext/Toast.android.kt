package com.king.ultraswiperefresh.app.ext

import android.widget.Toast
import com.king.ultraswiperefresh.app.ContextProvider

actual fun showToast(text: String) {
    val context = ContextProvider.context
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
