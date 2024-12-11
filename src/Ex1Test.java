import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class Ex1Test {
    @Test
    void computeNumberTest() {
        String s2 = "1011b2";
        int v = Ex1.number2Int(s2);
        assertEquals(v, 11);
        String s10 = "1011bA";
        v = Ex1.number2Int(s10);
        s2 = Ex1.int2Number(v, 2);
        int v2 = Ex1.number2Int(s2);
        assertEquals(v, v2);
        assertTrue(Ex1.equals(s10, s2));
    }

    @Test
    void isBasisNumberTest() {
        String[] good = {"1", "1b2", "01b2", "123bA", "ABbG", "0bA"};
        for (int i = 0; i < good.length; i = i + 1) {
            boolean ok = Ex1.isNumber(good[i]);
            assertTrue(ok);
        }
        String[] not_good = {"b2", "2b2", "1G3bG", " BbG", "0bbA", "abB", "!@b2", "A", "1bb2"};
        for (int i = 0; i < not_good.length; i = i + 1) {
            boolean not_ok = Ex1.isNumber(not_good[i]);
            assertFalse(not_ok);
        }
    }

    @Test
    void int2NumberTest() {
        assertEquals("0b2", Ex1.int2Number(0, 2));
        assertEquals("101b2", Ex1.int2Number(5, 2));
        assertEquals("123b10", Ex1.int2Number(123, 10));
        assertEquals("A1b16", Ex1.int2Number(161, 16));
        assertEquals("1F4b16", Ex1.int2Number(500, 16));
        assertEquals("", Ex1.int2Number(-1, 10));
        assertEquals("", Ex1.int2Number(10, 1));
        assertEquals("", Ex1.int2Number(10, 17));
    }

    @Test
    void maxIndexTest() {
        String[] validArray = {"101b2", "111b2", "100b2"};
        assertEquals(1, Ex1.maxIndex(validArray));
        String[] duplicateMaxArray = {"500b10", "1F4bG", "1F4bG", "499b10"};
        assertEquals(0, Ex1.maxIndex(duplicateMaxArray));
        String[] mixedArray = {"-1", "123bZ", "0b10", "1B2bG"};
        assertEquals(3, Ex1.maxIndex(mixedArray));
        String[] invalidArray = {"-1", "123bZ", "G1bG"};
        assertEquals(-1, Ex1.maxIndex(invalidArray));

    }
    @Test
    void equalsTest() {
        assertTrue(Ex1.equals("10b2", "2b10"));
        assertFalse(Ex1.equals("10b2", "abc"));
        assertFalse(Ex1.equals(null, "101111b2"));
        assertFalse(Ex1.equals("10b2", "111b2"));
        assertTrue(Ex1.equals("111b2", "111b2"));
        assertFalse(Ex1.equals("", "11111b2"));
    }
}

