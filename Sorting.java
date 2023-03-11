import java.util.Arrays;
import java.util.Random;

public class Sorting {
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                //bubbling largest element to the end
                if (array[j] > array[j + 1]) swap(array, j, j + 1);
            }
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            //selecting smallest element for each index
            findMin(array, i);
        }
    }

    private static void findMin(int[] array, int start) {
        int min = start;
        for (int i = start; i < array.length; i++) {
            if (array[i] < array[min]) min = i;
        }
        swap(array, start, min);
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                //inserting i+1th element into first i sorted elements
                if (array[j - 1] > array[j]) swap(array, j, j - 1);
            }
        }
    }

    public static void mergeSort(int[] array) {
        mergeSortHelper(array, 0, array.length - 1);
    }

    private static void mergeSortHelper(int[] array, int start, int end) {
        if (start == end) return;
        int middle = start + (end - start) / 2;

        //recurse
        mergeSortHelper(array, start, middle);
        mergeSortHelper(array, middle + 1, end);

        int[] left = Arrays.copyOfRange(array, start, middle + 1);
        int[] right = Arrays.copyOfRange(array, middle + 1, end + 1);
        int leftpointer = 0;
        int rightpointer = 0;
        int index = start;

        //merge
        while (leftpointer < left.length && rightpointer < right.length) {
            if (right[rightpointer] < left[leftpointer]) {
                array[index] = right[rightpointer];
                rightpointer++;
            } else {
                array[index] = left[leftpointer];
                leftpointer++;
            }
            index++;
        }
        while (leftpointer < left.length) {
            array[index] = left[leftpointer];
            leftpointer++;
            index++;
        }
        while (rightpointer < right.length) {
            array[index] = right[rightpointer];
            rightpointer++;
            index++;
        }
    }

    public static void quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    private static void quickSortHelper(int[] array, int start, int end) {
        if (start >= end) return;
        int middle = start + (end - start) / 2;
        int pivot = array[middle];
        swap(array, start, middle);
        int left = start + 1;
        int right = end;

        //partitioning
        while (left <= right) {
            if (array[left] < pivot) left++;
            else if (array[right] > pivot) right--;
            else if (array[left] > pivot && array[right] < pivot) swap(array, left, right);
        }
        swap(array, start, left - 1);

        //recurse
        quickSortHelper(array, start, left - 2);
        quickSortHelper(array, left, end);
    }

    public static void heapSort(int[] array) {
        heapify(array, 0);
        //extractMax
        for (int i = 0; i < array.length; i++) {
            swap(array, 0, array.length - 1 - i);
            bubbleDown(array, 0, array.length - 2 - i);
        }
    }

    private static void heapify(int[] array, int index) {
        if (index >= array.length / 2) return;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        heapify(array, left);
        heapify(array, right);
        bubbleDown(array, index, array.length - 1);
    }

    private static void bubbleDown(int[] array, int index, int end) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (left > end && right > end) return;
        else if (left > end && array[right] > array[index]) {
            swap(array, right, index);
            bubbleDown(array, right, end);
        } else if (right > end && array[left] > array[index]) {
            swap(array, left, index);
            bubbleDown(array, left, end);
        } else if (left <= end && right <= end) {
            if (array[left] > array[right] && array[left] > array[index]) {
                swap(array,left, index);
                bubbleDown(array, left, end);
            }
            else if (array[right] > array[left] && array[right] > array[index]) {
                swap(array,right, index);
                bubbleDown(array, right, end);
            }
        }
    }

    //Fisher-Yates shuffle
    private static int[] testArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        Random rng = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rng.nextInt(i + 1);
            swap(array, index, i);
        }
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String[] args) {
        int[] test1 = testArray(16);
        Sorting.bubbleSort(test1);
        System.out.println(Arrays.toString(test1));

        int[] test2 = testArray(16);
        Sorting.selectionSort(test2);
        System.out.println(Arrays.toString(test2));

        int[] test3 = testArray(16);
        Sorting.insertionSort(test3);
        System.out.println(Arrays.toString(test3));

        int[] test4 = testArray(16);
        Sorting.mergeSort(test4);
        System.out.println(Arrays.toString(test4));

        int[] test5 = testArray(16);
        Sorting.quickSort(test5);
        System.out.println(Arrays.toString(test5));

        int[] test6 = testArray(16);
        Sorting.heapSort(test6);
        System.out.println(Arrays.toString(test6));
    }
}
