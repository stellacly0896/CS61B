public class ArrayDeque < T > {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    //Creates an empty array deque.
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    //Resize the array
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;

        nextFirst = capacity - 1;
        nextLast = size;
    }


    //Adds an item of type T to the front of the deque.
    public void addFirst(T x) {

        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = x;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = x;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    /**Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. **/
    public void printDeque() {
        for (int i = 0 ; i < items.length ; i++) {
            System.out.print(items[i] + " ");
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }

        T firstItem = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        size--;

        return firstItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T lastItem = items[nextLast - 1];
        items[nextLast - 1] = null;
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
        size--;

        if (items.length >= 16 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return lastItem;
    }

    //Gets the item at the given index, where 0 is the front,
    // 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index > items.length - 1) {
            return null;
        } else {
            return items[index];
        }
    }
}
