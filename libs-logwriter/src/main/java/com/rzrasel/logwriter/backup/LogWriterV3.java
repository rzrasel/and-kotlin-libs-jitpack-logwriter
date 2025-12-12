package com.rzrasel.logwriter.backup;

import android.annotation.SuppressLint;

public class LogWriterV3 {
    private static final String FULL_CLASS_NAME = LogWriter.class.getName();
    private static final String CLASS_NAME = LogWriter.class.getSimpleName();
    private static final String DEBUG_PREFIX = "DEBUG_LOG_WRITER_PRINT: ";
    //private static final String LOG_FORMAT = "Message: %s | Class: %s.%s | Method: %s | Line: %d";
    private static final String LOG_FORMAT = "Message: %s - Class Name: %s%s - Method Name: %s - Line Number: %d";

    public LogWriterV3() {
        debugPrint("Initialized LogWriterV2 for class: " + getCallerClassName());
    }

    public static void log(String message, boolean isDebug) {
        if (isDebug) {
            log(message);
        }
    }

    public static void log(String message) {
        StackTraceElement callerStackTraceElement = getCallerStackTraceElement();
        if (callerStackTraceElement == null) {
            debugPrint("[Unknown Caller] Unable to determine caller information for message: " + message);
            return;
        }

        //debugPrint(stackTraceElement.toString());
        String packageName = getCallerPackageName(callerStackTraceElement);
        String className = callerStackTraceElement.getClassName();
        String methodName = callerStackTraceElement.getMethodName();
        int lineNumber = callerStackTraceElement.getLineNumber();

        @SuppressLint("DefaultLocale")
        String logMessage = String.format(
                LOG_FORMAT,
                message,
                packageName.isEmpty() ? "(default package)." : packageName + ".",
                className,
                methodName,
                lineNumber
        );

        debugPrint(logMessage);
    }

    private static StackTraceElement getCallerStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        //debugPrint(stackTrace.length + " " + CLASS_NAME);
        boolean isClassFound = false;
        int index = 0;
        String targetClassName = FULL_CLASS_NAME;
        for(int i = 0; i < stackTrace.length; i++) {
            //debugPrint(i + " - " + stackTrace[i].getClassName() + " = " + targetClassName);
            //debugPrint(stackTrace[i].toString());
            if(stackTrace[i].getClassName().equalsIgnoreCase(targetClassName)) {
                //debugPrint(stackTrace[i].getClassName() + " = " + FULL_CLASS_NAME);
                isClassFound = true;
                //return stackTrace[index];
            }
            /*if(isClassFound && !stackTrace[i].getClassName().equalsIgnoreCase(targetClassName)) {
                index = i;
                break;
            }*/
            if(isClassFound && !stackTrace[i].getClassName().equalsIgnoreCase(targetClassName)) {
                index = (i >= stackTrace.length) ? (stackTrace.length - 1) : i;
                return stackTrace[index];
            }
        }
        /*if(isClassFound) {
            //index;
            if(index >= stackTrace.length) {
                index = stackTrace.length - 1;
            }
            return stackTrace[index];
        }*/
        return null;
    }

    public static void logV1(String message) {
        StackTraceElement callerElement = getCallerStackTraceElement();
        if (callerElement == null) {
            debugPrint("Unable to determine caller information for message: " + message);
            return;
        }

        String packageName = getCallerPackageName(callerElement);
        String className = callerElement.getClassName();
        String methodName = callerElement.getMethodName();
        int lineNumber = callerElement.getLineNumber();

        @SuppressLint("DefaultLocale")
        String logMessage = String.format(
                LOG_FORMAT,
                message,
                packageName.isEmpty() ? "(default package)." : packageName + ".",
                className,
                methodName,
                lineNumber
        );

        debugPrint(logMessage);
    }

    private static StackTraceElement getCallerStackTraceElementV1() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // The first element is the current method, the second is the log method, and the third is the caller.
        if (stackTrace.length > 3) {
            return stackTrace[3];
        }
        return null;
    }

    private static String getCallerPackageName(StackTraceElement callerElement) {
        String className = callerElement.getClassName();
        if (className == null || className.isEmpty()) {
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
        System.out.println(DEBUG_PREFIX + message);
    }
}