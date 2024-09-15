package com.goshante.ppotatoex.util;
import io.netty.handler.logging.LogLevel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class log
{
    public static level MinLogLevel = level.Debug;

    public enum level
    {
        Debug(0),
        Info(1),
        Warning(2),
        Critical(3),
        Fatal(4);

        private final int value;
        private level(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }

        public String stringify()
        {
            switch(this)
            {
                case Debug:
                    return "DEBUG";
                case Info:
                    return "INFO";
                case Warning:
                    return "WARN";
                case Critical:
                    return "CRITICAL";
                case Fatal:
                    return "FATAL";
            }
            return "UNKNOWN";
        }
    }

    public static void write(level lvl, String msg)
    {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String formattedTime = now.format(formatter);
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement caller = stackTrace[2];

        if (lvl.getValue() >= MinLogLevel.getValue())
            System.out.println("[" + formattedTime + "] [" + lvl.stringify() + "][" + caller.getClassName() + "][" + caller.getMethodName() + ":" + caller.getLineNumber() + "]: " + msg);
    }
}
