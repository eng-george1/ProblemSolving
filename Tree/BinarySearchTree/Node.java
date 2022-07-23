package Tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.lang.model.util.ElementScanner14;

import netscape.javascript.JSObject;

public class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;

    public Node(int value) {
        this.value = value;
    }

    /**
     * @return Object return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return Node return the leftChild
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return Node return the rightChild
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    private String toStringNode(Node node) {
        StringBuilder strB = new StringBuilder();
        if (node == null)
            return "";
        strB.append(node.value);
        strB.append(System.getProperty("line.separator"));
        strB.append(toStringNode(node.leftChild));
        // strB.append(System.getProperty("line.separator"));
        strB.append(toStringNode(node.rightChild));
        // strB.append(System.getProperty("line.separator"));
        return strB.toString();
    }

    private String toStringOrderNode(Queue<Node> q) {
        StringBuilder strB = new StringBuilder();
        if (q.isEmpty())
            return "";
        Queue<Node> q1 = new LinkedList<Node>();
        while (!q.isEmpty()) {
            Node temp = q.poll();
            strB.append(temp.value);
            if (temp.getLeftChild() != null)
                q1.add(temp.getLeftChild());
            if (temp.getRightChild() != null)
                q1.add(temp.getRightChild());
            strB.append(",");
        }
        strB.append(System.getProperty("line.separator"));
        strB.append(toStringOrderNode(q1));
        return strB.toString();
    }

    public String toString() {
        return toStringNode(this);
    }

    public String toStringOrder() {
        Queue<Node> q1 = new LinkedList<Node>();
        q1.add(this);
        return toStringOrderNode(q1);
    }

    public void insert(int e) {
        insert(this, e);
    }

    public void insert2(int e) {
        if (this == null) {
            this.setValue(e);
            return;
        }
        Node currentNode = this;
        while (true) {
            if (e > currentNode.getValue()) {
                // right
                if (currentNode.rightChild != null) {
                    currentNode = currentNode.rightChild;
                } else {
                    currentNode.setRightChild(new Node(e));
                    return;
                }
            } else if (e < currentNode.getValue()) {
                // right
                if (currentNode.leftChild != null) {
                    currentNode = currentNode.leftChild;
                } else {
                    currentNode.setLeftChild(new Node(e));
                    return;
                }
            } else
                return;

        }
    }

    public void remove(int e) {
        remove(this, e);
    }

    private void remove(Node tree, int e) {
        Node currentNode = tree;
        boolean currentIsLeft = true;
        Node previousNode = null;
        while (currentNode != null) {
            if (currentNode.value == e) {
                // no child
                if (currentNode.leftChild == null && currentNode.rightChild == null) {
                    if (previousNode==null){
                    tree=null;
                        return;
                    }
                    if (currentIsLeft)
                        previousNode.leftChild = null;
                    else
                        previousNode.rightChild = null;
                    return;
                } if (currentNode.rightChild != null && (currentNode.leftChild == null||currentNode.rightChild.leftChild==null)) {
                    if(previousNode==null){
                        currentNode.value=currentNode.rightChild.value;
                        currentNode.rightChild=currentNode.rightChild.rightChild;
                        break;
                       }
                    if (currentIsLeft)
                        previousNode.leftChild = currentNode.rightChild;
                    else
                        previousNode.rightChild = currentNode.rightChild;
                    return;
                }
                 else if (currentNode.leftChild != null && (currentNode.rightChild == null||currentNode.leftChild.rightChild==null)) {
                   if(previousNode==null){
                    currentNode.value=currentNode.leftChild.value;
                    currentNode.leftChild=currentNode.leftChild.leftChild;
                    break;
                   }
                    if (currentIsLeft)
                        previousNode.leftChild = currentNode.leftChild;
                    else
                        previousNode.rightChild = currentNode.leftChild;
                    return;
                } 
                // two child
                else if (currentNode.leftChild != null && currentNode.rightChild != null) {
                    // min in right
                    Node minNode = currentNode.rightChild;
                    Node previouseMinNode = currentNode;
                   
                    while (true) {
                        if (minNode.leftChild == null) {
                            currentNode.setValue(minNode.getValue());
                            previouseMinNode.leftChild=minNode.rightChild;
                            return;
                        }
                        previouseMinNode = minNode;
                        minNode = minNode.leftChild;
                    }
                }

            } else if (currentNode.value < e) {
                // right
                if (currentNode.rightChild == null)
                    return;
                previousNode = currentNode;
                currentIsLeft = false;
                currentNode = currentNode.rightChild;

            } else {
                // left
                if (currentNode.leftChild == null)
                    return;
                previousNode = currentNode;
                currentIsLeft = true;
                currentNode = currentNode.leftChild;

            }

        }
    }

    private Node findMinLeaf(Node tree) {
        Node curreNode = tree;
        while (true) {
            if (curreNode.leftChild == null)
                return curreNode;
            curreNode = curreNode.leftChild;
        }

    }

    public void gg() {
        Node currentNode = this;
        Node previousNode = this;
        while (currentNode != null) {
            previousNode = currentNode;
            currentNode = currentNode.leftChild;
        }
    }

    private Node findMaxLeaf(Node tree) {
        Node curreNode = tree;
        while (true) {
            if (curreNode.rightChild == null)
                return curreNode;
            curreNode = curreNode.rightChild;
        }
    }

    private void insert(Node tree, int e) {
        if (tree == null) {
            tree = new Node(e);
            return;
        }
        if (tree.value < e) {
            if (tree.rightChild != null) {
                insert(tree.rightChild, e);
                return;
            }
            tree.setRightChild(new Node(e));
        } else if (tree.value > e) {
            if (tree.leftChild != null) {
                insert(tree.leftChild, e);
                return;
            }
            tree.setLeftChild(new Node(e));
        } else
            return;
    }
}
