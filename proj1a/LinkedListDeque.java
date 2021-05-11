
public class LinkedListDeque<T> {

    /** Nested class ListNode */
    public class ListNode {
        public ListNode prev;
        public T item;
        public ListNode next;

        public ListNode(){
            prev = null;
            next = null;
            item = null;
        }

        public ListNode(T i, ListNode p, ListNode n){
            prev = p;
            next = n;
            item = i;
        }
    }

    private ListNode sentinel;
    private int size;

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    public LinkedListDeque(){
        size = 0;
        sentinel = new ListNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque<T> other){
        size = 0;
        sentinel = new ListNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        for (int i = 0; i < other.size(); i++){
            addLast(get(i));
        }
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        ListNode newNode = new ListNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        ListNode newNode = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /**  Prints the items in the deque from first to last, separated by a space.
     *   Once all the items have been printed, print out a new line.
     */
    public void printDeque(){
        ListNode p = sentinel.next;
        while (p.next != sentinel){
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        else {
            T retItem = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return retItem;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if (size == 0){
            return null;
        }
        else {
            T retItem = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return retItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index: index of a node
     * @return: T
     */
    public T get(int index){
        if (index < 0 || index > size - 1){
            return null;
        }
        else {
            ListNode p = sentinel.next;
            int count = 0;
            while (count != index){
                p = p.next;
                count ++;
            }
            return p.item;
        }
    }

    public T getRecursive(int index){
        if (index < 0 || index > size - 1){
            return null;
        }
        else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }

    public T getRecursiveHelper(ListNode node, int index){
        if (index == 0){
            return node.item;
        }
        else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }




}
