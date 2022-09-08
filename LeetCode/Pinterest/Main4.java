package LeetCode.Pinterest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

import javax.management.monitor.CounterMonitor;

import Array.arrayList;

public class Main4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf2(new int[] { 1, 2, 3, 4 })));
        System.out.println(findDisappearedNumbers(new int[] { 1, 3, 5, 7, 3, 1, 7 }));
        System.out.println(findDuplicates(new int[] { 1, 3, 5, 7, 3, 1, 7 }));
        System.out.println(firstMissingPositive2(new int[] { -1, 3, 5, 7, 4, 1, 8 }));
        System.out.println(firstMissingPositive(new int[] { 1 }));
        System.out.println(numSubarrayProductLessThanK2(new int[] { 10, 5, 2, 6 }, 100));
        System.out
                .println(numSubarrayProductLessThanK2(new int[] { 10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3 }, 19));
        System.out.println(find132pattern(new int[] { 3, 1, 4, 2 }));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] { 1, 3, 1, 2, 0, 5 }, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[] { 1, 3, 1, 2, 0, 5 }, 3)));
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome(".,"));
        System.out.println(removeDuplicates(new int[] { 1, 1 }));
        System.out.println(trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));

    }

    public static int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[right.length - 1] = 1;
        int n = nums.length - 1;
        int[] result = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
            right[n - i] = right[n - i + 1] * nums[n - i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public static int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int prod = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            prod *= nums[i + 1];
            result[i] = result[i] * prod;
        }
        return result;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] > 0)
                nums[Math.abs(nums[i]) - 1] *= -1;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                result.add(i + 1);
        }
        return result;
    }

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                result.add(Math.abs(nums[i]));
            } else
                nums[Math.abs(nums[i]) - 1] *= -1;
        }
        return result;
    }

    public static int firstMissingPositive(int[] nums) {
        int[] nums1 = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length) {
                nums1[nums[i] - 1] += 1;
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == 0)
                return i + 1;
        }
        return 1;
    }

    public static int firstMissingPositive2(int[] nums) {
        int[] nums1 = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length) {
                if (nums[i] == i + 1 || nums[nums[i] - 1] == nums[i])
                    continue;
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
                i--;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }
        return nums.length + 1;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1)
            return 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int prod = nums[i];
            if (prod < k)
                count++;
            for (int j = i + 1; j < len && prod < k; j++) {
                prod *= nums[j];
                if (prod < k)
                    count++;
            }

        }
        return count;
    }

    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1)
            return 0;
        int left = 0, right = 0;
        int count = 0;
        int currentProd = 1;
        while (right < nums.length) {
            currentProd *= nums[right];
            System.out.println(currentProd);
            while (currentProd >= k) {
                currentProd /= nums[left++];
                // left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }

    public static int numSubarrayProductLessThanK3(int[] nums, int k) {
        if (k <= 1)
            return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            System.out.println(prod);
            while (prod >= k)
                prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }

    public static boolean find132pattern(int[] nums) {
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min[i]) {
                while (!stack.isEmpty() && stack.peek() <= min[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[i])
                    return true;
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow3(int[] nums, int k) {
        ArrayDeque<Integer[]> deq = new ArrayDeque<Integer[]>();
        int[] result = new int[nums.length - k + 1];
        int max = nums[0];
        for (int j = 0; j < k; j++) {
            while (!deq.isEmpty() && deq.peek()[0] <= nums[j]) {
                deq.poll();
            }
            deq.push(new Integer[] { nums[j], j });
            max = deq.stream().mapToInt(e -> e[0]).max().orElse(0);
            result[0] = max;
        }
        result[0] = max;
        for (int i = k; i < nums.length; i++) {
            if (i - deq.getLast()[1] == k)
                deq.removeLast();
            while (!deq.isEmpty() && deq.peek()[0] <= nums[i]) {
                deq.poll();
            }
            deq.push(new Integer[] { nums[i], i });
            max = deq.stream().mapToInt(e -> e[0]).max().orElse(0);
            result[i - k + 1] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow4(int[] nums, int k) {
        ArrayDeque<Integer[]> deq = new ArrayDeque<Integer[]>();
        int[] result = new int[nums.length - k + 1];
        int max = nums[0];
        for (int j = 0; j < k; j++) {
            while (!deq.isEmpty() && deq.peek()[0] <= nums[j]) {
                deq.poll();
            }
            deq.push(new Integer[] { nums[j], j });
            max = deq.getLast()[0];// deq.stream().mapToInt(e->e[0]).max().orElse(0);
            result[0] = max;
        }
        result[0] = max;
        for (int i = k; i < nums.length; i++) {
            if (i - deq.getLast()[1] == k)
                deq.removeLast();
            while (!deq.isEmpty() && deq.peek()[0] <= nums[i]) {
                deq.poll();
            }
            deq.push(new Integer[] { nums[i], i });
            max = deq.getLast()[0];// max= deq.stream().mapToInt(e->e[0]).max().orElse(0);
            result[i - k + 1] = max;
        }
        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
        int[] result = new int[nums.length - k + 1];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!deq.isEmpty() && i - deq.getLast() == k)
                deq.removeLast();
            while (!deq.isEmpty() && nums[deq.peek()] <= nums[i]) {
                deq.poll();
            }
            deq.push(i);
            if (i - k + 1 >= 0)
                result[i - k + 1] = nums[deq.getLast()];
        }
        return result;
    }

    public static boolean isSubsequence(String s, String t) {
        int index = s.length() - 1;
        for (int i = t.length() - 1; i >= 0; i--) {
            if (index < 0)
                return true;
            if (t.charAt(i) == s.charAt(index))
                index--;
        }
        if (index <= 0)
            return true;
        return false;
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty())
            return true;
        s = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        while (right > left) {
            while (left < s.length() && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (right >= 0 && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (left > right)
                return true;
            if (s.charAt(right) != s.charAt(left))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            ;
            if (nums[i] != nums[index])
                nums[++index] = nums[i];
        }
        return index + 1;
    }

    private static int max(int[] height, int start, int end) {
        int max = 0;
        for (int i = start; i < end; i++) {
            max = Math.max(max, height[i]);
        }
        return max;
    }

    public static int trap2(int[] height) {
        int count = 0;
        for (int i = 0; i < height.length; i++) {
            int right = max(height, i + 1, height.length);
            int left = max(height, 0, i);
            int diff = Math.min(right, left) - height[i];
            count += (diff < 0) ? 0 : diff;
        }
        return count;
    }

    public static int trap3(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[right.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        int count = 0;
        for (int i = 1; i < height.length - 1; i++) {
            count += Math.min(left[i], right[i]) - height[i];
        }
        return count;
    }

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1, maxL = height[left], maxR = height[right], count = 0;
        while (right > left) {
            if (height[left] < height[right]) {
                // move left
                if (height[left] >= maxL)
                    maxL = height[left];
                else
                    count += maxL - height[left];
                left++;
            } else {
                // move right
                if (height[right] >= maxR)
                    maxR = height[right];
                else
                    count += maxR - height[right];
                right--;
            }

        }
        return count;
    }
}
