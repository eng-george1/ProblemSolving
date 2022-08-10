package Sorting;

import java.util.Arrays;

public class QuickSort {
    public static int[] quickSort(int[] array) {
        if (array.length < 2)
            return array;
        int pivote = array[0];
        int[] leftArr = new int[array.length - 1];
        int[] rightArr = new int[array.length - 1];
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (pivote >= array[i]) {
                leftArr[leftIndex] = array[i];
                leftIndex++;
            } else {
                rightArr[rightIndex] = array[i];
                rightIndex++;
            }
        }
        int[] result = new int[array.length];
        if (leftIndex != 0) {
           leftArr= Arrays.copyOf(leftArr, leftIndex);
            System.arraycopy(quickSort(leftArr), 0, result, 0, leftIndex);
        }
        if (rightIndex != 0) {
            rightArr= Arrays.copyOf(rightArr, rightIndex);
            System.arraycopy(quickSort(rightArr), 0, result, leftIndex + 1, rightIndex);
        }
        result[leftIndex] = pivote;
        return result;
    }    
    
}
