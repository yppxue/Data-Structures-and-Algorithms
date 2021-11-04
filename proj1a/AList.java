/** Array based list.
 *  @author Josh Hug
 */

public class AList <Item> {
    private int size;
    private Item[] items;

    /** Creates an empty list. */
    public AList() {
        size = 0;
        items = (Item[]) new Object[100];
    }

    /** Inserts X into the front of the list. */
    public void addFirst(Item x) {
        Item[] a = (Item[]) new Object[items.length];
        a[0] = x;
        System.arraycopy(items, 0, a, 1, size);
        size += 1;
        items = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length){
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    /** resize */
    public void resize (int capacity){
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
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
        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque
     * If no such item exists, returns null. */
    public Item removeFirst() {
        if (size == 0){
            return null;
        }
        Item[] a = (Item[]) new Object[items.length];
        Item x = items[0];
        System.arraycopy(items, 1, a, 0, size - 1);
        size -= 1;
        items = a;
        return x;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item delItem = items[size - 1];
        size = size - 1;
        return delItem;
    }

} 