package Tree;

import Tree.BinaryTree.Node;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        Node root = new Node(1);
        root.setLeftChild(new Node(2));
        root.setRightChild(new Node(3));
        root.getLeftChild().setLeftChild(new Node(4));
        root.getLeftChild().setRightChild(new Node(5));
        System.out.println(root.toStringOrder());
    }
}
