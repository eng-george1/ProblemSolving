package Sorting;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] array) {
        if (array.length <= 1||array.length==0)
            return array;
        return merge(mergeSort(Arrays.copyOfRange(array, 0, array.length / 2 )),
                mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length )));
    }

    public static int[] merge(int[] leftArr, int[] rightArr) {
        int[] result = new int[leftArr.length + rightArr.length];
        int leftPointer = 0, rightPointer = 0;
        for (int i = 0; i < result.length; i++) {
            if (leftPointer < leftArr.length && rightPointer < rightArr.length) {
                if (leftArr[leftPointer] > rightArr[rightPointer]) {
                    result[i] = rightArr[rightPointer];
                    rightPointer++;
                } else {
                    result[i] = leftArr[leftPointer];
                    leftPointer++;
                }
            }
          else  if (leftPointer == leftArr.length) {
                while (rightPointer < rightArr.length) {
                    result[i] = rightArr[rightPointer];
                    rightPointer++;
                    i++;
                }

            } else if (rightPointer == rightArr.length) {
                while (leftPointer < leftArr.length) {
                    result[i] = leftArr[leftPointer];
                    leftPointer++;
                    i++;
                }
            }
        }
        return result;
    }
}
