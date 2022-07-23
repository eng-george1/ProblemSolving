package Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Node {
    private Object value;
    private Node leftChild;
    private Node rightChild;

    public Node(Object value) {
        this.value = value;
    }

    /**
     * @return Object return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
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
        //strB.append(System.getProperty("line.separator"));
        strB.append(toStringNode(node.rightChild));
        //strB.append(System.getProperty("line.separator"));
        return strB.toString();
    }
    private String toStringOrderNode(Queue<Node> q) {
        StringBuilder strB = new StringBuilder();
        if (q.isEmpty())
            return ""; 
            Queue<Node> q1=new LinkedList<Node>();
        while(!q.isEmpty()){
            Node temp=q.poll();
            strB.append(temp.value);
            if(temp.getLeftChild()!=null)
            q1.add(temp.getLeftChild());
            if(temp.getRightChild()!=null)
            q1.add(temp.getRightChild());
            strB.append(",");
        }
        strB.append(System.getProperty("line.separator"));
        strB.append( toStringOrderNode(q1));  
        return strB.toString();             
    }

    public String toString() {
        return toStringNode(this);
    }
    public String toStringOrder() {
        Queue<Node> q1=new LinkedList<Node>();
        q1.add(this);
        return toStringOrderNode(q1);
    }
}
