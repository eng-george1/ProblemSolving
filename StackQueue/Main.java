package StackQueue;

public class Main {
    public static void main(String[] args) {
        ImplwithLinked data = new ImplwithLinked();
        data.push(1);
        data.push(2);
        data.pop();
        data.enqueue(2);
        data.enqueue(3);
        data.dequeue();
        System.out.println(data.toString());

        QueuUsingStack myQueue = new QueuUsingStack();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false

        // ["MyQueue","push","push","push","push","pop","push","pop","pop","pop","pop"]
        // [[],[1],[2],[3],[4],[],[5],[],[],[],[]]
        QueuUsingStack q1 = new QueuUsingStack();
        q1.push(1);
        q1.push(2);
        q1.push(3);
        q1.push(4);
        q1.pop();
        q1.push(5);
        q1.pop();
        q1.pop();
        q1.pop();
        q1.pop();

        /**
         * MyStack myStack = new MyStack();
         * myStack.push(1);
         * myStack.push(2);
         * myStack.top(); // return 2
         * myStack.pop(); // return 2
         * myStack.empty(); // return False
         */
        StackusingQueue q2 = new StackusingQueue();
        q2.push(1);
        q2.push(2);
        System.out.println(q2.top());
        System.out.println(q2.pop());
        System.out.println(q2.empty());
    }
}
