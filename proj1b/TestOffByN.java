import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy5 = new OffByN(5);

    // Your tests go here.
    @Test
    public void testOffByN(){
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('r', 'w'));
        assertFalse(offBy5.equalChars('a', 'e'));
    }
}