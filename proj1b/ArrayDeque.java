public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    public ArrayDeque(int arr_size, int first, int next){
        size = 0;
        nextFirst = first;
        nextLast = next;
        items = (T[]) new Object[arr_size];
    }

    @Override
    public T get(int index){
        if (index < 0 || index > size){
            return null;
        }
        return items[index];
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()){
            System.out.println("This array is empty.");
        }
        else if (nextFirst < nextLast){
            for (int i = nextFirst + 1; i < nextLast; i++){
                System.out.print(items[i] + " ");
            }
        }
        else {
            for (int i = nextFirst; i < items.length; i++){
                System.out.print(items[i] + " ");
            }
            for (int i = 0; i < nextLast; i++){
                System.out.print(items[i] + " ");
            }
        }
    }

    public boolean isFull(){
        return size == items.length;
    }

    public int minusOneHelper(int index){
        if (index - 1 < 0){
            return items.length - 1;
        }
        return index - 1;
    }

    public int plusOneHelper(int index){
        if (index + 1 > items.length - 1){
            return 0;
        }
        return index + 1;
    }

    @Override
    public void addFirst(T item){
        if (isFull())
            resizeExpand();
        items[nextFirst] = item;
        nextFirst = minusOneHelper(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T item){
        if (isFull())
            resizeExpand();
        items[nextLast] = item;
        nextLast = plusOneHelper(nextLast);
        size += 1;
    }

    /** usage ratio: size / items.length
     *  if usage ratio < 0.25, the array should be resized
     */

    @Override
    public T removeFirst(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        nextFirst = plusOneHelper(nextFirst);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if (size / items.length < 0.25 && size > 0){
            resizeReduce();
        }
        return removedItem;
    }


    @Override
    public T removeLast(){
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        nextLast = minusOneHelper(nextLast);
        T removeItem = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (size / items.length < 0.25 && size > 0){
            resizeReduce();
        }
        return removeItem;
    }

    public void resizeExpand(){
        T[] newArray = (T[]) new Object[size * 2];
        int head = plusOneHelper(nextFirst);
        int rear = minusOneHelper(nextLast);
        int partLen = items.length - head;
        System.arraycopy(this.items, head, newArray, 0, partLen);
        System.arraycopy(this.items, 0, newArray, partLen, this.size - partLen);
        items = newArray;
        nextFirst = minusOneHelper(0);
        nextLast = size;
    }

    public void resizeReduce(){
        T[] newArray = (T[]) new Object[items.length / 2];
        int head = plusOneHelper(nextFirst);
        int rear = minusOneHelper(nextLast);
        if (rear < head){
            int partLen = items.length - head;
            System.arraycopy(items, head, newArray, 0, partLen);
            System.arraycopy(items, 0, newArray, partLen, this.size - partLen);
        }
        else {
            System.arraycopy(items, 0, newArray, 0, size);
        }
        items = newArray;
        nextFirst = minusOneHelper(0);
        nextLast = size;
    }

}
