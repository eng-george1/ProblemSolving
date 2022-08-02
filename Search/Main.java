package Search;

import java.util.ArrayList;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

import javax.lang.model.util.ElementScanner14;

import Tree.BinarySearchTree.Node;

public class Main {
    public static void main(String[] args) {
        int[] array = { 2, 4, 6, 2, 4, -1, 3, 9, -4, 20, 10, 55 };
        System.out.println("Start");
        System.out.println(binarySearch(array, 550));

        // BFS && DFS
        Node root = new Node(3);
        root.insert(2);
        root.insert(4);
        root.insert(1);
        root.insert(5);
        root.insert(6);
        root.insert(-1);
        root.insert(0);
        root.insert(9);
        root.insert(8);
        root.insert(7);
        System.out.println(root.toStringOrder());
        System.out.println(breadthfirstsearch(root));
        System.out.println(depthfirstsearch(root));
        System.out.println(isOrdered(root));

    }

    public static int binarySearch(int[] array, int num) {
        int currentIndex = array.length / 2;
        int maxIndex = array.length - 1;
        int minIndex = 0;

        while (currentIndex >= 0 && currentIndex < array.length) {
            if (array[currentIndex] < num) {
                minIndex = currentIndex + 1;

            } else if (array[currentIndex] > num) {
                maxIndex = currentIndex - 1;
            } else
                return currentIndex;

            currentIndex = minIndex + (maxIndex - minIndex) / 2;
            // 3-->5,1
            // 0-6
            // 3/2,3+(6-3)/2+1
        }
        return -1;
    }

    public static <T> List<Object> breadthfirstsearch(Node tree) {
        List<Object> result = new ArrayList<Object>();

        Queue<Node> q = new LinkedList<>();
        q.add(tree);
        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            result.add(currentNode.getValue());
            if (currentNode.getLeftChild() != null)
                q.add(currentNode.getLeftChild());
            if (currentNode.getRightChild() != null)
                q.add(currentNode.getRightChild());
        }

        return result;

    }

    public static <T> List<Object> depthfirstsearch(Node tree) {
        List<Object> result = new ArrayList<Object>();
        Stack<Node> s = new Stack<>();
        s.add(tree);

        while (!s.empty()) {
            Node currentNode = s.pop();
            result.add(currentNode.getValue());
            if (currentNode.getRightChild() != null)
                s.add(currentNode.getRightChild());
            if (currentNode.getLeftChild() != null)
                s.add(currentNode.getLeftChild());
        }
        return result;
    }

    public static <T> Boolean isOrdered(Node tree) {
        Queue<Node> q = new LinkedList<>();
        q.add(tree);
        while (!q.isEmpty()) {
            Node currentNode = q.poll();
            if (currentNode.getLeftChild() != null) {
                if (currentNode.getValue() < currentNode.getLeftChild().getValue())
                    return false;
                q.add(currentNode.getLeftChild());
            }
            if (currentNode.getRightChild() != null) {
                if (currentNode.getValue() > currentNode.getRightChild().getValue())
                    q.add(currentNode.getRightChild());
            }
        }

        return true;
    }

}
