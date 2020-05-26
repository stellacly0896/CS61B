public class LinkedListDeque <T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private Node sentinel; // A circular sentinel node;
    private int size;

    //Create an empty linked list deque
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        // For an empty list, the sentinel points to itself in both directions.
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (this.isEmpty()) {
            Node newFirst = new Node(sentinel,item,sentinel);
            sentinel.prev = newFirst;
            sentinel.next = newFirst;
        } else {
            Node oldFirst = sentinel.next;
            Node newFirst = new Node(sentinel, item, oldFirst);
            sentinel.next = newFirst;
            oldFirst.prev = newFirst;
        }
        size += 1;
        }

    //Adds an item of type T to the end of the deque.
    public void addLast(T item) {
        if (this.isEmpty()) {
            Node newLast = new Node(sentinel,item,sentinel);
            sentinel.prev = newLast;
            sentinel.next = newLast;
        } else {
            Node oldLast = sentinel.prev;
            Node newLast = new Node(oldLast,item,sentinel);
            sentinel.prev = newLast;
            oldLast.next = newLast;
        }
        size += 1;

    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            Node oldFirst = sentinel.next;
            Node newFirst = oldFirst.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            size -= 1;
            return oldFirst.item;
        }
    }
    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removedLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            Node oldLast = sentinel.prev;
            Node newLast = oldLast.prev;
            sentinel.prev = newLast;
            newLast.next = sentinel;
            size -= 1;
            return oldLast.item;

        }
    }
    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index > this.size) {
            return null;
        } else {
            Node n = sentinel.next;
            for (int count = 0; count < index; count++) {
                n = n.next;
            }
            return n.item;
        }
    }

    //Use recursion to get the item at the given index
    public T getRecursion(int index) {
        if (index > this.size) {
            return null;
        } else {
            return recursionHelper(sentinel.next,index);
            }
        }

    private T recursionHelper(Node n, int index) {
        if (index==0) {
            return n.item;
        } else {
            return recursionHelper(n.next,index-1);
        }
    }
    /**
    public static void main(String[] args) {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();


        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.removedLast());
        System.out.println(test.removeFirst());
        test.printDeque();

        test.addFirst(1);
        test.addLast(2);
        test.addFirst(0);

        System.out.println(test.isEmpty());
        System.out.println(test.size());
        //test.printDeque();
        System.out.println(test.get(0));
        System.out.println(test.getRecursion(2));

    }
    **/
}


