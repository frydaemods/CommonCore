package dev.frydae.beguild.exceptions;


import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public final class ExceptionUtils {
    private static final ThreadLocal<Class<?>> sourceClass = new ThreadLocal<>();

    private static Logger getLogger() {
        if (sourceClass.get() == null) {
            sourceClass.set(ExceptionUtils.class);
        }

        return LoggerFactory.getLogger(sourceClass.get());
    }

    public static void abort() {
        setLoggerSource();

        sneakyThrows(new LoggableException("Aborting... Someone doesn't care enough to add a message."));
    }

    public static void abort(String message, Level level, Object... args) {
        setLoggerSource();

        if (args != null && args.length > 0) {
            message = message.formatted(args);
        }

        sneakyThrows(new LoggableException(message, level));
    }

    public static void abort(String message) {
        setLoggerSource();

        abort(message, Level.ERROR);
    }

    public static void abort(String message, Object... args) {
        setLoggerSource();

        abort(message, Level.ERROR, args);
    }

    public static void abort(Runnable onAbort, String message, Object... args) {
        setLoggerSource();

        abort(onAbort, message, Level.ERROR, args);
    }

    public static void abort(Runnable onAbort, String message, Level level, Object... args) {
        setLoggerSource();

        onAbort.run();

        abort(message.formatted(args), level);
    }

    public static void verifyTrue(boolean condition, String message, Object... args) {
        setLoggerSource();

        if (!condition) {
            abort(message, args);
        }
    }

    public static void verifyFalse(boolean condition, String message, Object... args) {
        setLoggerSource();

        if (condition) {
            abort(message, args);
        }
    }

    public static void verifyNull(@Nullable Object object, String message, Object... args) {
        setLoggerSource();

        verifyTrue(object == null, message, args);
    }

    public static void verifyNonNull(Object object, String message, Object... args) {
        setLoggerSource();

        verifyTrue(object != null, message, args);
    }

    public static void verifyNotEquals(@Nullable Object object, @Nullable Object other, String message, Object... args) {
        setLoggerSource();

        if (object == null || other == null) {
            boolean bothNull = object == null && other == null;

            verifyFalse(bothNull, message, args);
        } else {
            verifyFalse(object.equals(other), message, args);
        }
    }

    public static void verifyEquals(Object object, Object other, String message, Object... args) {
        setLoggerSource();

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

    @SneakyThrows(ClassNotFoundException.class) // This should never happen because we're getting the class from the stack trace
    private static void setLoggerSource() {
        // The stack trace index is 3 because the stack trace is:
        // 0: Thread.getStackTrace()
        // 1: ExceptionUtils.setLoggerSource()
        // 2: ExceptionUtils.abort()
        // 3: The method that called ExceptionUtils.abort()
        int stackTraceIndex = 3;

        if (sourceClass.get() == null) {
            String className = Thread.currentThread().getStackTrace()[stackTraceIndex].getClassName();

            sourceClass.set(Class.forName(className));
        }
    }

    @SuppressWarnings("RedundantTypeArguments")
    public static void sneakyThrows(Throwable t) {
        if (t instanceof LoggableException loggableException) {
            logException(loggableException);

            sourceClass.remove();
        }

        ExceptionUtils.<RuntimeException>superSneaky(t);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void superSneaky(Throwable t) throws T {
        throw (T) t;
    }
}
