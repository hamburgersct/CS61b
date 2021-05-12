
public class ArrayDeque<T> {

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

    public T get(int index){
        if (index < 0 || index > size){
            return null;
        }
        return items[index];
    }

    public int size(){
        return size;
    }

    public boolean isFull(){
        return size == items.length;
    }

    public boolean isEmpty(){
        return size == 0;
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

    public void addFirst(T item){
        if (isFull())
            resizeExpand();
        items[nextFirst] = item;
        nextFirst = minusOneHelper(nextFirst);
        size += 1;
    }

    public void addLast(T item){
        if (isFull())
            resizeExpand();
        items[nextLast] = item;
        nextLast = plusOneHelper(nextLast);
        size += 1;
    }

    public void removeFirst(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        nextFirst = plusOneHelper(nextFirst);
        items[nextFirst] = null;
        size -= 1;
    }

    public void removeLast(){
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        nextLast = minusOneHelper(nextLast);
        items[nextLast] = null;
        size -= 1;
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


    public static void main(String[] args) {
        ArrayDeque<Integer> deq = new ArrayDeque(4, 1, 2);
        deq.addFirst(1);
        deq.addLast(3);
        deq.addFirst(5);
        deq.addFirst(6);
        deq.addLast(8);
//        deq.removeLast();
    }
}
