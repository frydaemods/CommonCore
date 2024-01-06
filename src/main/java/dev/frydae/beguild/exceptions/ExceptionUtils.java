package dev.frydae.beguild.exceptions;


import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Objects;

public final class ExceptionUtils {
    private static final ThreadLocal<Class<?>> sourceClass = new ThreadLocal<>();

    private static Logger getLogger() {
        if (sourceClass.get() == null) {
            sourceClass.set(ExceptionUtils.class);
        }

        return LoggerFactory.getLogger(sourceClass.get());
    }

    // region Abort Methods
    /**
     * Aborts the current thread with a default message
     */
    public static void abort() {
        setLoggerSource();

        sneakyThrows(new LoggableException("Aborting... Someone doesn't care enough to add a message."));
    }

    /**
     * Aborts the current thread with a message.
     *
     * @param message the message to log
     * @param level the log level
     * @param args the arguments to format the message with
     */
    public static void abort(String message, Level level, Object... args) {
        setLoggerSource();

        if (args != null && args.length > 0) {
            message = message.formatted(args);
        }

        sneakyThrows(new LoggableException(message, level));
    }

    /**
     * Aborts the current thread with a message.
     * The log level is set to {@link Level#ERROR}.
     *
     * @param message the message to log
     */
    public static void abort(String message) {
        setLoggerSource();

        abort(message, Level.ERROR);
    }

    /**
     * Aborts the current thread with a message.
     * The log level is set to {@link Level#ERROR}.
     *
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void abort(String message, Object... args) {
        setLoggerSource();

        abort(message, Level.ERROR, args);
    }

    /**
     * Aborts the current thread with a message.
     * The log level is set to {@link Level#ERROR}.
     *
     * @param onAbort a {@link Runnable} to run before aborting
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void abort(Runnable onAbort, String message, Object... args) {
        setLoggerSource();

        abort(onAbort, message, Level.ERROR, args);
    }

    /**
     * Aborts the current thread with a message.
     *
     * @param onAbort a {@link Runnable} to run before aborting
     * @param message the message to log
     * @param level the log level
     * @param args the arguments to format the message with
     */
    public static void abort(Runnable onAbort, String message, Level level, Object... args) {
        setLoggerSource();

        onAbort.run();

        abort(message.formatted(args), level);
    }
    // endregion

    // region Verification Methods
    /**
     * Verifies that a condition is true.
     * <p>
     * If the condition is false, the current thread is aborted.
     *
     * @param condition the condition to verify
     */
    public static void verifyTrue(boolean condition) {
        setLoggerSource();

        if (!condition) {
            abort();
        } else {
            sourceClass.remove();
        }
    }

    /**
     * Verifies that a condition is true.
     * <p>
     * If the condition is false, the current thread is aborted. and a message is logged.
     *
     * @param condition the condition to verify
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyTrue(boolean condition, String message, Object... args) {
        setLoggerSource();

        if (!condition) {
            abort(message, args);
        } else {
            sourceClass.remove();
        }
    }

    /**
     * Verifies that a condition is false.
     * <p>
     * If the condition is true, the current thread is aborted.
     *
     * @param condition the condition to verify
     */
    public static void verifyFalse(boolean condition) {
        setLoggerSource();

        if (condition) {
            abort();
        } else {
            sourceClass.remove();
        }
    }

    /**
     * Verifies that a condition is false.
     * <p>
     * If the condition is true, the current thread is aborted. and a message is logged.
     *
     * @param condition the condition to verify
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyFalse(boolean condition, String message, Object... args) {
        setLoggerSource();

        if (condition) {
            abort(message, args);
        } else {
            sourceClass.remove();
        }
    }

    /**
     * Verifies that an object is null.
     * <p>
     * If the object is not null, the current thread is aborted.
     *
     * @param object the object to verify
     */
    public static void verifyNull(@Nullable Object object) {
        setLoggerSource();

        verifyTrue(object == null);
    }

    /**
     * Verifies that an object is null.
     * <p>
     * If the object is not null, the current thread is aborted. and a message is logged.
     *
     * @param object the object to verify
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyNull(@Nullable Object object, String message, Object... args) {
        setLoggerSource();

        verifyTrue(object == null, message, args);
    }

    /**
     * Verifies that an object is not null.
     * <p>
     * If the object is null, the current thread is aborted.
     *
     * @param object the object to verify
     */
    public static void verifyNonNull(Object object) {
        setLoggerSource();

        verifyTrue(object != null);
    }

    /**
     * Verifies that an object is not null.
     * <p>
     * If the object is null, the current thread is aborted.
     *
     * @param object the object to verify
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyNonNull(Object object, String message, Object... args) {
        setLoggerSource();

        verifyTrue(object != null, message, args);
    }

    /**
     * Verifies that two objects are equal.
     * <p>
     * If the objects are not equal, the current thread is aborted.
     *
     * @param object the object to verify
     * @param other the object to compare to
     */
    public static void verifyNotEquals(@Nullable Object object, @Nullable Object other) {
        setLoggerSource();

        verifyTrue(!Objects.equals(object, other));
    }

    /**
     * Verifies that two objects are not equal.
     * <p>
     * If the objects are equal, the current thread is aborted. and a message is logged.
     *
     * @param object the object to verify
     * @param other the object to compare to
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyNotEquals(@Nullable Object object, @Nullable Object other, String message, Object... args) {
        setLoggerSource();

        verifyTrue(!Objects.equals(object, other), message, args);
    }

    /**
     * Verifies that two objects are equal.
     * <p>
     * If the objects are not equal, the current thread is aborted.
     *
     * @param object the object to verify
     * @param other the object to compare to
     */
    public static void verifyEquals(@Nullable Object object, @Nullable Object other) {
        setLoggerSource();

        verifyTrue(Objects.equals(object, other));
    }

    /**
     * Verifies that two objects are equal.
     * <p>
     * If the objects are not equal, the current thread is aborted, and a message is logged.
     *
     * @param object the object to verify
     * @param other the object to compare to
     * @param message the message to log
     * @param args the arguments to format the message with
     */
    public static void verifyEquals(@Nullable Object object, @Nullable Object other, String message, Object... args) {
        setLoggerSource();

        verifyTrue(Objects.equals(object, other), message, args);
    }
    // endregion

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
