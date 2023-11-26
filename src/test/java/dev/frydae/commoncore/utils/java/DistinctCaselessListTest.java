package dev.frydae.commoncore.utils.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistinctCaselessListTest {
    @Test
    public void testAdd() {
        DistinctCaselessList list = new DistinctCaselessList();

        assertTrue(list.add("fishsticks"));
        assertFalse(list.add("fishsticks"));
        assertFalse(list.add("Fishsticks"));
    }
}
