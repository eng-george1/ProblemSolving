package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import Array.arrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(kthSmallest1(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
        System.out.println(kthSmallest1(new int[][] { { -5 } }, 1));
        System.out.println(kthSmallest1(new int[][] { { 1, 2, 3 }, { 4, 7, 8 }, { 9, 10, 11 } }, 7));
        System.out.println(kthSmallest2(new int[][] { { 1, 2 }, { 1, 3 } }, 2));
        System.out.println(Arrays.toString(twoSum2(new int[] { 3, 2, 4 }, 6)));
        System.out.println(romanToInt("MCMXCIV"));

    
        print2D(merge(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } }));
        print2D(merge(new int[][] { { 2, 3 }, { 4, 5 }, { 6, 7 }, { 8, 9 }, { 1, 10 } }));
        System.out.println("Merge after Sort");
        print2D(merge1(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } }));
        print2D(merge1(new int[][] { { 2, 3 }, { 4, 5 }, { 6, 7 }, { 8, 9 }, { 1, 10 } }));

        System.out.println(findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }));
        System.out.println(findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
        System.out.println(findMedianSortedArrays(new int[] { 0, 0 }, new int[] { 0, 0 }));
        System.out.println(isValid("()"));
        System.out.println(isValid("]"));

        int[] arr = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        System.out.print(trap2(arr));
    }

    public static void print2D(int mat[][]) {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
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
                    value += values.get(s.charAt(i + 1)) - values.get(s.charAt(i));
                    i++;
                } else
                    value += values.get(s.charAt(i));
            } else
                value += values.get(s.charAt(i));

        }
        return value;
    }

   

    public static int[][] merge(int[][] intervals) {
        int[][] result = new int[intervals.length][intervals[0].length];
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            int tempInser = 1;
            int l1 = intervals[i][0];
            int h1 = intervals[i][1];
            if (intervals[i][0] == -1 && intervals[i][1] == -1)
                continue;
            for (int j = i + 1; j < intervals.length; j++) {
                int l2 = intervals[j][0];
                int h2 = intervals[j][1];
                if ((l1 >= l2 && l1 <= h2) || (h1 >= l2 && h1 <= h2) || (l2 >= l1 && l2 <= h1)
                        || (h2 >= l1 && h2 <= h1)) {
                    // merge
                    l1 = Math.min(l1, l2);
                    h1 = Math.max(h1, h2);
                    intervals[i][0] = -1;
                    intervals[i][1] = -1;
                    intervals[j][0] = l1;
                    intervals[j][1] = h1;
                    tempInser = 0;
                    break;

                }

            }
            if (tempInser == 1) {
                result[index][0] = l1;
                result[index][1] = h1;
                index++;
            }
        }
        return Arrays.copyOf(result, index);
    }

    public static int[][] merge1(int[][] intervals) {
        if (intervals.length < 2)
            return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[][] result = new int[intervals.length][intervals[0].length];
        int index = 0;
        int l1 = intervals[0][0];
        int h1 = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int l2 = intervals[i][0];
            int h2 = intervals[i][1];
            if ((l1 >= l2 && l1 <= h2) || (h1 >= l2 && h1 <= h2) || (l2 >= l1 && l2 <= h1)
                    || (h2 >= l1 && h2 <= h1)) {
                // merge
                l1 = Math.min(l1, l2);
                h1 = Math.max(h1, h2);
            } else {
                result[index][0] = l1;
                result[index][1] = h1;
                index++;
                l1 = l2;
                h1 = h2;
            }
        }
        result[index][0] = l1;
        result[index][1] = h1;
        return Arrays.copyOf(result, index + 1);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i1 = 0, i2 = 0;
        int mid = (nums1.length + nums2.length) / 2;
        int first = 0;
        while (mid > 0) {
            if (i1 >= nums1.length || i2 >= nums2.length) {
                if (i1 >= nums1.length) {
                    first = nums2[i2];
                    i2++;
                }
                if (i2 >= nums2.length) {
                    first = nums1[i1];
                    i1++;
                }
            } else {
                if (nums1[i1] < nums2[i2]) {
                    first = nums1[i1];
                    i1++;
                } else {
                    first = nums2[i2];
                    i2++;
                }
            }
            mid--;
        }

        int second = 0;
        if (i1 > nums1.length - 1) {
            second = nums2[i2];
        } else if (i2 > nums2.length - 1) {
            second = nums1[i1];
        } else if (nums1[i1] < nums2[i2])
            second = nums1[i1];
        else
            second = nums2[i2];
        if ((nums1.length + nums2.length) % 2 > 0)
            return second;
        return Double.valueOf(first + second) / 2;
    }

    static Map<Character, Character> map = new HashMap<>();
    static {
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
    }

    public static boolean isValid(String s) {
        Stack<Character> data = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                data.add(s.charAt(i));
            } else if (data.isEmpty() || data.pop() != map.get(s.charAt(i)))
                return false;
        }
        return data.isEmpty();
    }

    public static int trap(int[] height) {
        if (height.length < 2)
            return 0;
        int count = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int maxLeft = height[i];
            int maxRight = height[i];
            for (int j = 0; j < i; j++) {
                // get max
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i + 1; j < height.length; j++) {
                // get max
                maxRight = Math.max(maxRight, height[j]);
            }
            count += Math.min(maxLeft, maxRight) - height[i];
        }
        return count;
    }

    public static int trap2(int[] height) {
        if (height.length < 2)
            return 0;
        int count = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[right.length - 1] = height[right.length - 1];
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        for (int i = 0; i < height.length ; i++) {

            count += Math.min(right[i], left[i]) - height[i];
        }
        return count;
    }

    // Java code to implement of the approach

    // Function to return the maximum
    // water that can be stored
    public static int maxWater(int[] arr, int n) {

        // To store the maximum water
        // that can be stored
        int res = 0;

        // For every element of the array
        // except first and last element
        for (int i = 1; i < n - 1; i++) {

            // Find maximum element on its left
            int left = arr[i];
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }

            // Find maximum element on its right
            int right = arr[i];
            for (int j = i + 1; j < n; j++) {
                right = Math.max(right, arr[j]);
            }

            // Update maximum water value
            res += Math.min(left, right) - arr[i];
        }
        return res;
    }

    // This code is contributed by Debidutta Rath

}
