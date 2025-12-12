package com.rzrasel.logwriter.backup;

public enum LogType {
    DEBUG("\u001B[34m"),   // Blue
    INFO("\u001B[32m"),    // Green
    WARNING("\u001B[33m"), // Yellow
    ERROR("\u001B[31m"),   // Red
    RESET("\\u001B[0m");   // Red

    private final String colorCode;

    LogType(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}