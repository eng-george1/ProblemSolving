package LeetCode.Pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int[] tempwithoutFirst = new int[nums.length - k + 1];
        int max = nums[0];
        int maxwithoutFirst = Integer.MIN_VALUE;
        for (int j = 1; j < k; j++) {
            max = Math.max(max, nums[j]);
            maxwithoutFirst = Math.max(maxwithoutFirst, nums[j]);
        }
        result[0] = max;
        tempwithoutFirst[0] = maxwithoutFirst;
        for (int i = 1; i < nums.length - k + 1; i++) {
            result[i] = Math.max(nums[i], tempwithoutFirst[i - 1]);
            tempwithoutFirst[i] = Math.max(nums[i], tempwithoutFirst[i - 1]);
        }
        return result;
    }
}
