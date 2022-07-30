package Sorting;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.type.ArrayType;

public class Main {
    public static void main(String[] args) {
        // List<Integer> array=List.of(2,4,1,2,20,24,19,9,12,7);
        // array.sort(null);
        // System.out.println(array);
        System.out.println("Start");
        int[] array = {-2, 3, 4, 1, 8, 5, 7, 2, 6, 0, -1, 100, 2, 5, 8, 9, 00, 22, 45 };
        selectionSort(array);
        System.out.println("Selection: " + Arrays.toString(array));
        int[] array1 = { -2, 3, 4, 1, 8, 5, 7, 2, 6, 0, -1, 100, 2, 5, 8, 9, 00, 22, 45 };
        bubbleSort(array1);
        System.out.println("Bubble:    " + Arrays.toString(array1));

        int[] array3 = { -2, 3, 4, 1, 8, 5, 7, 2, 6, 0, -1, 100, 2, 5, 8, 9, 00, 22, 45 };
        insertionSort(array3);
        System.out.println("Insertion: " + Arrays.toString(array3));

        int[] array4 = { -2, 3, 4, 1, 8, 5, 7, 2, 6, 0, -1, 100, 2, 5, 8, 9, 00, 22, 45 };
        array4 = MergeSort.mergeSort(array4);
        System.out.println("Merge:     " + Arrays.toString(array4));

        int[] array5 = { -2, 3, 4, 1, 8, 5, 7, 2, 6, 0, -1, 100, 2, 5, 8, 9, 00, 22, 45 };
        array4 = QuickSort.quickSort(array5);
        System.out.println("Quick:     " + Arrays.toString(array4));
        selectionSort(new int[] { 1 });
        selectionSort(new int[] {});
        // System.out.println(Arrays.toString( ));
    }

    // O(n^2)
    public static void bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            boolean isSwapped = false;
            for (int ii = 0; ii < array.length - 1 - i; ii++) {
                if (array[ii] > array[ii + 1]) {
                    // swap
                    int tempForSwap = array[ii];
                    array[ii] = array[ii + 1];
                    array[ii + 1] = tempForSwap;
                    isSwapped = true;
                }
            }
            // if all items passed without any swap so its already sorted
            // best case O(n)
            if (!isSwapped)
                break;
        }

    }

    public static void selectionSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            int smalest = array[i];
            int smalestIndex = i;
            for (int ii = i + 1; ii < array.length; ii++) {

                if (smalest > array[ii]) {
                    // swap
                    smalest = array[ii];
                    smalestIndex = ii;
                }
            }
            array[smalestIndex] = array[i];
            array[i] = smalest;
        }

    }

    // O(n^2)
    public static void insertionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (array[j - 1] > array[j]) {
                    int tepSwap = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tepSwap;
                } else
                    // if one item passed without  swap so its already sorted and take the next
                    // best case O(n)
                    break;
            }
        }

    }

}
