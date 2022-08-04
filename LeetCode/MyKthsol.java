package LeetCode;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class MyKthsol {
    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
        System.out.println(kthSmallest(new int[][] { { 1, 2 }, { 1, 3 } }, 4));
        System.out.println(kthSmallest(new int[][] { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 },
                { 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } }, 5));

    }
//https://drive.google.com/file/d/144nrGMJSbKQ3WJcizfwa4nDkfxoBiWgO/view?usp=sharing
    public static int kthSmallest(int[][] matrix, int k) {
        int newsize = Math.min(matrix.length, k);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < newsize; i++) {
            for (int j = 0; j < newsize; j++) {
                minHeap.add(matrix[i][j]);               
            }
        }
        while (k-- > 1)
            minHeap.poll();
        return minHeap.poll();
    }
}
