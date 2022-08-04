package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println(kthSmallest1(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
        System.out.println(kthSmallest1(new int[][] { { -5 } }, 1));
        System.out.println(kthSmallest1(new int[][] { { 1, 2, 3 }, { 4, 7, 8 }, { 9, 10, 11 } }, 7));
        System.out.println(kthSmallest2(new int[][] { { 1, 2 }, { 1, 3 } }, 2));
        System.out.println(Arrays.toString(twoSum2(new int[] { 3, 2, 4 }, 6)));
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        // find dublicated
        return matrix[(k - 1) / matrix.length][((k - 1) % matrix.length)];
    }

    public static int kthSmallest1(int[][] matrix, int k) {
        // merge
        int[] array = new int[matrix.length * matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                array[i * matrix.length + j] = matrix[i][j];
            }
        }
        Sorting.Main.insertionSort(array);
        System.out.println(Arrays.toString(array));
        return array[k - 1];
    }

    public static int kthSmallest3(int[][] matrix, int k) {
        // get the dublicated count
        int dublicated = 0;
        int[] array = new int[matrix.length * matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                array[i * matrix.length + j] = matrix[i][j];
            }
        }
        Sorting.Main.insertionSort(array);
        System.out.println(Arrays.toString(array));
        return array[k - 1];
    }

    public static int kthSmallest2(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }

            }
        }
        return maxHeap.poll();
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[] { i, j };
            }

        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return new int[] { map.get(nums[i]), i };
            map.put(target - nums[i], i);
        }
        return null;
    }

    static Map<Character, Integer> values = new HashMap<>();
    static {
        values.put('M', 1000);
        values.put('D', 500);
        values.put('C', 100);
        values.put('L', 50);
        values.put('X', 10);
        values.put('V', 5);
        values.put('I', 1);
    }

    public static int romanToInt(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length()) {
                if (values.get(s.charAt(i)) < values.get(s.charAt(i + 1))) {
                    value += values.get(s.charAt(i + 1))-values.get(s.charAt(i)) ;
                    i++;
                } else
                    value += values.get(s.charAt(i));
            } else
                value += values.get(s.charAt(i));

        }
        return value;
    }
}
