package org.mrdevv.eyealert

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform