package dev.frydae.beguild.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionUtilsTest {
    @Test
    public void testVerifyTrue() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyTrue(true, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyTrue(false, "Test"));
    }

    @Test
    public void testVerifyFalse() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyFalse(false, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyFalse(true, "Test"));
    }

    @Test
    public void testVerifyNull() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyNull(null, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyNull(new Object(), "Test"));
    }

    @Test
    public void testVerifyNotNull() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyNonNull(new Object(), "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyNonNull(null, "Test"));
    }

    @Test
    public void testVerifyNotEquals() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyNotEquals(1, 2, "Test"));
        assertDoesNotThrow(() -> ExceptionUtils.verifyNotEquals(null, 1, "Test"));
        assertDoesNotThrow(() -> ExceptionUtils.verifyNotEquals(1, null, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyNotEquals(null, null, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyNotEquals(1, 1, "Test"));
    }

    @Test
    public void testVerifyEquals() {
        assertDoesNotThrow(() -> ExceptionUtils.verifyEquals(1, 1, "Test"));
        assertDoesNotThrow(() -> ExceptionUtils.verifyEquals(null, null, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyEquals(1, 2, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyEquals(null, 1, "Test"));
        assertThrows(LoggableException.class, () -> ExceptionUtils.verifyEquals(1, null, "Test"));
    }
}
