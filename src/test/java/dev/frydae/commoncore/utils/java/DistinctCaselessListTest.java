package dev.frydae.commoncore.utils.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistinctCaselessListTest {
    @Test
    public void testAdd() {
        DistinctCaselessList list = new DistinctCaselessList();

        assertTrue(list.add("fishsticks"));
        assertFalse(list.add("fishsticks"));
        assertFalse(list.add("Fishsticks"));
    }

    @Test
    public void testGetMissing() {
        DistinctCaselessList bigger = new DistinctCaselessList();
        bigger.addAll("first", "second", "third", "fourth");

        DistinctCaselessList smaller = new DistinctCaselessList();
        smaller.addAll("first", "third");

        assertEquals(2, bigger.getMissing(smaller).size());
        assertTrue(bigger.getMissing(smaller).containsAll("second", "fourth"));
    }
}
