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

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
        return array;
    }
    public static void main(String[] args) {
        int[] test = testArray(15);
        Sorting.quickSort(test);
        System.out.println(Arrays.toString(test));
    }
}
