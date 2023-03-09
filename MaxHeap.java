public class MaxHeap {
    private final int[] heap;
    private int counter = 0;

    public MaxHeap(int size) {
        this.heap = new int[size];
    }

    public void insert(int key) {
        this.heap[this.counter] = key;
        bubbleUp(counter);
        this.counter++;
    }

    public void delete(int index) {
        this.heap[index] = 0;
        swap(index, counter - 1);
        bubbleDown(index);
        this.counter--;
    }

    public int size() {
        return this.counter;
    }

    public void decreaseKey(int index, int key) {
        this.heap[index] = key;
        bubbleDown(index);
    }

    public void increaseKey(int index, int key) {
        this.heap[index] = key;
        bubbleUp(index);
    }

    public int extractMax() {
        int max = this.heap[0];
        delete(0);
        return max;
    }

    private void bubbleUp(int index) {
        if (index == 0) return;
        int parent = getParent(index);
        if (heap[parent] < heap[index]) swap(parent, index);
        bubbleUp(parent);
    }

    private void bubbleDown(int index) {
        if (index >= this.heap.length) return;
        int left = getLeft(index);
        int right = getRight(index);
        if (left >= this.heap.length && right >= this.heap.length) return;
        else if (left >= this.heap.length) {
            bubbleDown(right);
            return;
        }
        else if (right >= this.heap.length) {
            bubbleDown(left);
            return;
        }
        if (this.heap[index] < this.heap[left]) {
            swap(left, index);
            bubbleDown(left);
        } else if (this.heap[index] < this.heap[right]) {
            swap(right, index);
            bubbleDown(right);
        }
    }


    private int getLeft(int index) {
        return index * 2 + 1;
    }

    private int getRight(int index) {
        return index * 2 + 2;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }


    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
