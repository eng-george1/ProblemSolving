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

  

}
