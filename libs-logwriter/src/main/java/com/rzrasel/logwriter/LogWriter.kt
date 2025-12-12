package com.rzrasel.logwriter

import android.annotation.SuppressLint
import android.content.Context

object LogWriter {
    private val FULL_CLASS_NAME = LogWriter::class.java.name
    private val CLASS_NAME = LogWriter::class.java.simpleName
    private const val DEBUG_PREFIX = "DEBUG_LOG_WRITER_PRINT: "
    private const val LOG_FORMAT = "Message: %s - Class: %s - Method: %s - Line Number: %d | Full Class | Package: %s | Class: %s | Line Number: %d"

    init {
        debugPrint("Initialized LogWriter for class: ${getCallerClassName()}")
    }

    @JvmStatic
    fun log(message: String?) = log(message, context = null, isDebug = true)

    @JvmStatic
    fun log(message: String?, context: Context? = null, isDebug: Boolean = true) {
        if (context != null && !BuildConfig.isDebuggable(context)) return
        if (!isDebug) return

        val callerStackTraceElement = getCallerStackTraceElement()
        if (callerStackTraceElement == null) {
            debugPrint("[Unknown Caller] Unable to determine caller information for message: $message")
            return
        }

        val packageName = getCallerPackageName(callerStackTraceElement)
        val actualPackageName = packageName.ifEmpty { "(default package)" }
        val className = callerStackTraceElement.className
        val simpleClassName = className.substringAfterLast(".")
        val methodName = callerStackTraceElement.methodName
        val lineNumber = callerStackTraceElement.lineNumber

        @SuppressLint("DefaultLocale")
        val logMessage = String.format(
            LOG_FORMAT,
            message,
            simpleClassName,
            methodName,
            lineNumber,
            "$actualPackageName.",
            className,
            lineNumber
        )

        debugPrint(logMessage)
    }

    private fun getCallerStackTraceElement(): StackTraceElement? {
        val stackTrace = Thread.currentThread().stackTrace
        var isClassFound = false
        val targetClassName = FULL_CLASS_NAME

        for (stackTraceElement in stackTrace) {
            if (stackTraceElement.className.equals(targetClassName, ignoreCase = true)) {
                isClassFound = true
            }
            if (isClassFound && !stackTraceElement.className.equals(targetClassName, ignoreCase = true)) {
                return stackTraceElement
            }
        }
        return null
    }

    private fun getCallerPackageName(callerElement: StackTraceElement): String {
        val className = callerElement.className
        if (className.isNullOrEmpty()) {
            return ""
        }
        val lastDot = className.lastIndexOf('.')
        return if (lastDot > 0) className.take(lastDot) else ""
    }

    private fun getCallerClassName(): String {
        val callerElement = getCallerStackTraceElement()
        return callerElement?.className ?: "Unknown"
    }

    private fun getCallerMethodName(): String {
        val callerElement = getCallerStackTraceElement()
        return callerElement?.methodName ?: "Unknown"
    }

    private fun getCallerLineNumber(): Int {
        val callerElement = getCallerStackTraceElement()
        return callerElement?.lineNumber ?: -1
    }

    private fun debugPrint(message: String) {
        val coloredMessage = DEBUG_PREFIX + message
        println(coloredMessage)
    }
}