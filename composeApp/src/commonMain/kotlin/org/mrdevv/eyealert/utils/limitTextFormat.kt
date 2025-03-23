package org.mrdevv.eyealert.utils

fun limitText(text: String, limit: Int): String {
    val words = text.split(" ")
    return if (words.size > limit) {
        words.take(limit).joinToString(" ") + "..."
    } else {
        text
    }
}