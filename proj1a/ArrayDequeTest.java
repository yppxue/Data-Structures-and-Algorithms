import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests the ArrayDeque class.
 *  @author Josh Hug
 */

public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        assertEquals(0, L.size());
    }

    @Test
    public void testAddAndSize() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
      //  L.addLast(99);
        L.addFirst(36);
        L.addFirst(32);
        L.printDeque();
        assertEquals(2, L.size());
    }


    @Test
    public void testAddAndGetLast() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer)99, L.getLast());
        L.addLast(36);
        L.printDeque();
        assertEquals((Integer)36, L.getLast());
    }


    @Test
    public void testResize1() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer) 99, L.getLast());
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.addLast(36);
        L.printDeque();
        assertEquals((Integer)99, L.getFirst());
        assertEquals((Integer)36, L.getLast());
    }

    @Test
    public void testResize2() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer) 99, L.getLast());
        for (int i = 0; i < 8; i++){
            L.addLast(36);
        }
        for (int i = 0; i < 8; i++){
            L.removeLast();
            L.printDeque();
        }
    }

    @Test
    public void testResize3() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer) 99, L.getLast());
        for (int i = 0; i < 8; i++){
            L.addLast(36);
        }
        for (int i = 0; i < 8; i++){
            L.removeFirst();
            L.printDeque();
        }
    }

    @Test
    public void testResize4() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer) 99, L.getLast());
        for (int i = 0; i < 32; i++){
            L.addLast(36);
        }
        for (int i = 0; i < 30; i++){
            L.removeFirst();
            L.printDeque();
        }
    }


    @Test
    public void testRemove() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        assertEquals((Integer)99, L.getLast());
        L.addLast(36);
        assertEquals((Integer)36, L.getLast());
        L.removeLast();
        L.addFirst(44);
        L.printDeque();
        assertEquals((Integer)44, L.removeFirst());
        L.printDeque();
        assertEquals((Integer)99, L.getLast());
        L.addLast(100);
        assertEquals((Integer)100, L.getLast());
        assertEquals(2, L.size());
    }

    /** Tests insertion of a large number of items.*/
    @Test
    public void testMegaInsert() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }
        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", ArrayDequeTest.class);
    }
}