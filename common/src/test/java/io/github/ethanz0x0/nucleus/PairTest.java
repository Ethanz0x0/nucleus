package io.github.ethanz0x0.nucleus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PairTest {

    @Test
    public void equalsTest() {
        Pair<String, Integer> pair1 = new Pair<>("a", 2);
        Pair<String, Integer> pair2 = new Pair<>("a", 2);
        Pair<String, Integer> pair3 = new Pair<>("b", 3);

        assertEquals(pair1, pair2);
        assertNotEquals(pair1, pair3);
    }

    @Test
    public void toStringTest() {
        Pair<String, Integer> pair = new Pair<>("a", 2);
        String expected = "Pair{key=a, value=2}";
        assertEquals(expected, pair.toString());
    }
}
