package LeetCode.Pinterest;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Main7 {
  public static void main(String[] args) {
    System.out.println(minMeetingRooms(new int[][] { { 1, 5 }, { 8, 9 }, { 8, 9 } }));
    System.out.println(addTwoNumbers("1.001", "23.3"));
    System.out.println(addTwoNumbers("100000000000000000.00000000000000001", "100000000000000001.100000000000000000"));
    System.out.println(addTwoNumbers("11", "123"));
  }

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(e -> e[0]));
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i - 1][1] >= intervals[i][0]) {
        return false;
      }
    }
    return true;

  }

  public static int minMeetingRooms(int[][] intervals) {
    int ans = 1;
    int[] starts = new int[intervals.length];
    int[] ends = new int[intervals.length];
    for (int i = 0; i < intervals.length; i++) {
      starts[i] = intervals[i][0];
      ends[i] = intervals[i][1];
    }
    Arrays.sort(starts);
    Arrays.sort(ends);
    int startP = 0, endP = 0, count = 0;
    while (startP < starts.length) {
      if (starts[startP] < ends[endP]) {
        count++;
        startP++;
      } else {
        endP++;
        count--;
      }
      ans = Math.max(ans, count);
    }
    return ans;
  }

  public int minMeetingRooms1(int[][] intervals) {

    // Check for the base case. If there are no intervals, return 0
    if (intervals.length == 0) {
      return 0;
    }

    // Min heap
    PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(
        intervals.length,
        new Comparator<Integer>() {
          public int compare(Integer a, Integer b) {
            return a - b;
          }
        });

    // Sort the intervals by start time
    Arrays.sort(
        intervals,
        new Comparator<int[]>() {
          public int compare(final int[] a, final int[] b) {
            return a[0] - b[0];
          }
        });

    // Add the first meeting
    allocator.add(intervals[0][1]);

    // Iterate over remaining intervals
    for (int i = 1; i < intervals.length; i++) {

      // If the room due to free up the earliest is free, assign that room to this
      // meeting.
      if (intervals[i][0] >= allocator.peek()) {
        allocator.poll();
      }

      // If a new room is to be assigned, then also we add to the heap,
      // If an old room is allocated, then also we have to add to the heap with
      // updated end time.
      allocator.add(intervals[i][1]);
    }

    // The size of the heap tells us the minimum rooms required for all the
    // meetings.
    return allocator.size();
  }

  public void solveSudoku(char[][] board) {
    Map<String, List<Integer>> missed = new HashMap<>();
    int missedIndex = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == '.') {
          List<Integer> list = new ArrayList<>();
          Set<Integer> set = new HashSet<>();
          int starti = i / 3 * 3;
          int startj = j / 3 * 2;
          for (int ii = starti; i < starti + 3; ii++) {
            for (int jj = startj; i < startj + 3; jj++) {
              set.add((int) board[ii][jj]);
            }
          }

          for (int ii = 1; ii < 10; ii++) {
            set.add((int) board[ii][j]);
          }

          for (int ii = 1; ii < 10; ii++) {
            set.add((int) board[i][ii]);
          }
          for (int ii = 1; ii < 10; ii++) {
            if (!set.remove(ii)) {
              list.add(ii);
            }
          }
          if (list.size() == 1) {
            board[i][j] = list.get(0).toString(0).toCharArray()[0];
          }
          missed.put(Integer.toString(i) + Integer.toString(j), list);
        }
      }
    }

  }
  private static final String ZERO = "0";

  // Time: O(Max (N, M)); N = str1 length, M = str2 length
  // Space: O(N + M)
  public static String addTwoNumbers(String str1, String str2) {

      String[] s1 = str1.split("\\.");
      String[] s2 = str2.split("\\.");

      StringBuilder sb = new StringBuilder();

      // step 1. calculate decimal points after .
      // decimal points
      // prepare decimal point.
      String sd1 = s1.length > 1 ? s1[1] : ZERO;
      String sd2 = s2.length > 1 ? s2[1] : ZERO;
      while (sd1.length() != sd2.length()) {
          if (sd1.length() < sd2.length()) {
              sd1 += ZERO;
          } else {
              sd2 += ZERO;
          }
      }
      int carry = addStringHelper(sd1, sd2, sb, 0);

      sb.append(".");

      // Step 2. Calculate Number before decimal point.
      // Number
      addStringHelper(s1[0], s2[0], sb, carry);
      return sb.reverse().toString();
  }

  // This is modified version of add strings.
  // LC: https://leetcode.com/problems/add-strings/
  private static int addStringHelper(String str1, String str2, StringBuilder sb, int carry) {
      int i = str1.length() - 1;
      int j = str2.length() - 1;
      while (i >= 0 || j >= 0) {
          int sum = carry;

          if (j >= 0) {
              sum += str2.charAt(j--) - '0';
          }
          if (i >= 0) {
              sum += str1.charAt(i--) - '0';
          }
          carry = sum / 10;
          sb.append(sum % 10);
      }
      return carry;
  }


  public static String addTwoNumbers2(String s1, String s2) {
    if (s1 == null & s2 == null)
      return "0";
    if (s1 == null || s1.isEmpty())
      return s2;
    if (s2 == null || s2.isEmpty())
      return s1;
    // if (!(s1.indexOf('.') < 0 && s2.indexOf('.') < 0)) {
    //   if (s1.indexOf('.') < 0)
    //     s1 += ".";
    //   if (s2.indexOf('.') < 0)
    //     s2 += ".";
    // }
    while (Math.max(s1.indexOf('.'), s2.indexOf('.')) > s1.indexOf('.')) {
      s1 = "0" + s1;
    }
    while (Math.max(s1.indexOf('.'), s2.indexOf('.')) > s2.indexOf('.')) {
      s2 = "0" + s2;
    }
    while (Math.max(s1.length() - s1.indexOf('.'), s2.length() - s2.indexOf('.')) > s1.length() - s1.indexOf('.')) {
      s1 += "0";
    }
    while (Math.max(s1.length() - s1.indexOf('.'), s2.length() - s2.indexOf('.')) > s2.length() - s2.indexOf('.')) {
      s2 += "0";
    }
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    for (int i = s1.length() - 1; i >= 0; i--) {
      if (s1.charAt(i) == '.') {
        sb.insert(0, '.');
        continue;
      }
      int num1 = 0, num2 = 0, sum = carry;
      carry = 0;
      num1 = s1.charAt(i) - '0';
      num2 = s2.charAt(i) - '0';
      sum += num1 + num2;
      if (sum >= 10)
        carry = 1;
      sb.insert(0, sum % 10);
    }
    return sb.toString();

  }

}
