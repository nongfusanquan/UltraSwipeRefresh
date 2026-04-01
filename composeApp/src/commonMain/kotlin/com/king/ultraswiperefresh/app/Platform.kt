package com.king.ultraswiperefresh.app
interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
