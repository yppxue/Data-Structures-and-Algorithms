/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");

		LinkedListDeque<String> lld1 = new LinkedListDeque<>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addLast(20);
		lld1.addLast(30);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.printDeque();

		lld1.removeFirst();
		lld1.printDeque();
		// should be empty
		passed = checkSize(30, lld1.removeLast()) && passed;
		//passed = checkEmpty(true, lld1.isEmpty()) && passed;
		lld1.printDeque();
		printTestStatus(passed);

	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item */
	public static void getTest() {

		System.out.println("Running get test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addLast(20);
		lld1.addLast(30);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.printDeque();
		passed = checkSize(10, lld1.get(0)) && passed;
		passed = checkSize(20, lld1.get(1)) && passed;
		passed = checkSize(30, lld1.get(2)) && passed;
		passed = checkEmpty(true, null == lld1.get(4)) && passed;

		printTestStatus(passed);

	}

	/** Gets the item at the given index, where 0 is the front, 1 is the next item */
	public static void getRecurTest() {

		System.out.println("Running getRecur test.");

		LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
		// should be empty
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		lld1.addLast(20);
		lld1.addLast(30);
		// should not be empty
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		lld1.printDeque();
		passed = checkSize(10, lld1.getRecursive(0)) && passed;
		passed = checkSize(20, lld1.getRecursive(1)) && passed;
		passed = checkSize(30, lld1.getRecursive(2)) && passed;
		passed = checkEmpty(true, null == lld1.getRecursive(4)) && passed;

		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
		getTest();
		getRecurTest();
	}
} 