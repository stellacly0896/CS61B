public class ArrayDeque<Item>{
    private Item[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    //Creates an empty array deque.
    public ArrayDeque(){
        items = (Item[]) new Object[8];
        nextFirst = 0;
        nextLast = 0;
        size = 0;
    }

    //Resize the array
    private void resize(int capacity){
        Item[] newItems = (Item[]) new Object[capacity];
        System.arraycopy(items,0,newItems,0,size);
        items = newItems;

        nextFirst = capacity - 1;
        nextLast = size;
    }


    //Adds an item of type T to the front of the deque.
    public void addFirst(Item x){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextFirst] = x;
        if (nextFirst == 0){
            nextFirst = this.size - 1;
        }else{
            nextFirst--;
        }
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(Item x){
        if (size == items.length){
            resize(items.length * 2);
        }

        items[nextLast] = x;
        if (nextLast == this.size - 1){
            nextLast = 0;
        }else{
            nextLast++;
        }
        size += 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size(){
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
    public void printDeque(){
        for(int i=0;i<this.size;i++){
            System.out.print(items[i] + " ");
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public Item removeFirst(){
        if (this.isEmpty()){
            return null;
        }
        Item firstItem = items[nextFirst+1];
        items[nextFirst+1] = null;
        if (nextFirst == this.size -1){
            nextFirst = 0;
        }else{
            nextFirst++;
        }size--;

        if (items.length >= 16 && (double) size /items.length < 0.25){
            resize(items.length/2);
        }
        return firstItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public Item removeLast(){
        if (this.isEmpty()){
            return null;
        }
        Item lastItem = items[nextLast-1];
        items[nextLast-1] = null;
        if (nextLast == 0){
            nextLast = this.size-1;
        }else{
            nextLast--;
        }
        size--;
        if (items.length >= 16 && (double) size /items.length < 0.25){
            resize(items.length/2);
        }
        return lastItem;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public Item get(int index){
        if (index > this.size-1){
            return null;
        }else{
            return items[index];
        }
    }
}
