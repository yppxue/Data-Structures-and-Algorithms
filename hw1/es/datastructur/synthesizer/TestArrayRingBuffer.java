package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void Test1() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        arb.printDeque();
    }
    @Test
    public void Test2() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        arb.dequeue();
        arb.printDeque();
    }
    @Test
    public void Test3() {
        ArrayRingBuffer arb = new ArrayRingBuffer(8);
        arb.enqueue(1);
        arb.enqueue(2);
        //arb.dequeue();
        arb.dequeue();
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.printDeque();
    }
    @Test
    public void Test4() {
        ArrayRingBuffer arb = new ArrayRingBuffer(8);
        ArrayRingBuffer arb2 = new ArrayRingBuffer(8);
        arb.enqueue(1);
        arb.enqueue(2);
        arb2.enqueue(1);
        arb2.enqueue(2);
        assertTrue(arb.equals(arb2));
    }
}
