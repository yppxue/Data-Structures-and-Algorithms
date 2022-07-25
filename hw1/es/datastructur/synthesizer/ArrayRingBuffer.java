package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;

    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (this.isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        last = last % capacity();
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (this.isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T dequeueItem = rb[first];
        rb[first] = null;
        fillCount -= 1;
        first += 1;
        first = first % capacity();
        return dequeueItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (this.isEmpty()){
            throw new RuntimeException("No item to peek");
        }

        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    public void printDeque(){
        for (int i = 0; i < rb.length; i++) {
            System.out.print(rb[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        public ArrayRingBufferIterator(){
            pos = 0;
        }
        public boolean hasNext(){
            return pos < capacity();
        }
        public T next(){
            T returnItem = rb[pos];
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o){
        if (rb.getClass() != rb.getClass()){
            return false;
        }
        ArrayRingBuffer <T> other = (ArrayRingBuffer<T>) o;
        return this.capacity() == other.capacity() && this.fillCount() == other.fillCount() && rb[first] == other.rb[other.first];
    }


}
    // TODO: Remove all comments that say TODO when you're done.
