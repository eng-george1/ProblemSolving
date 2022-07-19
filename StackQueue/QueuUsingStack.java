package StackQueue;

import java.util.Stack;

public class QueuUsingStack {
    // push, peek, pop, and empty

    Stack<Integer> first = new Stack<>();
    Stack<Integer> second = new Stack<>();
  
    public void push(int x) {
        // Pushes element x to the back of the queue.
        moving(first, second);
        second.push(x);
        moving(second, first);
    }

    private void moving(Stack<Integer> source, Stack<Integer> destination) {
        while (!source.empty()) {
            destination.push(source.pop());
        }
    }

    public int pop() {
        // Removes the element from the front of the queue and returns it.

        System.out.println(first.peek());
        return first.pop();

    }

    public int peek() {
        // Returns the element at the front of the queue.
        return first.peek();
    }

    public boolean empty() {
        // Returns true if the queue is empty, false otherwise.
        return first.empty();
    }
}
