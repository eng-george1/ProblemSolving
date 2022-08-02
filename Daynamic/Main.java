package Daynamic;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[] { 2, 4, 8, 9, 9, 3 };
        System.out.println(rob(arr));
        System.out.println(maxProfit(new int[] { 4, 7, 2, 1 }));
    }

    List<Integer> sums = new ArrayList<Integer>();

    // https://leetcode.com/problems/house-robber/
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[1], nums[0]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minPrice)
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            else
                minPrice = prices[i];
        }
        return maxProfit;
    }

    // https://leetcode.com/problems/climbing-stairs/
    public static int climbStairs(int n) {
        int before = 1, previous = 1;
        while (n-- > 0)
            before = (previous += before) - before;
        return before;
    }   
}
