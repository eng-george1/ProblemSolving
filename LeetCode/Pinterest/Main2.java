package LeetCode.Pinterest;

import java.lang.Thread.State;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses2(")()())"));
        System.out.println(reverseWords("  the   sky is   blue   "));
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("a", "ab"));
        System.out.println(search(new int[] { 0, 2, 3, 4, 5, 6, 7, 8, 9, 9 }, 6));
        System.out.println(findMin(new int[] { 4, 5, 6, 7, -1, 0, 1, 2 }));
        System.out.println(findMin(new int[] { 11, 13, 15, 17 }));
        System.out.println(findMin(new int[] { 5, 1, 2, 3, 4 }));
        System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(maxProfit3(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(maxProfit3(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(maxProfit3(new int[] { 1, 2, 3, 4, 5 }));

        System.out.println(removeDuplicates(new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 }));
        int[] i1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int[] i2 = new int[] { 2, 3, 5 };
        merge(i1, 3, i2, 3);
        System.out.println(Arrays.toString(i1));
    }

    public static int longestValidParentheses(String s) {
        int maxL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - 1 - i < maxL)
                return maxL;
            for (int j = s.length() - 1; j > i; j--) {
                if (isValid(s, i, j))
                    maxL = Math.max(maxL, j - i + 1);
            }
        }
        return maxL;
    }

    private static boolean isValid(String s, int start, int end) {
        if ((end - start + 1) % 2 != 0)
            return false;
        Stack<Character> charStack = new Stack<>();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == ')') {
                if (charStack.isEmpty()) {
                    return false;
                }
                charStack.pop();
            } else {
                charStack.push('(');
            }
        }
        if (charStack.isEmpty())
            return true;
        return false;
    }

    public static int longestValidParentheses2(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty())
                result.append(words[i] + " ");
        }
        return result.toString().trim();
    }

    public static boolean isAnagram2(String s, String t) {
        char[] charS = s.toCharArray();
        Arrays.sort(charS);
        char[] charT = t.toCharArray();
        Arrays.sort(charT);
        return Arrays.equals(charS, charT);
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] charS = new char[26];
        char[] charT = new char[26];
        for (int i = 0; i < s.length(); i++) {
            charS[s.charAt(i) - 'a']++;
            charT[t.charAt(i) - 'a']++;
        }
        return Arrays.equals(charS, charT);
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[(left + right) / 2] < target) {
                left = (left + right) / 2 + 1;
            } else if (nums[(right + left) / 2] > target) {
                right = (right + left) / 2 - 1;
            } else {
                return (right + left) / 2;
            }
        }
        return -1;
    }

    // rotated
    public static int search2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            } else {
                if (target <= nums[end] && target > nums[mid])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    // 4,5,6,7,0,1,2
    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        while (right >= left) {
            int mid = left + (right - left) / 2;

            // if the mid element is greater than its next element then mid+1 element is the
            // smallest
            // This point would be the point of change. From higher to lower value.
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // if the mid element is lesser than its previous element then mid element is
            // the smallest
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            // if the mid elements value is greater than the 0th element this means
            // the least value is still somewhere to the right as we are still dealing with
            // elements
            // greater than nums[0]
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // if nums[0] is greater than the mid value then this means the smallest value
                // is somewhere to
                // the left
                right = mid - 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    // multiple days
    public static int maxProfit2(int[] prices) {
        if (prices.length < 2)
            return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (minPrice < prices[i]) {
                // sell
                maxProfit += prices[i] - minPrice;
            }
            minPrice = prices[i];
        }

        return maxProfit;
    }

    public static int maxProfit3(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public static int removeDuplicates(int[] nums) {
        int k = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] && count == 2) {

            } else {
                if (nums[i] == nums[i - 1])
                    count++;
                else
                    count = 1;
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (m > 0 && n > 0) {
                if (nums1[m - 1] > nums2[n - 1]) {
                    nums1[i] = nums1[m - 1];
                    m--;
                } else {
                    nums1[i] = nums2[n - 1];
                    n--;
                }
            } else if (m <= 0) {
                nums1[i] = nums2[n - 1];
                n--;
            } else {
                nums1[i] = nums1[m - 1];
                m--;
            }

        }
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end]=temp;
            end--;
            start++;
        }
    }
}