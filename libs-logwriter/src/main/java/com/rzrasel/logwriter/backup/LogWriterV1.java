package com.rzrasel.logwriter.backup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rz Rasel on 01-Aug-16.
 */
public class LogWriterV1 {
    public static boolean isDebug = true;

    public static void Log(String argMessage) {
        //|------------------------------------------------------------|
        if (!isDebug) {
            return;
        }
        String buildMessage = "";
        buildMessage = "Message:- " + argMessage + "; "
                + "Class Name:- " + getCallerClassName() + "; "
                + "Method Name:- " + getCallerMethodName() + "; "
                + "Line Number:- " + getCallerLineNumber() + "; "
                + "File Name:- " + getCallerFileName();
        System.out.println("DEBUG_LOG_PRINT_WRITER:- " + buildMessage);
        //|------------------------------------------------------------|
    }

    public static void Log(String argTag, String argMessage) {
        //|------------------------------------------------------------|
        if (!isDebug) {
            return;
        }
        argTag = argTag.toUpperCase();
        argTag = "DEBUG_LOG_PRINT_WRITER_" + argTag.replaceAll("\\s+", "_");
        String buildMessage = "";
        buildMessage = "Message:- " + argMessage + " - "
                + "Class Name:- " + getCallerClassName() + "; "
                + "Method Name:- " + getCallerMethodName() + "; "
                + "Line Number:- " + getCallerLineNumber() + "; "
                + "File Name:- " + getCallerFileName();
        System.out.println(argTag + ":- " + buildMessage);
        //|------------------------------------------------------------|
        /*int pid = android.os.Process.myPid();
        File outputFile = new File(Environment.getExternalStorageDirectory() + "/logs/logcat.txt");
        try {
            String command = "logcat | grep " + pid + " > " + outputFile.getAbsolutePath();
            Process p = Runtime.getRuntime().exec("su");
            OutputStream os = p.getOutputStream();
            os.write((command + "\n").getBytes("ASCII"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //|------------------------------------------------------------|
    }

    public static String getCallerClassName() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        /*for (int i = 0; i < stackTraceElements.length; i++) {
            System.out.println("DEBUG_TRACE: " + stackTraceElements[i].getClassName());
        }*/
        //for (StackTraceElement element : stackTrace)
        if (stackTraceElements.length >= 4) {
            return Thread.currentThread().getStackTrace()[4].getClassName();
        } else if (stackTraceElements.length == 3) {
            return Thread.currentThread().getStackTrace()[3].getClassName();
        } else if (stackTraceElements.length == 2) {
            return Thread.currentThread().getStackTrace()[2].getClassName();
        }
        return "";
    }

    public static String getCallerClassNameOnly() {
        String fullClassName = "";
        String className = "";
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 4) {
            fullClassName = Thread.currentThread().getStackTrace()[4].getClassName();
            className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            return className;
        } else if (stackTraceElements.length == 3) {
            fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
            className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            return className;
        } else if (stackTraceElements.length == 2) {
            fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            return className;
        }
        return className;
    }

    public static String getCallerMethodName() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 4) {
            return Thread.currentThread().getStackTrace()[4].getMethodName();
        } else if (stackTraceElements.length == 3) {
            return Thread.currentThread().getStackTrace()[3].getMethodName();
        } else if (stackTraceElements.length == 2) {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        }
        return "";
    }

    public static String getCallerLineNumber() {
        /*String callerClassName = new Exception().getStackTrace()[1].getClassName();
        String calleeClassName = new Exception().getStackTrace()[0].getClassName();*/
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        /*for (StackTraceElement element : stackTraceElements) {
            System.out.println("LINE_NUMBER: " + element.getLineNumber());
        }*/
        //StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 4) {
            return Thread.currentThread().getStackTrace()[4].getLineNumber() + "";
        } else if (stackTraceElements.length == 3) {
            return Thread.currentThread().getStackTrace()[3].getLineNumber() + "";
        } else if (stackTraceElements.length == 2) {
            return Thread.currentThread().getStackTrace()[2].getLineNumber() + "";
        }
        return "";
    }

    public static String getCallerFileName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 4) {
            return stackTraceElements[4].getFileName() + "";
        } else if (stackTraceElements.length == 3) {
            return stackTraceElements[3].getFileName() + "";
        } else if (stackTraceElements.length == 2) {
            return stackTraceElements[2].getFileName() + "";
        }
        /*String retVal = "";
        for (StackTraceElement element : stackTraceElements) {
            retVal += element.getFileName() + " ";
        }
        return retVal;*/
        return "";
    }

    public static String getCallerClassNameTemp() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stackTraceElements.length; i++) {
            StackTraceElement ste = stackTraceElements[i];
            /*if (!ste.getClassName().equals(KDebug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }*/
        }
        return null;
    }


    public static class ArrayHandle {
        public static Object[] reverse(Object[] arr) {
            List<Object> list = Arrays.asList(arr);
            Collections.reverse(list);
            return list.toArray();
        }
    }
}