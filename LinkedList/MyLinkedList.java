package LinkedList;

import java.util.Stack;

public class MyLinkedList {
    private MyLinkedList next;
    // private MyLinkedList[] data;
    private Object value;

    public MyLinkedList(Object head) {
        this.value = head;
    }

    // #region setter & getter
    /**
     * @return MyLinkedList return the next
     */
    public MyLinkedList getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(MyLinkedList next) {
        this.next = next;
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

    // #endregion
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        MyLinkedList currentLinkedList = this;
        strBuilder.append("-->" + currentLinkedList.value.toString());
        while (currentLinkedList.hasNext()) {
            strBuilder.append("-->" + currentLinkedList.next.value.toString());
            currentLinkedList = currentLinkedList.next;
        }

        return strBuilder.toString();
    }

    public boolean hasNext() {
        return next != null ? true : false;
    }

    public MyLinkedList addTail(Object value) {
        MyLinkedList lastNode=this;
        while(lastNode.hasNext()){
            lastNode=lastNode.next;
        }
        lastNode.next = new MyLinkedList(value);
        return lastNode.next;
    }

    public void deleteTail() {
        MyLinkedList lastNode=this;
        while(lastNode.hasNext()){
            lastNode=lastNode.next;
        }
        lastNode.next = new MyLinkedList(value);
         lastNode.next=null;
       // next = null;
    }

    public static MyLinkedList addHead(MyLinkedList list, Object value) {
        MyLinkedList newList = new MyLinkedList(value);
        newList.next = list;
        return newList;
    }

    public static MyLinkedList insert(MyLinkedList list, int index, Object value) {
        MyLinkedList currentNode = list;
        int i = 0;
        while (currentNode.hasNext()) {
            if (i == index) {
                MyLinkedList newlist = new MyLinkedList(value);
                newlist.next = currentNode.next;
                currentNode.next = newlist;
                break;
            }
            currentNode = currentNode.next;
            i++;
        }
        return list;
    }

    public static MyLinkedList delete(MyLinkedList list, int index) {
        MyLinkedList currentNode = list;
        int i = 0;
        while (currentNode.hasNext()) {
            if (i == index) {
                currentNode.next = currentNode.next.next;
                break;
            }
            currentNode = currentNode.next;
            i++;
        }
        return list;
    }
    public static MyLinkedList reverse(MyLinkedList list) {
        Stack stack = new Stack<>();
        MyLinkedList currentNode = list;
        stack.push(currentNode.getValue());

        while (currentNode.hasNext()) {
            stack.push(currentNode.getNext().getValue());
            currentNode = currentNode.getNext();
        }
        MyLinkedList newList = new MyLinkedList(stack.pop());

        while (!stack.empty()) {
            newList.addTail(stack.pop());
        }
        return newList;
    } public  MyLinkedList reverse() {
        Stack stack = new Stack<>();
        MyLinkedList currentNode = this;
        stack.push(currentNode.getValue());

        while (currentNode.hasNext()) {
            stack.push(currentNode.getNext().getValue());
            currentNode = currentNode.getNext();
        }
        MyLinkedList newList = new MyLinkedList(stack.pop());

        while (!stack.empty()) {
            newList.addTail(stack.pop());
        }
        return newList;
    }
}
