package dev.frydae.commoncore.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @Test
    public void testFormatMessage() {
        String input = "&aThis &is \\&a test &message";
        String expected = "§aThis &is &a test §message";

        assertEquals(expected, Utils.color(input));
    }
}
