package com.rzrasel.logwriter.backup;

public class DebugLog extends LogWriter {

    public static void Log(String message, boolean isDebug) {
        LogWriter.FULL_CLASS_NAME = DebugLog.class.getName();
        LogWriter.Log(message, isDebug);
    }

    public static void Log(String message) {
        LogWriter.FULL_CLASS_NAME = DebugLog.class.getName();
        LogWriter.Log(message);
    }

    public static void log(String message, boolean isDebug) {
        LogWriter.FULL_CLASS_NAME = DebugLog.class.getName();
        LogWriter.log(message, isDebug);
    }

    public static void log(String message) {
        LogWriter.FULL_CLASS_NAME = DebugLog.class.getName();
        LogWriter.log(message);
    }
}