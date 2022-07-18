package LinkedList;

import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList(1);
        list.addTail(2);
        list.getNext().addTail(3).addTail(4);
        list = MyLinkedList.addHead(list, "01");
        list = MyLinkedList.insert(list, 2, 33);
        list = MyLinkedList.delete(list, 2);
        System.out.println(list.toString());
        System.out.println(list.reverse()+" Reversed");
        LinkedList linkedList = new LinkedList<>();

        // Doubly
        MyDoublyLinkeList listD = new MyDoublyLinkeList(1);
        listD.append(2);
        listD.append(3);
        listD.append(4);
        listD.prepend("01");
        listD.insert(2, 33);
        listD.delete(2);
        // listD = MyLinkedList.delete(listD, 2, 33);
        System.out.println(listD.toString());
        System.out.println(listD.reverse()+" Reversed");

    }

   
}
