package StackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class StackusingQueue {
    private Queue<Integer> first = new LinkedList<Integer>();
    private Queue<Integer> second = new LinkedList<Integer>();

    public void push(int x) {
        second.add(x);
        moving(first, second);
        moving(second, first);
    }

    private void moving(Queue<Integer> source, Queue<Integer> destination) {
        while (!source.isEmpty()) {
            destination.add(source.poll());
        }
    }

    public int pop() {
        return first.poll();
    }

    public int top() {
        return first.peek();
    }

    public boolean empty() {
        return first.isEmpty();
    }
}
