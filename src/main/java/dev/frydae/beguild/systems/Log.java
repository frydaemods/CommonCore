package dev.frydae.beguild.systems;

import dev.frydae.beguild.BeGuildCommon;
import dev.frydae.beguild.utils.Patterns;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;
import java.util.logging.Logger;

public class Log {
    public static void log(String message) {
        info(message);
    }

    public static void info(String message) {
        for (String s : Patterns.NEWLINE.split(message)) {
            BeGuildCommon.getLogger().info(s);
        }
    }

    public static void warn(String message) {
        for (String s : Patterns.NEWLINE.split(message)) {
            BeGuildCommon.getLogger().warn(s);
        }
    }

    public static void severe(String message) {
        severe(Patterns.NEWLINE.split(message));
    }

    public static void severe(String[] lines) {
        Arrays.stream(lines).forEach(line -> BeGuildCommon.getLogger().error(line));
    }

    public static void error(String message) {
        severe(message);
    }

    public static void exception(String msg) {
        exception(new Throwable(msg));
    }

    public static void exception(Throwable e) {
        exception(e.getMessage(), e);
    }

    public static void exception(String msg, Throwable e) {
        if (msg != null) {
            severe(msg);
        }
        severe(ExceptionUtils.getStackFrames(e));
    }

    public static void exception(Throwable dbg, int lines) {
        if (dbg == null) {
            return;
        }
        Logger.getGlobal().severe(dbg.getMessage());
        StackTraceElement current = new Throwable().getStackTrace()[1];
        Logger.getGlobal().severe("c: " + current.getClassName() + ":" + current.getLineNumber());

        StackTraceElement[] stack = dbg.getStackTrace();
        for (int i = 0; i < lines && i < stack.length; i++) {
            StackTraceElement cur = stack[i];
            Logger.getGlobal().severe("  " + cur);
        }
    }
}
