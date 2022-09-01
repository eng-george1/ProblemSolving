package LeetCode;

import java.lang.Thread.State;
import java.security.DrbgParameters.Reseed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class Main4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[] { -4, -1, 0, 3, 10 })));

        TreeNode n = new TreeNode(2, new TreeNode(3), new TreeNode(1));
        System.out.println(lengthOfLongestSubstring("abcabcdeafghibb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));
        List<List<Integer>> result=   permute1(new int[]{1,2,3});
        result.forEach(c->System.out.println(Arrays.toString(c.toArray())));
      System.out.println(myPow(2,10));

    }

    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0, right = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (Math.abs(nums[right]) >= Math.abs(nums[left])) {
                result[i] = nums[right] * nums[right];
                right--;
            } else {
                result[i] = nums[left] * nums[left];
                left++;
            }
        }
        return result;
    }

    public static int[] sortedSquares2(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                stack.push(nums[i] * nums[i]);
            } else {
                int nextN = nums[i] * nums[i];
                while (!stack.isEmpty() && stack.peek() <= nextN) {
                    result[index] = stack.pop();
                    index++;
                }
                result[index] = nextN;
                index++;
            }
        }
        while (!stack.isEmpty()) {
            result[index] = stack.pop();
            index++;
        }

        return result;
    }

    private static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public static boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxL=0;
        int startIndex=0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))){
                startIndex=Math.max(startIndex,map.get(s.charAt(i)));
            }
            else{
                map.put(s.charAt(i),i+1);
                maxL=Math.max(maxL,i-startIndex+1);                
            }
        }
        return maxL;
    }
    public static List<List<Integer>> permute1(int[] num) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        res.add(new LinkedList<Integer>());
        for (int n : num) {
            int size = res.size();
            while (size > 0) {
                List<Integer> r = res.pollFirst();
                for (int i = 0; i <= r.size(); i++) {
                    List<Integer> temp = new LinkedList<Integer>(r);
                    temp.add(i, n);
                    res.add(temp);
                }
                size--;
            }
        }
        return res;
    }

        public static void backtrack(int n,
                              ArrayList<Integer> nums,
                              List<List<Integer>> output,
                              int first) {
          // if all integers are used up
          if (first == n)
            output.add(new ArrayList<Integer>(nums));
          for (int i = first; i < n; i++) {
            // place i-th integer first 
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
          }
       
        }
        public static List<List<Integer>> permute(int[] nums) {
          // init output list
          List<List<Integer>> output = new LinkedList();
      
          // convert nums into list since the output is a list of lists
          ArrayList<Integer> nums_lst = new ArrayList<Integer>();
          for (int num : nums)
            nums_lst.add(num);
      
          int n = nums.length;
          backtrack(n, nums_lst, output, 0);
          return output;
        }
    
        public static  void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int i = 0; i < (n + 1) / 2; i ++) {
                for (int j = 0; j < n / 2; j++) {
                    int temp = matrix[n - 1 - j][i];
                    matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                    matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
                    matrix[j][n - 1 - i] = matrix[i][j];
                    matrix[i][j] = temp;
                }
            }
        
        }
        private static double fastPow(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double half = fastPow(x, n / 2);
            if (n % 2 == 0) {
                return half * half;
            } else {
                return half * half * x;
            }
        }
        public static double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
    
            return fastPow(x, N);
        }
        public static  double myPow2(double x, int n) {
            if (n==0)return 1;
            if(x==0||x==1||x==-1)
            return x;
            double result=x;
            int nM=Math.abs(n);
            for(int i=0;i<nM-1;i++){
                result=result*x;
            }
            if(n<0)
            return 1/result;
            return result;
        }
        public static boolean validate(TreeNode root, Integer low, Integer high) {
            // Empty trees are valid BSTs.
            if (root == null) {
                return true;
            }
            // The current node's value must be between low and high.
            if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
                return false;
            }
            // The left and right subtree must also be valid.
            return validate(root.right, root.val, high) && validate(root.left, low, root.val);
        }
    
        public static boolean isValidBST(TreeNode root) {
            return validate(root, null, null);
        }
}
