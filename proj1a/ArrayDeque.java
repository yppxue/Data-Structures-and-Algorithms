/** Array based list.
 *  @author Josh Hug
 */

public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (Item[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    /** Inserts X into the front of the list. */
    public void addFirst(Item x) {
        if (size == items.length || (nextFirst - nextLast) == 1){
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else{
            nextFirst = nextFirst - 1;
        }
        size += 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length || (nextFirst - nextLast) == 1){
            resize(items.length * 2);
        }
        items[nextLast] = x;
        if (nextLast == items.length - 1){
            nextLast = 0;
        }else{
            nextLast = nextLast + 1;
        }
        size += 1;
    }

    /** resize */
    public void resize (int capacity){
        Item[] a = (Item[]) new Object[capacity];
        if (nextFirst < nextLast){
            if (capacity > items.length) {
                System.arraycopy(items, nextFirst + 1, a, nextFirst + 1, size);
            }else{
                //assume reducing size and place the first item in position 1;
                System.arraycopy(items, nextFirst + 1, a,  1, size);
                nextFirst = 0;
                nextLast = size + 1;
            }
            items = a;
        }else{
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextFirst+1, a, capacity - (items.length - nextFirst - 1), items.length - nextFirst - 1);
            nextFirst = capacity - (items.length - nextFirst - 1) - 1;
            items = a;
        }

    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque(){
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        int lastIdx = this.getLastIdx();
        return items[lastIdx];
    }

    /** Returns the item from the front of the list. */
    public Item getFirst() {
        int firstIdx = this.getFirstIdx();
        return items[firstIdx];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        int firstIdx = this.getFirstIdx();
        if (firstIdx + i >= items.length){
        // need to loop back to the front of the array
            return items[firstIdx + i - items.length];
        }
        return items[firstIdx + i];
    }
    /** Get front of deque index */
    public int getFirstIdx(){
        if (nextFirst == items.length - 1){
            return 0;
        }else{
            return nextFirst + 1;
        }
    }

    /** Get front of deque index */
    public int getLastIdx(){
        if (nextLast == 0){
            return items.length - 1;
        }else{
            return nextLast - 1;
        }
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque
     * If no such item exists, returns null. */
    public Item removeFirst() {
        Item x;
        int firstIdx = this.getFirstIdx();
        x = items[firstIdx];
        items[firstIdx] = null;
        size -= 1;
        nextFirst = firstIdx;
        if (size < 0.25*items.length){
            resize(items.length/2);
        }
        return x;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item x;
        int lastIdx = this.getLastIdx();
        x = items[lastIdx];
        items[lastIdx] = null;
        size -= 1;
        nextLast = lastIdx;
        if (size < 0.25*items.length){
            resize(items.length/2);
        }
        return x;
    }

} 