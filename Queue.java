public class Queue<T> {
    private final T[] queue;
    private int start = 0;
    private int counter = 0;

    public Queue(int size) {
        //The only way items can be added into stack
        //is through push, which already checks the type,
        //so it is safe to typecast Object[] to T[]
        @SuppressWarnings("unchecked")
        T[] newqueue = (T[]) new Object[size];
        this.queue = newqueue;
    }

    public void enq(T obj) {
        if (this.counter == this.queue.length) return;
        this.queue[getIndex()] = obj;
        counter++;
    }

    public T deq() {
        T result = this.queue[start];
        this.queue[start] = null;
        this.counter--;
        this.start++;
        this.start = start % this.queue.length;
        return result;
    }

    public T peek() {
        return this.queue[this.start];
    }

    public int size() {
        return this.counter;
    }

    public boolean isEmpty() {
        return this.counter == 0;
    }

    private int getIndex() {
        return (this.start + this.counter) % this.queue.length;
    }
}