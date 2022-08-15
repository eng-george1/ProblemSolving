package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;
import javax.swing.undo.UndoManager;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("))(("));

        System.out.println(maxSubArray(new int[] { -1, -2, 1, 2, -5, 5 }));
        System.out.println(maxSubArray2(new int[] { -1, -2, 1, 2, -5, 5 }));

        System.out.println(fullJustify(new String[] { "hi", "th", "h", "q", "fff" }, 5));

        System.out.println(subarraySum4(new int[] { 1, 1, 2, 3, 3, 3, 5 }, 6));
        System.out.println(subarraySum(new int[] { 1, 1, 1 }, 2));
        System.out.println(subarraySum(new int[] { 1, 2, 3 }, 3));
        System.out.println(subarraySum(new int[] { 1 }, 0));
        System.out.println(subarraySum(new int[] { -1, -1, 1 }, 0));
        Main2 m = new Main2();
        m.calladdTwoNumbers();

    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.add(i);
            else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    s = s.substring(0, i) + s.substring(i + 1);
                    i--;
                } else
                    stack.pop();
            }

        }
        while (!stack.isEmpty()) {
            s = s.substring(0, stack.peek()) + s.substring(stack.pop() + 1);
        }
        return s;
    }

    private static int firstPostiveIndex(int[] nums, int startIndex) {
        if (nums.length <= startIndex)
            return -1;
        for (int i = startIndex; i < nums.length; i++) {
            if (nums[i] > 0)
                return i;
        }
        return -1;
    }

    private static int sum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static int maxSubArray(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }

        return maxSubarray;
    }

    public static int maxSubArray2(int[] nums) {
        int current = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            current = Math.max(current + nums[i], nums[i]);
            max = Math.max(current, max);
        }
        return max;
    }

    private static final String SPC = " ";

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int spaceCount = 0;
        while (i < words.length) {
            String word = words[i];
            if (sb.length() == 0 && word.length() <= maxWidth) {
                sb.append(word);
                ++i;
            } else if (1 + word.length() + sb.length() <= maxWidth) {
                sb.append(SPC);
                sb.append(word);
                ++i;
                ++spaceCount;
            } else {
                justify(sb, spaceCount, maxWidth, false);
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        justify(sb, spaceCount, maxWidth, true);
        result.add(sb.toString());
        return result;
    }

    static void justify(StringBuilder sb, int spaceCount, int maxWidth, boolean isLast) {
        if (sb.length() == maxWidth)
            return;
        int reqSpace = maxWidth - sb.length();
        int spaceIdx = sb.indexOf(SPC, 0);
        if (spaceIdx < 0 || isLast) {
            while (reqSpace > 0) {
                sb.append(SPC);
                --reqSpace;
            }
            return;
        }
        int currIdx = 0;
        while (reqSpace > 0 && spaceIdx > 0) {
            spaceIdx = sb.indexOf(SPC, currIdx);
            if (spaceIdx < 0) {
                currIdx = 0;
                spaceIdx = 1;
                continue;
            }
            sb.insert(spaceIdx, SPC);
            currIdx = spaceIdx;
            while (SPC.charAt(0) == sb.charAt(currIdx)) {
                ++currIdx;
            }
            --reqSpace;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
        TreeNode a = p;
        TreeNode b = q;

        while (a != b) {
            // a = a.parent;
            // b = b.parent;

            if (a == null) {
                a = q;
            } else {
                a = a.getParent();
            }

            if (b == null) {
                b = p;
            } else {
                b = b.getParent();
            }
        }

        return a;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode a = p;
        TreeNode b = q;
        while (a != b) {
            if (a.getParent() != null)
                a = a.getParent();
            else
                a = b;
            if (b.getParent() != null)
                b = b.getParent();
            else
                b = a;
        }
        return a;
    }

    public static int subarraySum(int[] nums, int k) {

        int count = 0;
        int startIndex = 0;
        int currentsum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentsum += nums[i];
            if (currentsum >= k) {
                if (currentsum == k) {
                    count++;
                } else {
                    while (currentsum > k && startIndex < i) {
                        currentsum -= nums[startIndex];
                        if (currentsum == k && k != 0)
                            count++;
                        startIndex++;
                    }
                }
            }
        }

        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentCount = 0;
            for (int j = i; j < nums.length; j++) {
                currentCount += nums[j];
                if (currentCount == k)
                    count++;
            }
        }
        return count;
    }

    public static int subarraySum3(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length];
        sum[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            sum[i] = nums[i] + sum[i + 1];
        }
        for (int j = 0; j < nums.length; j++) {

        }

        return count;
    }
    public static int subarraySum4(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public void calladdTwoNumbers() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        l1 = addTwoNumbers(l1, l2);
        System.out.println(l1.toString());
        ListNode l11 = new ListNode(9);
        ListNode l12 = new ListNode(1, new ListNode(9, new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9,new ListNode(9))))))))));
        l11 = addTwoNumbers(l11, l12);
        System.out.println(l11.toString());

    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummyHead.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1V = 0;
        int l2V = 0;
        int count = 1;
        while (l1 != null) {
            l1V += l1.val * count;
            l1 = l1.next;
            count *= 10;
        }
        count = 1;
        while (l2 != null) {
            l2V += l2.val * count;
            l2 = l2.next;
            count *= 10;
        }

        count = 10;
        int sumTotal = l1V + l2V;
        ListNode current = new ListNode();
        ListNode sum = new ListNode();
        current = sum;
        while (sumTotal > 0) {
            current.val = sumTotal % count; 
            sumTotal = sumTotal / 10;
            if(sumTotal==0)
            break;
            current.next = new ListNode();
           
            current = current.next;
        }
        return sum;
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            ListNode l = this;
            String s = "";
            while (l != null) {
                s += l.val;
                s += "-->";
                l = l.next;
            }
            return s;
        }
    }
}
