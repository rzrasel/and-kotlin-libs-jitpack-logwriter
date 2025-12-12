package com.rzrasel.logwriter.backup;

import android.annotation.SuppressLint;

public class LogWriter {
    protected static String FULL_CLASS_NAME = LogWriter.class.getName();
    private static final String CLASS_NAME = LogWriter.class.getSimpleName();
    private static final String DEBUG_PREFIX = "DEBUG_LOG_WRITER_PRINT: ";
    //private static final String LOG_FORMAT = "Message: %s | Class: %s.%s | Method: %s | Line: %d";
    private static final String LOG_FORMAT = "Message: %s - Class: %s - Method: %s - Line Number: %d | Full Class | Package: %s | Class: %s | Line Number: %d";
    private static final String RESET = LogType.RESET.getColorCode();

    public LogWriter() {
        debugPrint("Initialized LogWriterV2 for class: " + getCallerClassName());
    }

    public static void Log(String message, boolean isDebug) {
        log(message, isDebug);
    }

    public static void Log(String message) {
        log(message);
    }

    public static void log(String message, boolean isDebug) {
        if(isDebug) {
            log(message);
        }
    }

    public static void log(String message) {
        /*if(!BuildConfig.DEBUG) {
            return;
        }*/
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        if(callerStackTraceElement == null) {
            debugPrint("[Unknown Caller] Unable to determine caller information for message: " + message);
            return;
        }

        String packageName = getCallerPackageName(callerStackTraceElement);
        packageName = packageName.isEmpty() ? "(default package)" : packageName;
        String className = callerStackTraceElement.getClassName();
        String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
        String methodName = callerStackTraceElement.getMethodName();
        int lineNumber = callerStackTraceElement.getLineNumber();

        @SuppressLint("DefaultLocale")
        String logMessage = String.format(
                LOG_FORMAT,
                message,
                simpleClassName,
                methodName,
                lineNumber,
                packageName + ".",
                className,
                lineNumber
        );

        debugPrint(logMessage);
    }

    private static StackTraceElement getCallerStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean isClassFound = false;
        String targetClassName = FULL_CLASS_NAME;
        for(StackTraceElement stackTraceElement : stackTrace) {
            if(stackTraceElement.getClassName().equalsIgnoreCase(targetClassName)) {
                isClassFound = true;
            }
            if(isClassFound && !stackTraceElement.getClassName().equalsIgnoreCase(targetClassName)) {
                return stackTraceElement;
            }
        }
        return null;
    }

    private static String getCallerPackageName(StackTraceElement callerElement) {
        String className = callerElement.getClassName();
        if(className == null || className.isEmpty()) {
            return "";
        }
        int lastDot = className.lastIndexOf('.');
        return lastDot > 0 ? className.substring(0, lastDot) : "";
    }

    private static String getCallerClassName() {
        StackTraceElement callerElement = getCallerStackTraceElement();
        return callerElement != null ? callerElement.getClassName() : "Unknown";
    }

    private static String getCallerMethodName() {
        StackTraceElement callerElement = getCallerStackTraceElement();
        return callerElement != null ? callerElement.getMethodName() : "Unknown";
    }

    private static int getCallerLineNumber() {
        StackTraceElement callerElement = getCallerStackTraceElement();
        return callerElement != null ? callerElement.getLineNumber() : -1;
    }

    private static void debugPrint(String message) {
        String coloredMessage = DEBUG_PREFIX + message;
        System.out.println(coloredMessage);
    }
}