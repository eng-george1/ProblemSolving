package LeetCode.Amazon;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import LeetCode.Main2.ListNode;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[] { 1, 2, 2, 3 }, new int[] { 1, 2, 2, 3 })));
        System.out.println(Arrays.deepToString(insert(new int[][] { { 1, 3 }, { 6, 9 } }, new int[] { 2, 5 })));
        System.out.println(Arrays.deepToString(insert(new int[][] {}, new int[] { 2, 5 })));
        System.out.println(Arrays.deepToString(
                insert(new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } }, new int[] { 4, 8 })));
        System.out.println(Arrays.deepToString(insert(new int[][] { { 1, 5 } }, new int[] { 6, 8 })));
        System.out.println(IsPalindrome(230202032));
    }

    public static boolean problem1(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                count++;
            else if(s.charAt(i)==')')
                count--;
            if(count<0)
                return false;
        }
        return count==0;
    }
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length)
            return intersect(nums2, nums1);
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                nums1[index++] = num;
                map.put(num, map.get(num) - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, index);
    }
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curreNode = head;
        while (curreNode != null) {
            ListNode next = curreNode.next;
            curreNode.next = prev;
            prev = curreNode;
            curreNode = next;
        }
        return prev;
    }

    public static ListNode reverseList2(ListNode head) {
        return reverseList(head, null);
    }

    private static ListNode reverseList(ListNode current, ListNode prev) {
        if (current == null)
            return prev;
        ListNode next = current.next;
        current.next = prev;
        prev = current;
        current = next;
        return reverseList(current, prev);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
            // Traverse from left to right.
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        int VISITED = 101;
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Four directions that we will move: right, down, left, up.
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial direction: moving right.
        int currentDirection = 0;
        // The number of times we change the direction.
        int changeDirection = 0;
        // Current place that we are at is (row, col).
        // row is the row index; col is the column index.
        int row = 0;
        int col = 0;
        // Store the first element and mark it as visited.
        List<Integer> result = new ArrayList<>();
        result.add(matrix[0][0]);
        matrix[0][0] = VISITED;
        while (changeDirection < 2) {
            while (row + directions[currentDirection][0] >= 0 &&
                   row + directions[currentDirection][0] < rows &&
                   col + directions[currentDirection][1] >= 0 &&
                   col + directions[currentDirection][1] < columns &&
                   matrix[row + directions[currentDirection][0]]
                   [col + directions[currentDirection][1]] != VISITED) {
                // Reset this to 0 since we did not break and change the direction.
                changeDirection = 0;
                // Calculate the next place that we will move to.
                row = row + directions[currentDirection][0];
                col = col + directions[currentDirection][1];
                result.add(matrix[row][col]);
                matrix[row][col] = VISITED;
            }
            // Change our direction.
            currentDirection = (currentDirection + 1) % 4;
            // Increment change_direction because we changed our direction.
            changeDirection++;
        }
        return result;
    }

    // merge interval
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // init data
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>();

        // add all intervals starting before newInterval
        while (idx < n && newStart > intervals[idx][0])
            output.add(intervals[idx++]);

        // add newInterval
        int[] interval = new int[2];
        // if there is no overlap, just add the interval
        if (output.isEmpty() || output.getLast()[1] < newStart)
            output.add(newInterval);
        // if there is an overlap, merge with the last interval
        else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // add next intervals, merge with newInterval if needed
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            // if there is no overlap, just add an interval
            if (output.getLast()[1] < start)
                output.add(interval);
            // if there is an overlap, merge with the last interval
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }

    // public static int[][] insert(int[][] intervals, int[] newInterval) {
    // if (newInterval.length == 0)
    // return intervals;
    // if (intervals.length == 0)
    // return new int[][] { { newInterval[0], newInterval[1] } };
    // List<Integer[]> list = new ArrayList<>();
    // int isMerged = 0;
    // for (int[] interval : intervals) {
    // isMerged = 0;
    // if (newInterval[1] < interval[0]) {
    // // no merge and before
    // list.add((new Integer[] { newInterval[0], newInterval[1] }));
    // list.add((new Integer[] { interval[0], interval[1] }));
    // } else if (newInterval[0] > interval[1]) {
    // // no merge and after
    // list.add((new Integer[] { interval[0], interval[1] }));
    // isMerged = 1;
    // } else {
    // // merge
    // newInterval[0] = Math.min(newInterval[0], interval[0]);
    // newInterval[1] = Math.max(newInterval[1], interval[1]);
    // isMerged = 1;
    // }
    // }
    // if (isMerged == 1)
    // list.add((new Integer[] { newInterval[0], newInterval[1] }));
    // int[][] ans = new int[list.size()][2];
    // for (int i = 0; i < list.size(); i++) {
    // ans[i][0] = list.get(i)[0];
    // ans[i][1] = list.get(i)[1];
    // }
    // return ans;
    // }
    public int[][] merge(int[][] intervals) {

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

    public static boolean IsPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by
        // revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x =
        // 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to
        // itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber / 10;
    }
    
}
