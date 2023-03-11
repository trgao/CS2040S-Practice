public class Stack<T> {
    private final T[] stack;
    private int counter = 0;

    public Stack(int size) {
        //The only way items can be added into stack
        //is through push, which already checks the type,
        //so it is safe to typecast Object[] to T[]
        @SuppressWarnings("unchecked")
        T[] newstack = (T[]) new Object[size];
        this.stack = newstack;
    }

    public void push(T obj) {
        if (counter == this.stack.length) return;
        this.stack[this.counter] = obj;
        this.counter++;
    }

    public T pop() {
        this.counter--;
        T result = this.stack[this.counter];
        this.stack[this.counter] = null;
        return result;
    }

    public T peek() {
        return this.stack[this.counter - 1];
    }

    public int size() {
        return this.counter;
    }

    public boolean isEmpty() {
        return this.counter == 0;
    }
}