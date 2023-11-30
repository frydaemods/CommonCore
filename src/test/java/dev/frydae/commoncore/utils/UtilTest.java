package dev.frydae.commoncore.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {
    @Test
    public void testFormatMessage() {
        String input = "&aThis &is \\&a test &message";
        String expected = "§aThis &is &a test §message";

        assertEquals(expected, Util.color(input));
    }
}
