package com.king.ultraswiperefresh.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.startup.Initializer

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * <p>
 * <a href="https://github.com/jenly1314">Follow me</a>
 */
@SuppressLint("StaticFieldLeak")
object ContextProvider {
    lateinit var context: Context
}

class ContextInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        ContextProvider.context = context
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
