package Interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LeetCode.TreeNode;

public class PayPalPreparation {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdeafghibb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxL = 0;
        int startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                startIndex = Math.max(startIndex, map.get(s.charAt(i)));
            } else {
                map.put(s.charAt(i), i + 1);
                maxL = Math.max(maxL, i - startIndex + 1);
            }
        }
        return maxL;
    }

    public static void calcul(int n, List<Integer> nums, List<List<Integer>> result, int first) {
        if (first == n)
            result.add(new ArrayList<>(nums));
        for (int i = first; i < n; i++) {
            Collections.swap(nums, first, i);
            calcul(n, nums, result, first + 1);
            Collections.swap(nums, first, i);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int num : nums) {
            numbers.add(num);
        }
        calcul(nums.length, numbers, result, 0);
        return result;
    }

    public static void rotate(int[][] matrix) {

        int n = matrix.length - 1;
        for (int i = 0; i < n + 2 / 2; i++) {
            for (int j = 0; j < n + 1 / 2; j++) {
                int temp = matrix[n - j][i];
                matrix[n - j][i] = matrix[n - i][n - j];
                matrix[n - i][n - j] = matrix[j][n - i];
                matrix[j][n - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

    }

    private static double fastPow(double x, int n) {
        if (n == 0)
            return 1;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        return half * half * x;
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    private static boolean validate(TreeNode root, Integer low, Integer high) {
        if (root == null)
            return true;
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        return validate(root.right, root.val, high) && (validate(root.left, low, root.val));
    }

    public static boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
 
}
