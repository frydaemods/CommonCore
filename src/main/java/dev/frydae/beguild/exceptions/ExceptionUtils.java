package dev.frydae.beguild.exceptions;


import com.mojang.logging.LogUtils;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.event.Level;

public final class ExceptionUtils {
    @Getter private static final Logger logger = LogUtils.getLogger();

    public static void abort() {
        sneakyThrows(new LoggableException("Aborting... Someone doesn't care enough to add a message."));
    }

    public static void abort(String message, Level level, Object... args) {
        if (args != null && args.length > 0) {
            message = message.formatted(args);
        }

        sneakyThrows(new LoggableException(message, level));
    }

    public static void abort(String message) {
        abort(message, Level.ERROR);
    }

    public static void abort(String message, Object... args) {
        abort(message, Level.ERROR, args);
    }

    public static void abort(Runnable onAbort, String message, Object... args) {
        abort(onAbort, message, Level.ERROR, args);
    }

    public static void abort(Runnable onAbort, String message, Level level, Object... args) {
        onAbort.run();

        abort(message.formatted(args), level);
    }

    public static void verifyTrue(boolean condition, String message, Object... args) {
        if (!condition) {
            abort(message, args);
        }
    }

    public static void verifyFalse(boolean condition, String message, Object... args) {
        if (condition) {
            abort(message, args);
        }
    }

    public static void verifyNull(@Nullable Object object, String message, Object... args) {
        verifyTrue(object == null, message, args);
    }

    public static void verifyNonNull(Object object, String message, Object... args) {
        verifyTrue(object != null, message, args);
    }

    public static void verifyNotEquals(@Nullable Object object, @Nullable Object other, String message, Object... args) {
        if (object == null || other == null) {
            boolean bothNull = object == null && other == null;

            verifyFalse(bothNull, message, args);
        } else {
            verifyFalse(object.equals(other), message, args);
        }
    }

    public static void verifyEquals(Object object, Object other, String message, Object... args) {
        if (object == null || other == null) {
            boolean bothNull = object == null && other == null;

            verifyTrue(bothNull, message, args);
        } else {
            verifyTrue(object.equals(other), message, args);
        }
    }

    private static void logException(LoggableException e) {
        getLogger().atLevel(e.getLevel()).log(e.getMessage());
    }

    public static <T extends Throwable> void sneakyThrows(Throwable t) {
        if (t instanceof LoggableException loggableException) {
            logException(loggableException);
        }

        ExceptionUtils.<RuntimeException>superSneaky(t);
    }

    private static <T extends Throwable> T superSneaky(Throwable t) throws T {
        throw (T) t;
    }
}
