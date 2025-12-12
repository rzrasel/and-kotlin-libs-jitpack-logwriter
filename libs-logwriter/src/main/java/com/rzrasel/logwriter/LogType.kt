package com.rzrasel.logwriter

enum class LogType(val colorCode: String) {
    DEBUG("\u001B[34m"),   // Blue
    INFO("\u001B[32m"),    // Green
    WARNING("\u001B[33m"), // Yellow
    ERROR("\u001B[31m"),   // Red
    RESET("\u001B[0m");    // Reset
}