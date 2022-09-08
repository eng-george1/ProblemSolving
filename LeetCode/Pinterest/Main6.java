package LeetCode.Pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;
import javax.management.Query;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.html.HTMLDocument.RunElement;

import Array.arrayList;
import LeetCode.TreeNode;

public class Main6 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[] { 5, 2, 3, 1 })));
        System.out.println(Arrays.toString(sortArray2(new int[] { 5, 2, 3, 1 })));

        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        System.out.println("preorder " + preorderTraversal2(tree));
        System.out.println("inOrder  " + inOrderTraversal2(tree));
        System.out.println("postorder" + postorderTraversal2(tree));
        System.out.println("BFS      " + BFSTraversal(tree));
        System.out.println("level order" + levelOrder(tree));
        System.out.println("zigzag     " + zigzagLevelOrder(tree));
        System.out.println("BFS      " + levelOrder(sortedArrayToBST(new int[] { -10, -3, 0, 5, 9 })));
        System.out.println(diameterOfBinaryTree(tree));

    }

    public static int[] sortArray(int[] nums) {
        mergesort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void mergesort(int[] nums, int low, int high) {
        if (low >= high)
            return;
        int mid = (high + low) / 2;
        mergesort(nums, low, mid);
        mergesort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high) {
        int[] result = new int[high - low + 1];
        int midCopy = mid;
        int lowCopy = low;
        mid++;
        for (int i = 0; i < result.length; i++) {
            if (low <= midCopy && mid <= high) {
                if (nums[low] < nums[mid]) {
                    result[i] = nums[low];
                    low++;
                } else {
                    result[i] = nums[mid];
                    mid++;
                }
            } else if (low <= midCopy) {
                result[i] = nums[low];
                low++;
            } else {
                result[i] = nums[mid];
                mid++;
            }
        }
        for (int j = 0; j < result.length; j++) {
            nums[j + lowCopy] = result[j];
        }
    }

    public static int[] sortArray2(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start > end)
            return;
        int pivote = partition(nums, start, end);
        quickSort(nums, start, pivote - 1);
        quickSort(nums, pivote + 1, end);
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = end;
        int pointer = start - 1;
        for (int i = start; i <= end; i++) {
            if (nums[i] <= nums[pivot]) {
                pointer++;
                int temp = nums[pointer];
                nums[pointer] = nums[i];
                nums[i] = temp;
            }
        }
        return pointer;

    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        preorderTraversal(root, ans);
        return ans;
    }

    public static void preorderTraversal(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;
        ans.add(root.val);
        preorderTraversal(root.left, ans);
        preorderTraversal(root.right, ans);
    }

    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        inOrderTraversal(root, ans);
        return ans;
    }

    public static void inOrderTraversal(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;
        inOrderTraversal(root.left, ans);
        ans.add(root.val);
        inOrderTraversal(root.right, ans);
    }

    public static List<Integer> BFSTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            ans.add(t.val);
            if (t.left != null)
                queue.add(t.left);
            if (t.right != null)
                queue.add(t.right);
        }
        return ans;
    }

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            ans.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                root = root.left;
            else if (!stack.isEmpty()) {
                root = stack.pop();
            } else {
                root = null;
            }
        }
        return ans;
    }

    public static List<Integer> inOrderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.val);
                root = root.right;

            }
        }
        return ans;
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            ans.add(0, root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        return ans;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null)
            return ans;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= size; i++) {
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
                list.add(root.val);
            }
            ans.add(list);
        }
        return ans;
    }

    static List<List<Integer>> levels = new ArrayList<>();

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return levels;

        generateLevels(root, 0);
        return levels;
    }

    public static void generateLevels(TreeNode currentNode, int levelNumber) {
        // Add New Level
        if (levels.size() == levelNumber)
            levels.add(new ArrayList<Integer>());

        levels.get(levelNumber).add(currentNode.val);

        if (currentNode.left != null)
            generateLevels(currentNode.left, levelNumber + 1);

        if (currentNode.right != null)
            generateLevels(currentNode.right, levelNumber + 1);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null)
            return ans;
        queue.add(root);
        int zig = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= size; i++) {
                root = queue.poll();
                if (1 > 0) {
                    if (root.left != null)
                        queue.add(root.left);
                    if (root.right != null)
                        queue.add(root.right);
                }
                if (zig > 0) {
                    list.add(root.val);
                } else {
                    list.add(0, root.val);
                }
            }
            zig *= -1;
            ans.add(list);
        }
        return ans;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode t = new TreeNode(nums[nums.length / 2]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(t);
        int index = nums.length / 2 - 1;
        while (!queue.isEmpty() && index >= 0) {
            TreeNode currentT = queue.poll();
            currentT.left = new TreeNode(nums[index]);
            queue.add(currentT.left);
            if (index != 0) {
                currentT.right = new TreeNode(nums[nums.length - index]);
                queue.add(currentT.right);
            }
            index--;
        }
        return t;
    }

    public static TreeNode sortedArrayToBST2(int[] nums) {
        if (nums.length == 0)
            return null;
        return arrayToBTSHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode arrayToBTSHelper(int[] nums, int low, int high) {
        if (low > high)
            return null;
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = arrayToBTSHelper(nums, low, mid - 1);
        node.right = arrayToBTSHelper(nums, mid + 1, high);
        return node;
    }

    private static int diameter;

    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        longestPath(root);
        return diameter;
    }

    private static int longestPath(TreeNode node) {
        if (node == null)
            return 0;
        // recursively find the longest path in
        // both left child and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);

        // update the diameter if left_path plus right_path is larger
        diameter = Math.max(diameter, leftPath + rightPath);

        // return the longest one between left_path and right_path;
        // remember to add 1 for the path connecting the node and its parent
        return Math.max(leftPath, rightPath) + 1;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
